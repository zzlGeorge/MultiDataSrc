package com.george.multidb.Impl;

import com.george.multidb.ISqlSessionPools;
import com.george.multidb.SqlSessionHelper;
import com.george.utils.FileUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by George on 2017/8/25.
 */
public class SqlSessionPools implements ISqlSessionPools {

    private static Logger log = Logger.getLogger(SqlSessionPools.class);
    private static final String CONF_FILE_NAME = "templet/mybatisConfig.xml";
    private static final String CONF_FILE_PATH;

    //模板字符串只读一次
    private static String templateContentFinal;

    //存放session工厂
    private static Map<Integer, SqlSessionFactory> sessionFactories = new HashMap<Integer, SqlSessionFactory>();

    //存放&读取session容器
    private static Map<Integer, SqlSession> sessionPools = new HashMap<Integer, SqlSession>();

    static {
        String path = SqlSessionPools.class.getClassLoader().getResource(CONF_FILE_NAME).getPath().substring(1);
        try {
            path = URLDecoder.decode(path, "utf-8");//解决空格目录乱码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CONF_FILE_PATH = path;
        templateContentFinal = FileUtils.readFileByLines(CONF_FILE_PATH);
    }

    public void createSessionPool(Integer id) {
//        closeSqlSessionPool(id);//先关闭池
        SqlSession sqlSessions;
        sqlSessions = getSessionBySrcId(id);
        if (sqlSessions == null) {
            log.error("创建失败!");
            return;
        }
        getPools().put(id, sqlSessions);
    }

    /**
     * 创建本地池
     * 注意 ！ 本地池定义为 0  号池
     */
    public void createLocalPool() {
        buildFactory(0, null, null);
        SqlSessionFactory sessionFactory = getSqlSessionFactory(0);
        SqlSession sqlSession = new SqlSessionTemplate(sessionFactory);
        //将本地池也加入   【0】号池为本地池
        getPools().put(0, sqlSession);
    }

    public SqlSession getSession(Integer id) {
        if (getPools().get(id) == null)
            createSessionPool(id);

        return getPools().get(id);//spring 中包裹的SqlSession类，线程安全
//        return SqlSessionManager.newInstance(sessionPools.getSqlSessionFactory(id));
    }

    public SqlSessionFactory getSqlSessionFactory(Integer id) {
        if (id == null) return null;
        SqlSessionFactory factory = getSessionFactories().get(id);
        if (factory == null) {
            buildFactory(id, DBSrcInfoHelper.getInstance().getDBSrcConnDetail(id),
                    DBSrcInfoHelper.getInstance().getDBSrcMapperById(id));//创建工厂
            factory = getSessionFactories().get(id);
        }
        return factory;
    }

    /**
     * 获得sqlsession
     * mapperInfo结构为[(随意)，mapper[完整路径]
     *
     * @param connInfo   连接信息
     * @param mapperInfo mapper信息
     * @Param id 数据库id
     */
    private synchronized SqlSession buildSqlSession(Integer id, Map<String, String> connInfo,
                                                    Map<String, String> mapperInfo) {
        SqlSession sqlSession;
        if (sessionFactories.get(id) == null) {
            buildFactory(id, connInfo, mapperInfo);
        }
        SqlSessionFactory sessionFactory = sessionFactories.get(id);
        sqlSession = new SqlSessionTemplate(sessionFactory);
        return sqlSession;
    }

    private synchronized void buildFactory(Integer id, Map<String, String> connInfo,
                                           Map<String, String> mapperInfo) {

        SqlSessionFactory sessionFactory;
        if (id == 0) {//本地创建
            String resource = "mybatis-config-local.xml";
            InputStream is = SqlSessionPools.class.getClassLoader().getResourceAsStream(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(is);
        } else {
            String templateContent = templateContentFinal;

            if (connInfo != null && connInfo.size() != 0 /*&& mapperInfo.size() > 0*/) {
                templateContent = templateContent.replaceAll("#driver#", connInfo.get("driver"));
                templateContent = templateContent.replaceAll("#url#", connInfo.get("url"));
                templateContent = templateContent.replaceAll("#username#", connInfo.get("username"));
                templateContent = templateContent.replaceAll("#password#", connInfo.get("password"));

                StringBuilder mapperContent = new StringBuilder("");
                Iterator iterator = mapperInfo.values().iterator();
                while (iterator.hasNext()) {
                    String content = iterator.next().toString();
                    String str = "<mapper resource=\"" + content + "\"/> \n";
                    mapperContent.append(str);
                }
                templateContent = templateContent.replaceAll("#mapper#", mapperContent.toString());
            } else {
                log.error("连接信息不正确或不能为空！");
                throw new RuntimeException("连接信息不正确或不能为空！");
            }
            InputStream is = new ByteArrayInputStream(templateContent.getBytes());
            sessionFactory = new SqlSessionFactoryBuilder()
                    .build(is);
        }
        sessionFactories.put(id, sessionFactory);//存放session工厂
    }

    public SqlSession getSessionBySrcId(Integer id) {
        SqlSession sqlSession = null;
        Map<String, String> connMap;
        try {
            connMap = DBSrcInfoHelper.getInstance().getDBSrcConnDetail(id);
            sqlSession = buildSqlSession(id, connMap, DBSrcInfoHelper.getInstance().getDBSrcMapperById(id));
        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return sqlSession;
    }

    public boolean hasThePool(Integer id) {
        if (id == null || getPools().get(id) == null)
            return false;
        return true;
    }

    public Map<Integer, SqlSession> getPools() {
        return sessionPools;
    }

    public Map<Integer, SqlSessionFactory> getSessionFactories() {
        return sessionFactories;
    }

//    public void closeSession(SqlSession session) {
//        if (session != null) {
//            try {
//                session.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public boolean closeSqlSessionPool(Integer id) {
//        SqlSession sessions = getPools().get(id);
//        if (sessions != null) {
//            sessions.close();
//        }
//        return true;
//    }
}
