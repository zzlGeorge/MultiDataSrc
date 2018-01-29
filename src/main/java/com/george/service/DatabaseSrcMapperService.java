package com.george.service;

import com.george.dao.entities.DBSrcMappersEntity;

import java.util.List;

/**
 * Created by George on 2018/1/29.
 */
public interface DatabaseSrcMapperService {
    List<DBSrcMappersEntity> getSrcMappersInfo(DBSrcMappersEntity entity);
}
