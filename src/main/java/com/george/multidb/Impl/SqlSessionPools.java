package com.george.multidb.Impl;

import com.george.multidb.ISqlSessionPools;
import com.george.multidb.SqlSessionHelper;
import com.george.utils.FileUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

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

    public static final int SESSION_INIT_NUM = 10;//每个连接的连接数量配置
    private static final int PURSE_TIME = 1000;//停顿时间

    //存放&读取session容器
    private static Map<Integer, LinkedList<SqlSession>> sessionPools = new HashMap<Integer, LinkedList<SqlSession>>();

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
        closeSqlSessionPool(id);//先关闭池
        List<SqlSession> sqlSessions;
        sqlSessions = getSessionBySrcId(id);
        if (sqlSessions == null) {
            log.error("创建失败!");
            return;
        }
        getPools().put(id, (LinkedList<SqlSession>) sqlSessions);
    }


    public synchronized SqlSession getSession(final Integer id) {
        //判断有没有这个连接池
        synchronized (this) {
            if (!hasThePool(id)) {//...........不加锁可能创建多个池
                if (id == 0)//本地库创建方式
                    createLocalPool();
                else
                    createSessionPool(id);
            }
        }
//        createSessionPool(id);
        //获取id号连接池
        final LinkedList<SqlSession> sessionPool = getPools().get(id);

        synchronized (this) {//同步取session
            while (sessionPool.size() == 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": " + "----------------等待" + id + "号sqlSessions数据库连接池-------------");
                    Thread.sleep(PURSE_TIME);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (sessionPool.size() > 0) {//池内连接还有
                final SqlSession session = sessionPool.removeFirst();
//                System.out.println(Thread.currentThread().getName() + ": " + id + "号sqlSessions连接池【取出】，现在大小是" + sessionPool.size());
                //返回SqlSession对象的代理对象
                return (SqlSession) Proxy.newProxyInstance(SqlSessionPools.class.getClassLoader(), session.getClass().getInterfaces(), new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        synchronized (sessionPool) {//同步将session放回池中
                            if (!method.getName().equals("close")) {
                                return method.invoke(session, args);
                            } else {
                                //如果调用的是SqlSession对象的close方法，就把session还给数据库连接池
                                sessionPool.add(session);
//                                System.out.println(Thread.currentThread().getName() + ": " + id + "号sqlSessions连接池【放回】，现在大小为" + sessionPool.size());
                                return null;
                            }
                        }
                    }
                });
            } else {//池内连接没有
                throw new RuntimeException("对不起，数据库忙");
            }
        }
    }

    public synchronized List<SqlSession> buildSqlSession(Map<String, String> connInfo, Map<String, String> mapperInfo, Integer quantity) {
        List<SqlSession> sqlSessions = new LinkedList<SqlSession>();
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
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
                .build(is);
//        sqlSession = sessionFactory.openSession();
        for (int i = 0; i < quantity; i++) {
            SqlSession sqlSession = sessionFactory.openSession(true);
            sqlSessions.add(sqlSession);
        }
        return sqlSessions;
    }

    public List<SqlSession> getSessionBySrcId(Integer id) {
        List<SqlSession> sessions = null;
        Map<String, String> connMap;
        try {
            connMap = DBSrcInfoHelper.getInstance().getDBSrcConnDetail(id);
            sessions = buildSqlSession(connMap, DBSrcInfoHelper.getInstance().getDBSrcMapperById(id), SESSION_INIT_NUM);
        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return sessions;
    }

    public boolean hasThePool(Integer id) {
        if (id == null || getPools().get(id) == null)
            return false;
        return true;
    }

    public Map<Integer, LinkedList<SqlSession>> getPools() {
        return sessionPools;
    }


    public void closeSession(SqlSession session) {
        if (session != null) {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean closeSqlSessionPool(Integer id) {
        LinkedList<SqlSession> sessions = getPools().get(id);
        if (sessions != null && sessions.size() > 0) {
            for (int i = 0; i < sessions.size(); i++) {
                sessions.get(i).close();
            }
        }
        return true;
    }

    /**
     * 创建本地池
     * 注意 ！ 本地池定义为 0  号池
     */
    public void createLocalPool() {
        LinkedList<SqlSession> localSessions = SqlSessionHelper.getPools().get(0);
        if (localSessions != null && localSessions.size() > 0) {
            for (int i = 0; i < localSessions.size(); i++) {//关闭本地池
                localSessions.get(i).close();
            }
        }
        String resource = "mybatis-config-local.xml";
        InputStream is = SqlSessionPools.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        LinkedList<SqlSession> sessions = new LinkedList<SqlSession>();
        for (int i = 0; i < SqlSessionPools.SESSION_INIT_NUM; i++) {
//            SqlSession session = sessionFactory.openSession();   //默认手动提交
            SqlSession session = sessionFactory.openSession(true);//自动提交
            if (session == null) {
                throw new RuntimeException("无法获取本地sqlsession对象！");
            }
            sessions.add(session);
        }
        //将本地池也加入   【0】号池为本地池
        SqlSessionHelper.getPools().put(0, sessions);
    }
}
