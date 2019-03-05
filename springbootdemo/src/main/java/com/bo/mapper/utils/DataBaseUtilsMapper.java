package com.bo.mapper.utils;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataBaseUtilsMapper<T> {

    /**
     *
     * @param tableName 数据库表名
     *
     * @param columns 表中列名
     *
     */
    void batchInsert(@Param("tableName") String tableName,
            @Param("columns") List<String> columns,@Param("mapList") List<Map<String, Object>> mapList);

    /**
     * 查询要修改的记录以及其中的属性
     * @param tableName 表名
     * @param linkedMap 要查询的表中列名与别名集合，键代表列名，值代表别名
     * @param idList 要查询的记录Id，
     * @return
     */
    List<Map<String,Object>> queryUpdateAttribute(@Param("tableName") String tableName, @Param("linkedMap") Map<String,String> linkedMap, @Param("idList") List<Object> idList,@Param("className") String className);

    /**
     * 批量修改表中数据
     * @param tableName 表名
     * @param updateObjs 要修改的对象集合
     */
    void batchUpdate(@Param("tableName") String tableName,@Param("updateObjs") List<Map<String, Object>> updateObjs);
}
