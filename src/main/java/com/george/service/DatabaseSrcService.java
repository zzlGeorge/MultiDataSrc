package com.george.service;

import com.george.dao.entities.DBDetailsEntity;
import com.george.dao.entities.DBSrcInfoEntity;
import com.george.dao.entities.DBSrcMappersEntity;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by George on 2018/1/2.
 */
public interface DatabaseSrcService {
    List<DBSrcInfoEntity> getSrcInfo(DBSrcInfoEntity entity);

    /**
     * 找到指定mapper的数据源使用者
     * */
    List<DBSrcInfoEntity> getMapperDbUser(Integer mapperId);

    /**
     * 保存数据库相关信息
     * */
    boolean saveDbSrc(DBSrcInfoEntity entity);

    boolean deleteDbSrc(Integer[] ids);

    /**
     * 更新数据库基本信息
     * */
    boolean updateDbSrc(DBSrcInfoEntity entity);

}
