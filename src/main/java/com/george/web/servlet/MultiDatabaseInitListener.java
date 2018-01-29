package com.george.web.servlet;


import com.george.multidb.SqlSessionHelper;
import com.george.utils.jdbcUtils.JdbcDao;
import com.george.utils.jdbcUtils.JdbcUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by George on 2017/12/25.
 * <p>
 * web容器启动后的监听器(多数据源初始化)
 */
public class MultiDatabaseInitListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent context) {

    }

    public void contextInitialized(ServletContextEvent context) {
        System.out.println("==================Automatic Loading Start==================");
        // 上下文初始化执行
        try {
            JdbcUtil jdbcUtil = new JdbcUtil();
            List<Map<String, Object>> srcIds = JdbcDao.findResult(jdbcUtil.getConnection(),
                    "select id from db_src_info where status=1 and deleteFlag = 0", null);
            int count = 0;
            for (Map<String, Object> m : srcIds) {
                Integer id = (Integer) m.get("id");
                System.out.println("#####   Creating NO.[" + id + "] Database Src.");
                try {
                    SqlSessionHelper.createPool(id);
                    count++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("**  [" + count + "] databases created successfully!  **");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("==================Automatic Loading End==================");
    }
}
