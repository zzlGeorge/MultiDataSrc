package com.george.multidb;

import java.util.Map;

/**
 * Created by George on 2017/12/20.
 */
public interface IDBSrcInfoHelper {
    Map<String, String> getDBSrcConnDetail(Integer srcId);
}
