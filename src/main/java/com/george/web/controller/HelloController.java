package com.george.web.controller;

import com.george.dao.entities.DBDetailsEntity;
import com.george.dao.entities.DBSrcInfoEntity;
import com.george.multidb.SqlSessionHelper;
import com.george.service.DatabaseSrcService;
import com.george.web.ParamObject;
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
//        Object o = SqlSessionHelper.getPools();
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

    @RequestMapping(value = "/dbSrcView", method = RequestMethod.GET)
    public String dbSrcViewPage() {
        return "page/dbSrcView";
    }




    @RequestMapping(value = "/baseSrcInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object baseSrcInfoGET(ParamObject param, DBSrcInfoEntity entity) {
        return baseSrcInfo(param, entity);
    }

    @RequestMapping(value = "/baseSrcInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object baseSrcInfo(ParamObject param, DBSrcInfoEntity entity) {
        List<DBSrcInfoEntity> data = databaseSrcService.getSrcInfo(entity);
        param.setDataList(data);
        return param;
    }

    @RequestMapping(value = "/getSrcInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object getSrcInfoGET(ParamObject param, DBDetailsEntity entity) {
        return getSrcInfo(param, entity);
    }

    @RequestMapping(value = "/getSrcInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object getSrcInfo(ParamObject param, DBDetailsEntity entity) {
        List<DBDetailsEntity> data = databaseSrcService.getSrcDetailsInfo(entity);
        param.setDataList(data);
        return param;
    }

}
