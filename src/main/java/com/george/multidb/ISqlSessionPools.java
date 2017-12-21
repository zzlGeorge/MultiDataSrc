package com.george.multidb;

import org.apache.ibatis.session.SqlSession;

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
     * 从id号连接池获取session(线程安全的)
     *
     * @param id 数据源id
     */
    SqlSession getSession(final Integer id);

    /**
     * 通过id获取sessions
     *
     * @param id 数据源id
     */
    List<SqlSession> getSessionBySrcId(Integer id);

    boolean hasThePool(Integer id);

    /**
     * 获得sqlsession
     * mapperInfo结构为[(随意)，mapper[完整路径]
     *
     * @param connInfo   连接信息
     * @param mapperInfo mapper信息
     */
    List<SqlSession> buildSqlSession(Map<String, String> connInfo, Map<String, String> mapperInfo, Integer quantity);

    Map<Integer, LinkedList<SqlSession>> getPools();

    void closeSession(SqlSession session);

    boolean closeSqlSessionPool(Integer id);
}
