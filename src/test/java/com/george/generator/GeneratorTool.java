package com.george.generator;

import com.george.dao.entities.DBDetails;
import com.george.general.Constants;
import com.george.multidb.SqlSessionHelper;
import com.george.utils.generators.mybatis.generator.MapperGenerator;
import com.george.utils.javaBeanCreater.BeanProcess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by xiam on 2015/6/23.
 *
 * @ContextConfiguration(locations = { "classpath*:/spring1.xml", "classpath*:/spring2.xml" })
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:freemarker.xml")
//@ContextConfiguration("classpath:freemarker.xml")
public class GeneratorTool {

    @Autowired
    private MapperGenerator mapperGenerator;

    /**
     * 生成mapper的java接口和xml文件
     */
    @Test
    public void generateMapperTools() {
        GeneratorMethods.generateMapper(mapperGenerator, DBDetails.class,
                "com.george.dao.mappers",
                "\\src\\main\\resources\\mappers");
        System.out.println("生成完毕。");
    }

    /**
     * javabean生成器
     */
    @Test
    public void generateJavaBeanFromDB() {
        Connection connection = SqlSessionHelper.getPoolConn(0);//指定数据库连接
        try {
            boolean res = BeanProcess.generateJavaBeanFromDB(Constants.ROOT_PATH + "/src/main/java", "com.george.dao.entities",
                    connection, "db_src_info", "DBSrcInfo");//生成javabean文件于指定位置
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}