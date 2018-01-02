package com.george.multidb.Impl;

import com.george.dao.entities.DBDetailsEntity;
import com.george.dao.entities.DBSrcInfoEntity;
import com.george.dao.entities.DBSrcMappersEntity;
import com.george.dao.mappers.DBDetailsMapper;
import com.george.dao.mappers.DBSrcMappersEntityMapper;
import com.george.multidb.SqlSessionHelper;

import java.util.*;

/**
 * Created by George on 2017/12/20.
 */
public class DBSrcInfoHelper {

    /**
     * 医院  -   数据源ID
     */
    public static Map<String, String> HOSPITAL_SRCID;
    private static DBSrcInfoHelper instance = new DBSrcInfoHelper();

    static {
        SqlSessionHelper.createLocalPool();
//        HOSPITAL_SRCID = getHospitalName_SrcID();
    }

    private DBSrcInfoHelper() {
    }

    public static DBSrcInfoHelper getInstance() {
        return instance;
    }

    public Map<String, String> getDBSrcConnDetail(Integer srcId) {
        Map<String, String> resultMap = new HashMap<String, String>();

        DBDetailsMapper dbDetailsMapper = SqlSessionHelper.getMapperInstance(DBDetailsMapper.class, 0);
        List<DBDetailsEntity> data = dbDetailsMapper.getDBSrcConnInfo(null);

        for (DBDetailsEntity dbDetails : data) {
            DBSrcInfoEntity targetInfo = findTargetDbSrcInfo(dbDetails.getDataBases(), srcId);
            if (targetInfo != null) {
                resultMap.put("driver", dbDetails.getDriverName());

                String totalUrl = dbDetails.getUrl() + targetInfo.getSrcDbName();
                resultMap.put("url", totalUrl);

                resultMap.put("username", dbDetails.getUserName());
                resultMap.put("password", dbDetails.getPassword());
                return resultMap;
            }
        }
        return null;
    }

    /**
     * 找到目标数据库信息
     */
    private DBSrcInfoEntity findTargetDbSrcInfo(List<DBSrcInfoEntity> sets, Integer targetId) {
        for (DBSrcInfoEntity info : sets) {
            if (info.getDbId() == targetId)
                return info;
        }
        return null;
    }

    public Map<String, String> getDBSrcMapperById(Integer srcId) {
        Map<String, String> resMap = new HashMap<String, String>();

        DBSrcMappersEntity entityParam = new DBSrcMappersEntity();
        entityParam.setSrcId(srcId);

        DBSrcMappersEntityMapper dbSrcMappersEntityMapper = SqlSessionHelper.getMapperInstance(DBSrcMappersEntityMapper.class, 0);
        List<DBSrcMappersEntity> mappers = dbSrcMappersEntityMapper.getSrcMappers(entityParam);

        for (DBSrcMappersEntity entity : mappers) {
            resMap.put(entity.getMapperName(), entity.getRelativePath() + entity.getMapperName());
        }
        return resMap;
    }

//
//    /**
//     * 获取 医院名 - 医院数据源ID 关系
//     */
//    public static Map<String, String> getHospitalName_SrcID() {
//        Map<String, String> hs_id = new HashMap<>();
//        HospitalDbDao dao = SqlSessionHelper.getMapperInstance(HospitalDbDao.class, 0);
//        List<Object> res = dao.getHospitals();
//        for (Object entity1 : res) {
//            HospitalDbInfoEntity entity = (HospitalDbInfoEntity) entity1;
//            hs_id.put(entity.getConn_msg_id().toString(), entity.getHospital());
//        }
//        return hs_id;
//    }
//
//    /**
//     * 刷新  HOSPITAL_SRCID  静态变量
//     */
//    public static void refreshHOSPITAL_SRCID() {
//        HOSPITAL_SRCID = getHospitalName_SrcID();
//    }
//
//    /**
//     * 获取医院所有mapper
//     */
//    public static Map<Integer, List<String>> getHospitalMappers() throws Exception {
//        Map<Integer, List<String>> records = new HashMap<Integer, List<String>>();
//        HospitalDbDao dao = SqlSessionHelper.getMapperInstance(HospitalDbDao.class, 0);
////        List<HospitalMapperEntity> list = dao.getHospitalMappers();
//        List<HospitalMapperEntity> list = dao.getHospitalMappersNew(null);
//        for (HospitalMapperEntity entity : list) {
//            Integer hospitalId = Integer.parseInt(entity.getHospital_id());
//            if (records.get(hospitalId) == null) {
//                List<String> list1 = new LinkedList<String>();
//                records.put(hospitalId, list1);
//            }
//            String relativePath = entity.getRelative_path();
//            String mapperName = entity.getMapper_name();
//            String totalPath = relativePath + "/" + mapperName;
//            if (entity.getCreateTime() != null)
//                records.get(hospitalId).add(totalPath + "," + entity.getCreateTime().toString());
//            else records.get(hospitalId).add(totalPath);
//        }
//        return records;
//    }
//
//    /**
//     * 管理端加入mapper时动态更新连接池内连接
//     */
//    public static boolean refreshHospitalSqlSession(Integer hsId) {
//        if (hsId == null)
//            return false;
////        SqlSessionHelper.getPools().remove(hsId);
//        if (hsId == 0)  //本地池创建
//            DBSrcInfoHelper.createLocalPool();
//        else
//            SqlSessionHelper.createPool(hsId);
//        return true;
//    }
//
//    /**
//     * 刷新所有连接
//     */
//    public static String refreshAll() {
//        String dbStr = "";  //刷新的数据源记录
//        Map<Integer, LinkedList<SqlSession>> id_sessions = SqlSessionHelper.getPools();
//        Iterator iter = id_sessions.keySet().iterator();
//        List list = IteratorUtils.toList(iter);
//        for (Object id : list) {
//            Integer ID = (Integer) id;
//            dbStr += ID.toString() + "  ";
//            refreshHospitalSqlSession(ID);
//        }
//        return dbStr;
//    }

}
