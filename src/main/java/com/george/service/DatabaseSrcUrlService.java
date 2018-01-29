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
     * �������ݿ������Ϣ
     * */
    boolean saveDbUrlSrc(DBDetailsEntity entity);

    /**
     * ɾ�����ݿ����
     * */
    boolean deleteDbUrlSrc(Integer[] ids);

    /**
     * �������ݿ���������Ϣ
     * */
    boolean updateDbUrlSrc(DBDetailsEntity entity);
}
