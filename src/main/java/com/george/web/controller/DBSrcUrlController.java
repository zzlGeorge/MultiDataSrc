package com.george.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.george.dao.entities.DBDetailsEntity;
import com.george.service.DatabaseSrcService;
import com.george.service.DatabaseSrcUrlService;
import com.george.utils.FilterData;
import com.george.web.ParamObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by George on 2018/1/29.
 */
@Controller
@RequestMapping(value = "/dbSrc")
public class DBSrcUrlController {


    @Autowired
    private DatabaseSrcUrlService databaseSrcUrlService;

    /**
     * 数据源连接信息页面
     */
    @RequestMapping(value = "/dbSrcUrlView", method = RequestMethod.GET)
    public String dbSrcUrlViewPage() {
        return "page/dbSrcUrlView";
    }


    /**
     * 数据源较完整信息（包含连接信息）
     */
    @RequestMapping(value = "/getSrcInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getSrcInfo(ParamObject param, DBDetailsEntity entity) {
        List<DBDetailsEntity> data = databaseSrcUrlService.getSrcDetailsInfo(entity);
        param.setDataList(data);
        return param;
    }

    /**
     * 数据源较完整信息（包含连接信息）
     */
    @RequestMapping(value = "/getSrcUrlInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getSrcUrlInfo(ParamObject param, DBDetailsEntity entity) {
        List<DBDetailsEntity> data = databaseSrcUrlService.getSrcUrlInfo(entity);
        return FilterData.splitPage(param, data);
    }

    /**
     * 获取所有数据库服务链接的信息
     */
    @RequestMapping(value = "/getAllSrcUrlInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllSrcUrlInfo(ParamObject param, DBDetailsEntity entity) {
        List<DBDetailsEntity> data = databaseSrcUrlService.getSrcUrlInfo(entity);
        param.setDataList(data);
        return param;
    }

    /**
     * 获取某个数据库的连接信息
     */
    @RequestMapping(value = "/getOneUrlInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getSrcUrlInfo(DBDetailsEntity entity) {
        List<DBDetailsEntity> data = databaseSrcUrlService.getSrcUrlInfo(entity);
        ParamObject paramObject = new ParamObject();
        paramObject.setDataList(data);
        return paramObject;
    }

    @RequestMapping(value = "/saveDbUrlSrc", method = RequestMethod.POST)
    @ResponseBody
    public Object saveDbUrlSrc(ParamObject param, DBDetailsEntity entity) {
        boolean result = databaseSrcUrlService.saveDbUrlSrc(entity);
        if (!result) {
            param.setResult(false);
            param.setMessage("添加数据库服务信息失败！");
        } else {
            param.setMessage("添加数据库服务信息成功！");
        }
        return param;
    }

    @RequestMapping(value = "/deleteDbUrlSrc", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteDbUrlSrc(ParamObject param, Integer[] ids) {
        boolean result = databaseSrcUrlService.deleteDbUrlSrc(ids);
        if (!result) {
            param.setResult(false);
            param.setMessage("删除数据库服务失败！");
        } else {
            param.setMessage("删除数据库服务成功！");
        }
        return param;
    }

    @RequestMapping(value = "/updateDbUrlSrc", method = RequestMethod.POST)
    @ResponseBody
    public Object updateDbUrlSrc(ParamObject param, String entityString) {
        DBDetailsEntity entity = JSONObject.parseObject(entityString, DBDetailsEntity.class);
        entity.setUpdateTime(new Date());
        boolean result = databaseSrcUrlService.updateDbUrlSrc(entity);
        if (!result) {
            param.setResult(false);
            param.setMessage("更新该数据库服务失败！");
        } else {
            param.setMessage("更新该数据库服务成功！");
        }
        return param;
    }
}
