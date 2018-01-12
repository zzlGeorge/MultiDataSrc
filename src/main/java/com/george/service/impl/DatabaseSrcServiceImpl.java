package com.george.service.impl;

import com.george.dao.entities.DBDetailsEntity;
import com.george.dao.entities.DBSrcInfoEntity;
import com.george.dao.entities.DBSrcMappersEntity;
import com.george.dao.mappers.DBDetailsMapper;
import com.george.dao.mappers.DBSrcMappersEntityMapper;
import com.george.multidb.SqlSessionHelper;
import com.george.service.DatabaseSrcService;
import com.george.utils.jdbcUtils.JdbcDao;
import com.george.utils.jdbcUtils.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by George on 2018/1/2.
 */

@Service
public class DatabaseSrcServiceImpl implements DatabaseSrcService {
    @Autowired
    private DBDetailsMapper dbDetailsMapper;

    @Autowired
    private DBSrcMappersEntityMapper dbSrcMappersEntityMapper;

    public List<DBSrcInfoEntity> getSrcInfo(DBSrcInfoEntity entity) {
        return dbDetailsMapper.getSrcInfo(entity);
    }

    public List<DBDetailsEntity> getSrcDetailsInfo(DBDetailsEntity entity) {
        return dbDetailsMapper.getDBSrcConnInfo(entity);
    }

    public List<DBDetailsEntity> getSrcUrlInfo(DBDetailsEntity entity) {
        return dbDetailsMapper.findByEntity(entity);
    }

    public List<DBSrcMappersEntity> getSrcMappersInfo(DBSrcMappersEntity entity) {
        return dbSrcMappersEntityMapper.findByEntity(entity, null);
    }

    public List<DBSrcInfoEntity> getMapperDbUser(Integer mapperId) {
        Connection connection = SqlSessionHelper.getConnectionFromDataSource(0);
        StringBuffer ids = new StringBuffer();
        try {
            List<Map<String, Object>> result = JdbcDao.findResult(connection,
                    "SELECT srcId FROM src_and_mapper WHERE mapperId=" + mapperId, null);
            for (Map<String, Object> m : result) {
                System.out.println(m);
                ids.append(m.get("srcId") + ", ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBSrcInfoEntity paramEntity = new DBSrcInfoEntity();
        paramEntity.setIds(ids.substring(0, ids.length() - 2));
        return dbDetailsMapper.getSrcInfo(paramEntity);
    }
}
