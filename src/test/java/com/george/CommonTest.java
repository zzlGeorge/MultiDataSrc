package com.george;

import com.alibaba.druid.pool.DruidDataSource;
import com.george.dao.mappers.DBSrcMappersEntityMapper;
import com.george.general.Constants;
import com.george.multidb.SqlSessionHelper;
import com.george.utils.jdbcUtils.JdbcDao;
import com.george.utils.jdbcUtils.JdbcUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * Created by George on 2017/12/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config/druid.xml")
public class CommonTest {

    @Autowired
    private DruidDataSource druidDataSource;
    @Test
    public void testCommon(){
        try {
            druidDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(Constants.ROOT_PATH);
    }

    @Test
    public void testGetConn() {
        Connection connection = SqlSessionHelper.getPoolConn(2);

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
            String url = "jdbc:oracle:thin:@192.168.20.11:1521:orcl";    //��ȡ����URL
            String user = "ecology"; //�����û���
            String password = "Medicalsystem0815"; //��������
            Connection con = DriverManager.getConnection(url, user, password); //��ȡ���ݿ�����
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
     * Druid���ӳز���
     * */
    @Test
    public void testDruidPools() {
        //������һ��ʵ��
        DruidDataSource dataSource = new DruidDataSource();
        //�������ݿ����ӵ�ַ
        dataSource.setUrl("jdbc:mysql://localhost:3306/multi_db");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        //���ó�ʼ����С
        dataSource.setInitialSize(1);
        //���������ݿ����Ӵ��е���С������
        dataSource.setMinIdle(1);
        //�������������
        dataSource.setMaxActive(20);
        //���û�ȡ���ӵ����ȴ�ʱ��
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
