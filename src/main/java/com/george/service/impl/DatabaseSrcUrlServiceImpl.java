package com.george.service.impl;

import com.george.dao.entities.DBDetailsEntity;
import com.george.dao.mappers.DBDetailsMapper;
import com.george.service.DatabaseSrcUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by George on 2018/1/29.
 */

@Service
public class DatabaseSrcUrlServiceImpl implements DatabaseSrcUrlService{
    @Autowired
    private DBDetailsMapper dbDetailsMapper;

    public List<DBDetailsEntity> getSrcDetailsInfo(DBDetailsEntity entity) {
        return dbDetailsMapper.getDBSrcConnInfo(entity);
    }

    public List<DBDetailsEntity> getSrcUrlInfo(DBDetailsEntity entity) {
        return dbDetailsMapper.findByEntity(entity);
    }

    public boolean saveDbUrlSrc(DBDetailsEntity entity) {
        entity.setUpdateTime(new Date());
        return dbDetailsMapper.saveDbUrlSrc(entity) > 0 ? true : false;
    }

    public boolean deleteDbUrlSrc(Integer[] ids) {
        if (ids == null) return false;
        List<DBDetailsEntity> list = new LinkedList<DBDetailsEntity>();
        for (int i = 0; i < ids.length; i++) {
            DBDetailsEntity entity = new DBDetailsEntity();
            entity.setId(ids[i]);
            entity.setDeleteFlag(1);
            list.add(entity);
        }
        return dbDetailsMapper.updateBatchDbUrl(list) > 0 ? true : false;
    }

    public boolean updateDbUrlSrc(DBDetailsEntity entity) {
        List<DBDetailsEntity> list = new LinkedList<DBDetailsEntity>();
        list.add(entity);
        return dbDetailsMapper.updateBatchDbUrl(list) > 0 ? true : false;
    }
}
