package com.george;

import com.george.dao.entities.DBDetails;
import com.george.dao.mappers.DBDetailsMapper;
import com.george.multidb.DBSrcInfoHelper;
import com.george.multidb.SqlSessionHelper;
import com.george.utils.jdbcUtils.JdbcDao;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by George on 2017/12/12.
 */
public class CommonTest {
    @Test
    public void test() {
        /*Connection connection = SqlSessionHelper.getPoolConn(0);
        JdbcDao dao = new JdbcDao();
        try {
            ResultSet records = dao.getRecords(connection,"select * from db_src_info");
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
//        DBDetailsMapper mapper = SqlSessionHelper.getMapperInstance(DBDetailsMapper.class, 0);
//        List<DBDetails> data = mapper.getDBSrcConnInfo(null);

        DBSrcInfoHelper helper = new DBSrcInfoHelper();
        helper.getDBSrcConnDetail(1);
        System.out.println();
    }
}
