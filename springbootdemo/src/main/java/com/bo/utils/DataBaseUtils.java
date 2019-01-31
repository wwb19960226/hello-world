package com.bo.utils;


import com.bo.annotate.DbColumn;
import com.bo.annotate.DbTable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 关于数据库的工具操作类
 * @param <T>
 */
@Component
public class DataBaseUtils<T> {

    /**
     * 批量插入数据库
     * @param list 插入集合
     * @return
     */
    public Boolean batchInstert(List<T> list){

        if(CollectionUtils.isEmpty(list)){
            return false;
        }
        T t = list.get(0);
        Class<?> aclass = t.getClass();
        boolean annotationPresent = aclass.isAnnotationPresent(DbTable.class);

        if(annotationPresent == false){
            return Boolean.FALSE;
        }
        DbTable dbTable = aclass.getAnnotation(DbTable.class);
        HashMap<String, List<String>> map = new HashMap<>();
        List<String> columns = new ArrayList<>();
        Field[] fields = aclass.getFields();
        for(Field field:fields){
            boolean annotationPresentField = field.isAnnotationPresent(DbColumn.class);
            if(annotationPresentField == true){
                //获取当前属性名称
                DbColumn dbColumn = field.getAnnotation(DbColumn.class);
                columns.add(dbColumn.name());
            }
        }
        //获取到标注批量添加属性的级别
        map.put(dbTable.name(),columns);
        //将集合数据与表名所对应

        return false;
    }
}
