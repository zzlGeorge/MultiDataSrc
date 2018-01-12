package com.george.web.controller;

import com.george.dao.entities.DBDetailsEntity;
import com.george.dao.entities.DBSrcInfoEntity;
import com.george.dao.mappers.multi.SafetyStockMapper;
import com.george.multidb.SqlSessionHelper;
import com.george.service.DatabaseSrcService;
import com.george.utils.FilterData;
import com.george.web.ParamObject;
import com.george.web.paramObject.SafetyStockParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by George on 2017/12/11.
 */

@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @Autowired
    private DatabaseSrcService databaseSrcService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testPage() {
        Object o = SqlSessionHelper.getPools();
        SqlSessionHelper.getConnectionFromDataSource(2);
        SqlSessionHelper.getDataSource(2);
//        SqlSessionHelper.createPool(2);
        return "test";
    }

    @RequestMapping(value = "/innerDemo", method = RequestMethod.GET)
    public String innerDemoPage() {
//        Object o = SqlSessionHelper.getPools();
        return "page/inner";
    }

    @RequestMapping(value = "/inner2Demo", method = RequestMethod.GET)
    public String inner2DemoPage() {
//        Object o = SqlSessionHelper.getPools();
        return "page/inner2";
    }

    @RequestMapping(value = "/testFtl", method = RequestMethod.GET)
    public String testSrcState(Model model) {
        model.addAttribute("username", "John");
        return "template/testFtl";
    }

    @RequestMapping(value = "/safetyStockPage", method = RequestMethod.GET)
    public String safetyStockPage(Model model) {
        return "page/safetyStock";
    }

    @RequestMapping(value = "/multiThreadTest", method = RequestMethod.GET)
    public String multiThreadTestPage() {
        return "page/multiThreadTest";
    }

    /**
     * 物料库存——测试
     */
    @RequestMapping(value = "/safetyStock", method = RequestMethod.GET)
    @ResponseBody
    public Object safetyStockGet(SafetyStockParam pageInfo) {
        return safetyStock(pageInfo);
    }

    @RequestMapping(value = "/safetyStock", method = RequestMethod.POST)
    @ResponseBody
    public Object safetyStock(SafetyStockParam pageInfo) {
        if (pageInfo.getHospitalId() == null)
            return null;
        try {
            SafetyStockMapper mapper = SqlSessionHelper.getMapperInstance(SafetyStockMapper.class, pageInfo.getHospitalId());
            List<Object> data = mapper.getSafetyStockDataByEntity(pageInfo);
            pageInfo = (SafetyStockParam) FilterData.splitPage(pageInfo, data);
        } catch (Exception e) {
            String result = pageInfo.getHospitalId() + "号数据源没有配置SafetyStockMapper.xml文件";
            pageInfo.setMessage(result);
            pageInfo.setCode(-1);
        }

        //测试数据-------------------------------------------------------start
//        List<Object> testdata = pageInfo.getDataList();
//        Random r = new Random();
//        for (Object o : testdata) {
//            SafetyStock safetyStock = (SafetyStock) o;
//            if (safetyStock.getSafetyStock() == null) {
//                safetyStock.setSafetyStock(r.nextInt(10000) + "");
//            }
//            if (safetyStock.getStockSlowLimit() == null) {
//                safetyStock.setStockSlowLimit(r.nextInt(8000) + "");
//            }
//        }
//        pageInfo.setDataList(testdata);
        //测试数据-------------------------------------------------------end
        return pageInfo;
    }

}
