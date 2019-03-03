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
}
