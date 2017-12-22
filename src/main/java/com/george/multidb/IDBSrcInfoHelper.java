package com.george.multidb;

import java.util.Map;

/**
 * Created by George on 2017/12/20.
 */
public interface IDBSrcInfoHelper {
    /**
     * 查找srcId号数据库的连接信息
     */
    Map<String, String> getDBSrcConnDetail(Integer srcId);

    /**
     * 通过srcId获得mapper信息
     * */
    Map<String, String> getDBSrcMapperById(Integer srcId);
}
