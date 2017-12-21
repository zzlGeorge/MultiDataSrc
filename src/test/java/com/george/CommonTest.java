package com.george;

import com.george.multidb.SqlSessionHelper;
import com.george.utils.jdbcUtils.JdbcDao;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by George on 2017/12/12.
 */
public class CommonTest {
    @Test
    public void test() {
        Connection connection = SqlSessionHelper.getPoolConn(0);
        JdbcDao dao = new JdbcDao();
        try {
            ResultSet records = dao.getRecords(connection,"select * from db_src_info");
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
