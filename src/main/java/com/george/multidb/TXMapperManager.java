package com.george.multidb;

import org.apache.ibatis.session.SqlSession;

/**
 * Created by George on 2018/3/19.
 * SqlSession的Mapper管理对象
 */
public class TXMapperManager {

    private Object mapperInstance;
    private SqlSession targetSqlSession;

    public TXMapperManager(SqlSession targetSqlSession, Object mapperInstance) {
        this.targetSqlSession = targetSqlSession;
        this.mapperInstance = mapperInstance;
    }


    /**
     * 完成事务并提交
     */
    public void finishedAndCommit() {
        try {
            this.targetSqlSession.commit();
        } finally {
            this.targetSqlSession.close();
        }
    }

    public Object getMapperInstance() {
        return mapperInstance;
    }

    public void setMapperInstance(Object mapperInstance) {
        this.mapperInstance = mapperInstance;
    }
}
