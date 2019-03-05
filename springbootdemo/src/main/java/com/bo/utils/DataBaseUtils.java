package com.bo.utils;


import com.bo.annotate.DbColumn;
import com.bo.annotate.DbTable;
import com.bo.mapper.utils.DataBaseUtilsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 关于数据库的工具操作类
 * @param <T>
 */
@Component
public class DataBaseUtils<T> {

    private Logger logger = LoggerFactory.getLogger(DataBaseUtils.class);
    /**
     * 数据库工具类Mapper
     */
    @Autowired
    private DataBaseUtilsMapper dataBaseUtilsMapper;



    /**
     * 批量插入数据库
     * @param list 插入集合
     * @return
     */
    public void batchInstert(List<T> list) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        if(CollectionUtils.isEmpty(list)){
            throw new RuntimeException("批量添加集合不能为空");
        }
        T t = list.get(0);
        Class<?> aclass = t.getClass();
        boolean annotationPresent = aclass.isAnnotationPresent(DbTable.class);

        if(annotationPresent == false){
            throw new RuntimeException("批量添加未获取数据库注解");
        }
        DbTable dbTable = aclass.getAnnotation(DbTable.class);
        List<String> columns = new ArrayList<>();
        List<String> attributes = new ArrayList<>();
        Field[] fields = aclass.getDeclaredFields();
        for(Field field:fields){
            boolean annotationPresentField = field.isAnnotationPresent(DbColumn.class);
            if(annotationPresentField == true){
                //获取当前数据库属性名称
                DbColumn dbColumn = field.getAnnotation(DbColumn.class);
                columns.add(dbColumn.name());
                //获取当前类属性名称
                String name = field.getName();
                attributes.add(name);

            }
        }
        //获取到标注批量添加属性的级别
        String tableName = dbTable.name();

       List<Map<String, Object>> mapList = new ArrayList<>();
        for(T obj:list){
            Map<String, Object> map = new LinkedHashMap<>();
            StringBuilder builder = new StringBuilder();
            for(String attribute:attributes){
                String attFirst = attribute.substring(0, 1).toUpperCase();
                String attLast = attribute.substring(1, attribute.length());

                String getter = builder.append("get").
                        append(attFirst).append(attLast).toString();
                Method method = obj.getClass().getMethod(getter,null);
                Object invoke = method.invoke(obj, null);
                map.put(attribute, invoke);
                builder.setLength(0);
            }
            mapList.add(map);

        }
        dataBaseUtilsMapper.batchInsert(tableName,columns,mapList);
    }

    /**
     * 实现任意类的批量修改
     * @param list 要修改的对象集合
     * @param t    修改对象的字节码
     */
    public void batchUpdate(List<T> list,Class<T> t) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(CollectionUtils.isEmpty(list)){
            throw new RuntimeException("批量修改集合不能为空");
        }
        //思路：1.通过反射获取list集合中对应的ID，2.通过ID在数据库中查询到对应的数据
        //3.对比新旧数据，将改变后的数据改入数据库中
        String getId = "getId";
        Method method = t.getMethod(getId, null);
        if(method == null){
            throw new RuntimeException("没有找到对应getId方法");
        }

        //获取到对应的ID集合
        List<Object> idList = new ArrayList<>();
        for(T obj: list){
            Object invoke = method.invoke(obj, null);
            idList.add(invoke);
        }

        //获取自定义注解的表名，属性以及是否可以修改
        DbTable dbTable = t.getAnnotation(DbTable.class);
        if(dbTable == null){
            throw new RuntimeException("DbTable注解无法获取表名");
        }
        //获取表名
        String tableName = dbTable.name();

        Field[] fields = t.getDeclaredFields();

        if(fields == null || fields.length == 0){
            throw new RuntimeException("未找到要修改的属性");
        }

        Map<String, String> linkedMap = new LinkedHashMap<>();
        for(Field field :fields){
            DbColumn dbColumn = field.getAnnotation(DbColumn.class);
            if(dbColumn != null){
                String columnName = dbColumn.name();
                boolean canModify = dbColumn.canModify();
                if(!canModify){
                    continue;
                }
                //要修改的表列名称
                linkedMap.put(field.getName(),columnName);
            }
        }

        //通过表名，表列名称，对应的ID数组查询出对应属性
        if(CollectionUtils.isEmpty(linkedMap)){
            throw new RuntimeException("未找到要修改的属性");
        }
        //这里的泛型返回类型出了一些问题，我想要返回一个对象，类名无法通过参数传递，也无法用object来返回，最后，只能使用map返回
        List<Map<String,Object>> oldList = dataBaseUtilsMapper.queryUpdateAttribute(tableName,linkedMap,idList,t.getName());
        if(CollectionUtils.isEmpty((oldList))){
            throw new RuntimeException("要修改的数据不存在");
        }
        Map<Long, Map<String, Object>> oldMap = new HashMap<>();
        for(Map<String,Object> oldObj: oldList){
            oldMap.put((Long) oldObj.get("id"),oldObj);
        }


        //我先考虑一下关于批量修改的设计思想方面  修改的话是将对象要修改的属性给统计到一个map中，key为属性名，value为属性值
        List<Map<String, Object>> updateObjs = new ArrayList<>();
        for(T newObj: list){
            //存放修改后的属性和值
            Map<String, Object> updateAttrabutes = new HashMap<>();
            Long id = (Long)method.invoke(newObj, null);
            //旧据中包含该主键ID
            if(oldMap.containsKey(id)){
                //获取到的旧对象 String为属性名 Object为属性对应的值 新对象为newObj
                Map<String, Object> oldObj = oldMap.get(id);
                for(Map.Entry<String, Object> oldAttrbute:oldObj.entrySet()){
                    //通过反射获取新对象中对应的值，与旧对象开始比较
                    String key = oldAttrbute.getKey();
                    Object oldValue  = oldAttrbute.getValue();
                    Object newValue = getMethodValue(key, t, newObj);
                    //从前后台传递过来的对象有可能是空值（非必填项）
                    if(!(oldValue != null?oldValue.toString():"").equals(newValue != null?newValue.toString():"")) {
                        String columnName = linkedMap.get(key);
                        updateAttrabutes.put(columnName, newValue);
                    }

                }
                if(!CollectionUtils.isEmpty(updateAttrabutes)){
                    updateAttrabutes.put("f_id",id);
                    updateObjs.add(updateAttrabutes);
                }
            }
        }
        if(!CollectionUtils.isEmpty(updateObjs)){
            dataBaseUtilsMapper.batchUpdate(tableName,updateObjs);
        }
    }

    /**
     * 传入属性名获取属性值的方法
     * @param attName 传入的属性名
     *@param t 传入的字节码
     *@param obj 要调用的实体类对象
     * @return 获取到的属性值
     */
    private Object getMethodValue(String attName,Class<T> t,T obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        StringBuilder builder = new StringBuilder("get");
        String beginName = attName.substring(0, 1).toUpperCase();
        String endName = attName.substring(1,attName.length());
        String getter = builder.append(beginName).append(endName).toString();
        Method method = t.getMethod(getter, null);
        if(method == null){
            throw new RuntimeException("未找到当前属性方法："+getter);
        }
        Object invoke = method.invoke(obj, null);
        return invoke;
    }
}
