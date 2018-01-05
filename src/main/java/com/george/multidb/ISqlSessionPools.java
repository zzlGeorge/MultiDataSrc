package com.george.multidb;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by George on 2017/12/20.
 */
public interface ISqlSessionPools {
    /**
     * 创建id号连接池
     *
     * @param id 数据源id
     */
    void createSessionPool(Integer id);

    /**
     * 创建本地数据源
     * */
    void createLocalPool();

    /**
     * 从id号连接池获取session(线程安全的)
     *
     * @param id 数据源id
     */
    SqlSession getSession(final Integer id);

    /**
     * 获取id号数据库的session工厂
     * */
    SqlSessionFactory getSqlSessionFactory(Integer id);

    /**
     * 通过id获取sessions
     *
     * @param id 数据源id
     */
    List<SqlSession> getSessionBySrcId(Integer id);

    boolean hasThePool(Integer id);

    Map<Integer, LinkedList<SqlSession>> getPools();

    Map<Integer,SqlSessionFactory> getSessionFactories();

    void closeSession(SqlSession session);

    boolean closeSqlSessionPool(Integer id);
}
