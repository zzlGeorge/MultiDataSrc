package com.george.dao.mappers;

import com.george.utils.generators.mybatis.annotation.MyBatisMapper;

import com.george.utils.generators.mybatis.mapper.CRUDMapper;

import com.george.dao.entities.DBSrcMappersEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisMapper
public interface DBSrcMappersEntityMapper extends CRUDMapper<DBSrcMappersEntity, Long> {
    List<DBSrcMappersEntity> getSrcMappers(@Param("entity")DBSrcMappersEntity entity);
}
