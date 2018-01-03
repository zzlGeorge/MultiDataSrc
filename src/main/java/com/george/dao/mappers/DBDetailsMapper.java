package com.george.dao.mappers;

import com.george.dao.entities.DBSrcInfoEntity;
import com.george.utils.generators.mybatis.annotation.MyBatisMapper;

import com.george.utils.generators.mybatis.mapper.CRUDMapper;

import com.george.dao.entities.DBDetailsEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisMapper
public interface DBDetailsMapper extends CRUDMapper<DBDetailsEntity, Long> {
    List<DBDetailsEntity> getDBSrcConnInfo(@Param("entity") DBDetailsEntity entity);

    List<DBSrcInfoEntity> getSrcInfo(@Param("entity") DBSrcInfoEntity entity);

    List<DBDetailsEntity> findByEntity(@Param("entity") DBDetailsEntity entity);
}
