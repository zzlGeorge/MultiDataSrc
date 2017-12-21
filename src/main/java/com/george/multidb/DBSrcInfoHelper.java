package com.george.multidb;

import com.george.dao.entities.DBDetails;
import com.george.dao.entities.DBSrcInfo;
import com.george.dao.mappers.DBDetailsMapper;
import com.george.utils.jdbcUtils.JdbcUtil;

import java.sql.Connection;
import java.util.*;

/**
 * Created by George on 2017/8/9.
 */
public class DBSrcInfoHelper implements IDBSrcInfoHelper {

    /**
     * 医院  -   数据源ID
     */
    public static Map<String, String> HOSPITAL_SRCID;

    private DBDetailsMapper dbDetailsMapper = SqlSessionHelper.getMapperInstance(DBDetailsMapper.class, 0);

    static {
        SqlSessionHelper.createLocalPool();
//        HOSPITAL_SRCID = getHospitalName_SrcID();
    }

    public Map<String, String> getDBSrcConnDetail(Integer srcId) {
        Map<String, String> resultMap = new HashMap<String, String>();
        List<DBDetails> data = dbDetailsMapper.getDBSrcConnInfo(null);
        for (DBDetails dbDetails : data) {
            DBSrcInfo targetInfo = findTargetDbSrcInfo(dbDetails.getDataBases(), srcId);
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
    private DBSrcInfo findTargetDbSrcInfo(List<DBSrcInfo> sets, Integer targetId) {
        for (DBSrcInfo info : sets) {
            if (info.getId() == targetId)
                return info;
        }
        return null;
    }


    //    public static Map<String, String> getHostpitalConnDetail(Integer hospitalId) throws Exception {
//        Map<String, String> resultMap = new HashMap<String, String>();
//        HospitalDbDao dao = SqlSessionHelper.getMapperInstance(HospitalDbDao.class, 0);
//        HospitalDbInfoEntity entity = dao.getHospitalConnDetail(hospitalId);
//        if (entity != null) {
//            resultMap.put("driver", entity.getDriver_name());
//            resultMap.put("url", entity.getUrl());
//            resultMap.put("username", entity.getUser());
//            resultMap.put("password", entity.getPassword());
//        }
//        return resultMap;
//    }
//
//    /**
//     * 获取某家医院的所有mapper信息
//     */
//    public static Map<String, String> getHospitalMapperById(Integer id) throws Exception {
//        Map<String, String> mapper = new HashMap<String, String>();
//        HospitalDbDao dao = SqlSessionHelper.getMapperInstance(HospitalDbDao.class, 0);
////        List<HospitalMapperEntity> list = dao.getHospitalMapperById(id);
//        List<HospitalMapperEntity> list = dao.getHospitalMappersNew(id.toString());
//        for (HospitalMapperEntity entity : list) {
//            String relativePath = entity.getRelative_path();
//            String mapperName = entity.getMapper_name();
//            String totalPath = relativePath + "/" + mapperName;
//            mapper.put(mapperName, totalPath);
//        }
//        return mapper;
//    }
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
