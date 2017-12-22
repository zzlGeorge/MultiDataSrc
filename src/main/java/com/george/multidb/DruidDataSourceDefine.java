package com.george.multidb;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by George on 2017/12/22.
 * mybatis使用第三方数据库连接池
 */
public class DruidDataSourceDefine extends DruidDataSourceFactory implements DataSourceFactory {
    protected Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public DataSource getDataSource() {
        try {
            return createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
