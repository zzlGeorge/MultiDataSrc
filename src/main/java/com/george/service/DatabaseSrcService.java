package com.george.service;

import com.george.dao.entities.DBDetailsEntity;
import com.george.dao.entities.DBSrcInfoEntity;

import java.util.List;

/**
 * Created by George on 2018/1/2.
 */
public interface DatabaseSrcService {
    List<DBSrcInfoEntity> getSrcInfo(DBSrcInfoEntity entity);

    List<DBDetailsEntity> getSrcDetailsInfo(DBDetailsEntity entity);
}
