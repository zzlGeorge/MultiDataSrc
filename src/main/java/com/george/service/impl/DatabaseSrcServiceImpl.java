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
import java.util.Date;
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

    public List<DBSrcInfoEntity> getSrcInfo(DBSrcInfoEntity entity) {
        return dbDetailsMapper.getSrcInfo(entity);
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

    public boolean saveDbSrc(DBSrcInfoEntity entity) {
        entity.setUpdateTime(new Date());
        return dbDetailsMapper.saveDbSrcInfo(entity) > 0 ? true : false;
    }

    public boolean deleteDbSrc(Integer[] ids) {
        if (ids == null) return false;
        List<DBSrcInfoEntity> list = new LinkedList<DBSrcInfoEntity>();
        for (int i = 0; i < ids.length; i++) {
            DBSrcInfoEntity entity = new DBSrcInfoEntity();
            entity.setDbId(ids[i]);
            entity.setDeleteFlag(1);
            list.add(entity);
        }
        return dbDetailsMapper.updateBatch(list) > 0 ? true : false;
    }

    public boolean updateDbSrc(DBSrcInfoEntity entity) {
        List<DBSrcInfoEntity> list = new LinkedList<DBSrcInfoEntity>();
        list.add(entity);
        return dbDetailsMapper.updateBatch(list) > 0 ? true : false;
    }
}
