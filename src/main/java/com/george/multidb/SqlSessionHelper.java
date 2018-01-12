package com.george.multidb;

import com.george.multidb.Impl.SqlSessionPools;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by George on 2017/8/22.
 */
public class SqlSessionHelper {
    private static Logger log = Logger.getLogger(SqlSessionHelper.class);
    private static SqlSessionPools sessionPools = new SqlSessionPools();

    /**
     * ��ȡid������ԴSqlSession
     */
    public static SqlSession getSqlSession(Integer id) {
        return sessionPools.getSession(id);
    }


    /**
     * ��ȡid�ųص�һ������
     *
     * @param id ����Դid
     * @return ������Դ�µ�һ������
     */
    public static Connection getPoolConn(Integer id) {
        if (id == null) return null;
        SqlSession targetSession = getSqlSession(id);
        try {
            targetSession = checkSqlSession(targetSession, id);//���
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection resConn = targetSession.getConnection();
//        sessionPools.closeSession(targetSession);
        return resConn;
    }

    /**
     * ������Դ�л�ȡһ�����ݿ�����
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
     * ��ȡid������Դ
     */
    public static DataSource getDataSource(Integer id) {
        SqlSessionFactory factory = sessionPools.getSqlSessionFactory(id);
        return factory.getConfiguration().getEnvironment().getDataSource();
    }

    /**
     * ͨ����ҽԺ��id���Ѵ�����Mapper��ȡMapperʵ��
     *
     * @param mapper     �Ѵ�����Mapper�ӿ�
     * @param hospitalId ҽԺid
     */
    public static <T> T getMapperInstance(Class<T> mapper, Integer hospitalId) {
        SqlSession session = null;
        T instance = null;
        try {
            session = getSqlSession(hospitalId);
            session = checkSqlSession(session, hospitalId);
            instance = session.getMapper(mapper);
        } catch (Exception e) {
            String mapperTotalName = mapper.getName();
            String result = hospitalId + "������Դû������" + mapperTotalName.
                    substring(mapperTotalName.lastIndexOf(".") + 1, mapperTotalName.length()) + ".xml�ļ�";
            System.out.println(result);
        } finally {
//            sessionPools.closeSession(session);
        }
        return instance;
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
     * ���sqlSession
     *
     * @param session    �����SqlSession
     * @param hospitalId ����Դid
     */
    private static SqlSession checkSqlSession(SqlSession session, Integer hospitalId) throws Exception {
        SqlSession resSession = session;
        /*if (!resSession.getConnection().isValid(5)) { //���SQLSessionʧЧ
            log.info(hospitalId + "�ų�" + "�Ѿ�ʧЧ������ˢ�£�");
            if (hospitalId == 0) {//���سش�����ʽ��һ��
                createLocalPool();
            } else {
                sessionPools.createSessionPool(hospitalId);
            }
            resSession = getSqlSession(hospitalId);
        }*/
        return resSession;
    }

}
