package com.george.multidb;

import com.george.multidb.Impl.SqlSessionPools;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by George on 2017/8/22.
 */
public class SqlSessionHelper {
    private static Logger log = Logger.getLogger(SqlSessionHelper.class);
    private static SqlSessionPools sessionPools = new SqlSessionPools();

    /**
     * 获取id号数据源SqlSession
     */
    public static SqlSession getSqlSession(Integer id) {
        return sessionPools.getSession(id);
    }

    /**
     * 获取id号池的一个连接
     *
     * @param id 数据源id
     * @return 该数据源下的一个连接
     */
    public static Connection getPoolConn(Integer id) {
        if (id == null) return null;
        SqlSession targetSession = getSqlSession(id);
        try {
            targetSession = checkSqlSession(targetSession, id);//检查
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection resConn = targetSession.getConnection();
        sessionPools.closeSession(targetSession);
        return resConn;
    }

    /**
     * 通过（医院）id和已创建的Mapper获取Mapper实例
     *
     * @param mapper     已创建的Mapper接口
     * @param hospitalId 医院id
     */
    public static <T> T getMapperInstance(Class<T> mapper, Integer hospitalId) {
        SqlSession session = null;
        T instance = null;
        try {
            session = getSqlSession(hospitalId);
            session = checkSqlSession(session, hospitalId);
            instance = session.getMapper(mapper);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionPools.closeSession(session);
        }
        return instance;
    }

    public static void createLocalPool() {
        sessionPools.createLocalPool();
    }

    public static void createPool(Integer id) {
        sessionPools.createSessionPool(id);
    }

    public static Map<Integer, LinkedList<SqlSession>> getPools() {
        return sessionPools.getPools();
    }

    /**
     * 检查sqlSession
     *
     * @param session    需检查的SqlSession
     * @param hospitalId 数据源id
     */
    private static SqlSession checkSqlSession(SqlSession session, Integer hospitalId) throws Exception {
        SqlSession resSession = session;
        /*if (!resSession.getConnection().isValid(5)) { //如果SQLSession失效
            log.info(hospitalId + "号池" + "已经失效，启动刷新！");
            if (hospitalId == 0) {//本地池创建方式不一样
                createLocalPool();
            } else {
                sessionPools.createSessionPool(hospitalId);
            }
            resSession = getSqlSession(hospitalId);
        }*/
        return resSession;
    }

}
