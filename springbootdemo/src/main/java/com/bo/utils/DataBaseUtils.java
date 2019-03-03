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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        //TODO 问题应该出现在属性的注入顺序上以及mybatis遍历map上
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
        logger.info("tableName:"+tableName);
        dataBaseUtilsMapper.batchInsert(tableName,columns,mapList);



        //list代表当前对象，map用以存放属性与值之间的映射
        /*List<Map<String, Object>> mapList = new ArrayList<>();
        for(T obj:list){
            Map<String, Object> map = new HashMap<>();
            StringBuilder builder = new StringBuilder();
            for(String attribute:attributes){
                String attFirst = attribute.substring(0, 1).toUpperCase();
                String attLast = attribute.substring(1, attribute.length());

                String getter = builder.append("get").
                        append(attFirst).append(attLast).toString();
                Method method = obj.getClass().getMethod(getter,null);
                Object invoke = method.invoke(method, null);
                map.put(attribute,invoke);
                builder.setLength(0);
            }
            mapList.add(map);

        }

        //思考一下，mybatis插入表格数据，那么表名有了，表中属性有了，
        //接下来映射的就是对象调用其属性给数据库插入值
        dataBaseUtilsMapper.batchInsert(tableName,columns,mapList);*/
    }
}
