package com.george.service.impl;

import com.george.dao.entities.DBSrcInfoEntity;
import com.george.dao.entities.DBSrcMappersEntity;
import com.george.dao.mappers.DBSrcMappersEntityMapper;
import com.george.multidb.SqlSessionHelper;
import com.george.service.DatabaseSrcMapperService;
import com.george.utils.jdbcUtils.JdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by George on 2018/1/29.
 */
@Service
public class DatabaseSrcMapperServiceImpl implements DatabaseSrcMapperService{

    @Autowired
    private DBSrcMappersEntityMapper dbSrcMappersEntityMapper;

    public List<DBSrcMappersEntity> getSrcMappersInfo(DBSrcMappersEntity entity) {
        return dbSrcMappersEntityMapper.findByEntity(entity, null);
    }
}
