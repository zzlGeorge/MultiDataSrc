package com.george.multidb;

import com.george.dao.entities.DBSrcInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by George on 2017/12/20.
 */
public interface IDBSrcInfoHelper {
    /**
     * 查找srcId号数据库的连接信息
     */
    Map<String, String> getDBSrcConnDetail(Integer srcId);
}
