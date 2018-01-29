package com.george.service;

import com.george.dao.entities.DBDetailsEntity;

import java.util.List;

/**
 * Created by George on 2018/1/29.
 */
public interface DatabaseSrcUrlService {
    List<DBDetailsEntity> getSrcDetailsInfo(DBDetailsEntity entity);

    List<DBDetailsEntity> getSrcUrlInfo(DBDetailsEntity entity);

    /**
     * 保存数据库服务信息
     * */
    boolean saveDbUrlSrc(DBDetailsEntity entity);

    /**
     * 删除数据库服务
     * */
    boolean deleteDbUrlSrc(Integer[] ids);

    /**
     * 更新数据库服务基本信息
     * */
    boolean updateDbUrlSrc(DBDetailsEntity entity);
}
