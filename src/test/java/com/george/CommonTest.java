package com.george;

import com.george.dao.mappers.DBSrcMappersEntityMapper;
import com.george.multidb.Impl.DBSrcInfoHelper;
import com.george.multidb.SqlSessionHelper;
import com.george.utils.jdbcUtils.JdbcDao;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by George on 2017/12/12.
 */
public class CommonTest {
    @Test
    public void testGetConn() {
        Connection connection = SqlSessionHelper.getPoolConn(1);
        JdbcDao dao = new JdbcDao();
        try {
            ResultSet records = dao.getRecords(connection, "select * from db_src_info");
            System.out.println(records);
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
}
