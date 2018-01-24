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

    List<DBDetailsEntity> getSrcDetailsInfo(DBDetailsEntity entity);

    List<DBDetailsEntity> getSrcUrlInfo(DBDetailsEntity entity);

    List<DBSrcMappersEntity> getSrcMappersInfo(DBSrcMappersEntity entity);

    /**
     * �ҵ�ָ��mapper������Դʹ����
     * */
    List<DBSrcInfoEntity> getMapperDbUser(Integer mapperId);



    /**
     * �������ݿ������Ϣ
     * */
    boolean saveDbSrc(DBSrcInfoEntity entity);

    boolean deleteDbSrc(Integer[] ids);

    /**
     * �������ݿ������Ϣ
     * */
    boolean updateDbSrc(DBSrcInfoEntity entity);
}
