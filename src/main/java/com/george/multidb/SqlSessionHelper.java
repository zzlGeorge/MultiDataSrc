package com.george.multidb;

import com.george.multidb.Impl.SqlSessionPools;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
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
//        sessionPools.closeSession(targetSession);
        return resConn;
    }

    /**
     * 从数据源中获取一个数据库连接
     */
    public static Connection getConnectionFromDataSource(Integer id) {
        SqlSessionFactory factory = sessionPools.getSqlSessionFactory(id);
        Connection connection = null;
        try {
            connection = factory.getConfiguration().getEnvironment().getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 获取id号数据源
     */
    public static DataSource getDataSource(Integer id) {
        SqlSessionFactory factory = sessionPools.getSqlSessionFactory(id);
        return factory.getConfiguration().getEnvironment().getDataSource();
    }

    /**
     * 通过id和已创建的Mapper获取Mapper实例
     *
     * @param mapper 已创建的Mapper接口
     * @param srcID  数据源id
     */
    public static <T> T getMapperInstance(Class<T> mapper, Integer srcID) {
        SqlSession session = null;
        T instance = null;
        try {
            session = getSqlSession(srcID);
            session = checkSqlSession(session, srcID);
            instance = session.getMapper(mapper);
        } catch (Exception e) {
            String mapperTotalName = mapper.getName();
            String result = srcID + "号数据源没有配置" + mapperTotalName.
                    substring(mapperTotalName.lastIndexOf(".") + 1, mapperTotalName.length()) + ".xml文件";
            System.out.println(result);
        }
        return instance;
    }


    /**
     * 通过管理类来解决操作数据库的事务问题
     *
     * @param mapper mapper接口
     * @param srcId  数据源id
     * @param <T>    Mybatis的Mapper接口类型
     * @return 生成mapper管理对象
     */
    public static <T> Object getTxMapperManager(Class<T> mapper, Integer srcId) {
        if (!sessionPools.hasThePool(srcId))
            sessionPools.createSessionPool(srcId);
        SqlSession sqlSession = sessionPools.getSessionFactories()
                .get(srcId).openSession(false);//获取最原始的mapper
        Object mapperInstance = sqlSession.getMapper(mapper);
        return new TXMapperManager(sqlSession, mapperInstance);
    }

    public static void createLocalPool() {
        sessionPools.createLocalPool();
    }

    public static void createPool(Integer id) {
        sessionPools.createSessionPool(id);
    }

    /*public static Map<Integer, LinkedList<SqlSession>> getPools() {
        return sessionPools.getPools();
    }*/

    public static Map<Integer, SqlSession> getPools() {
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

//    public static SqlSessionPools getSessionPoolsInstance() {
//        return sessionPools;
//    }

}
