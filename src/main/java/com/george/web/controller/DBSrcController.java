package com.george.web.controller;

import com.george.dao.entities.DBDetailsEntity;
import com.george.dao.entities.DBSrcInfoEntity;
import com.george.service.DatabaseSrcService;
import com.george.utils.FilterData;
import com.george.web.ParamObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by George on 2018/1/3.
 */

@Controller
@RequestMapping(value = "/dbSrc")
public class DBSrcController {
    @Autowired
    private DatabaseSrcService databaseSrcService;

    /**
     * 数据源基本信息页面
     */
    @RequestMapping(value = "/dbSrcView", method = RequestMethod.GET)
    public String dbSrcViewPage() {
        return "page/dbSrcView";
    }

    /**
     * 数据源连接信息页面
     */
    @RequestMapping(value = "/dbSrcUrlView", method = RequestMethod.GET)
    public String dbSrcUrlViewPage() {
        return "page/dbSrcUrlView";
    }

    /**
     * 数据源名称获取
     */
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

    /**
     * 数据源基本信息
     */
    @RequestMapping(value = "/srcInfoView", method = RequestMethod.POST)
    @ResponseBody
    public Object srcInfoView(ParamObject param, DBSrcInfoEntity entity) {
        List<DBSrcInfoEntity> data = databaseSrcService.getSrcInfo(entity);
        return FilterData.splitPage(param, data);
    }

    /**
     * 数据源较完整信息（包含连接信息）
     */
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

    /**
     * 数据源较完整信息（包含连接信息）
     */
    @RequestMapping(value = "/getSrcUrlInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object getSrcUrlInfoGET(ParamObject param, DBDetailsEntity entity) {
        return getSrcUrlInfo(param, entity);
    }

    @RequestMapping(value = "/getSrcUrlInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object getSrcUrlInfo(ParamObject param, DBDetailsEntity entity) {
        List<DBDetailsEntity> data = databaseSrcService.getSrcUrlInfo(entity);
        return FilterData.splitPage(param, data);
    }

    /**
     * 获取某个数据库的连接信息
     * */
    @RequestMapping(value = "/getOneUrlInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object getSrcUrlInfoGET(DBDetailsEntity entity) {
        return getSrcUrlInfo(entity);
    }

    @RequestMapping(value = "/getOneUrlInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object getSrcUrlInfo(DBDetailsEntity entity) {
        List<DBDetailsEntity> data = databaseSrcService.getSrcUrlInfo(entity);
        ParamObject paramObject = new ParamObject();
        paramObject.setDataList(data);
        return paramObject;
    }
}
