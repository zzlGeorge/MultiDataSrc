package com.george.dao.mappers;

import com.george.utils.generators.mybatis.annotation.MyBatisMapper;

import com.george.utils.generators.mybatis.mapper.CRUDMapper;

import com.george.dao.entities.DBDetails;

@MyBatisMapper
public interface DBDetailsMapper extends CRUDMapper<DBDetails, Long> {

}
