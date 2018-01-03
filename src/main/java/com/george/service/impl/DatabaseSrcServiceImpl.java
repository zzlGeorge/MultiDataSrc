package com.george.service.impl;

import com.george.dao.entities.DBDetailsEntity;
import com.george.dao.entities.DBSrcInfoEntity;
import com.george.dao.mappers.DBDetailsMapper;
import com.george.service.DatabaseSrcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by George on 2018/1/2.
 */

@Service
public class DatabaseSrcServiceImpl implements DatabaseSrcService {
    @Autowired
    private DBDetailsMapper dbDetailsMapper;

    public List<DBSrcInfoEntity> getSrcInfo(DBSrcInfoEntity entity) {
        return dbDetailsMapper.getSrcInfo(entity);
    }

    public List<DBDetailsEntity> getSrcDetailsInfo(DBDetailsEntity entity) {
        return dbDetailsMapper.getDBSrcConnInfo(entity);
    }

    public List<DBDetailsEntity> getSrcUrlInfo(DBDetailsEntity entity) {
        return dbDetailsMapper.findByEntity(entity);
    }
}
