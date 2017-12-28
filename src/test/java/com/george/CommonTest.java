package com.george;

import com.alibaba.druid.pool.DruidDataSource;
import com.george.dao.mappers.DBSrcMappersEntityMapper;
import com.george.general.Constants;
import com.george.multidb.Impl.DBSrcInfoHelper;
import com.george.multidb.SqlSessionHelper;
import com.george.utils.jdbcUtils.JdbcDao;
import com.george.utils.jdbcUtils.JdbcUtil;
import org.junit.Test;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * Created by George on 2017/12/12.
 */
public class CommonTest {

    @Test
    public void testCommon(){
        System.out.println(Constants.ROOT_PATH);
    }

    @Test
    public void testGetConn() {
        Connection connection = SqlSessionHelper.getPoolConn(1);

        try {
            JdbcUtil jdbcUtil = new JdbcUtil();
            List<Map<String, Object>> result = JdbcDao.findResult(jdbcUtil.getConnection(),
                    "select id from db_src_info", null);
            for (Map<String, Object> m : result) {
                System.out.println(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@192.168.20.11:1521:orcl";    //获取连接URL
            String user = "ecology"; //连接用户名
            String password = "Medicalsystem0815"; //连接密码
            Connection con = DriverManager.getConnection(url, user, password); //获取数据库连接
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }*/
    }

    @Test
    public void testGetMapper() {
        DBSrcMappersEntityMapper mapper = SqlSessionHelper.getMapperInstance(DBSrcMappersEntityMapper.class, 1);
        mapper.getSrcMappers(null);
        System.out.println();
    }

    /**
     * Druid连接池测试
     * */
    @Test
    public void testDruidPools() {
        //创建了一个实例
        DruidDataSource dataSource = new DruidDataSource();
        //设置数据库连接地址
        dataSource.setUrl("jdbc:mysql://localhost:3306/multi_db");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        //设置初始化大小
        dataSource.setInitialSize(1);
        //设置在数据库连接词中的最小连接数
        dataSource.setMinIdle(1);
        //设置最大连接数
        dataSource.setMaxActive(20);
        //设置获取连接的最大等待时间
        dataSource.setMaxWait(60000);

        dataSource.setTimeBetweenEvictionRunsMillis(60000);

        dataSource.setMinEvictableIdleTimeMillis(300000);

        dataSource.setValidationQuery("SELECT 'X'");

        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);

        dataSource.setPoolPreparedStatements(false);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);


        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("select count(*) from db_src_info");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
