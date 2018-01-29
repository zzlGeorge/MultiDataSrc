package com.george.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.george.dao.entities.DBDetailsEntity;
import com.george.dao.entities.DBSrcInfoEntity;
import com.george.dao.entities.DBSrcMappersEntity;
import com.george.service.DatabaseSrcService;
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

    @RequestMapping(value = "/getMapperDbUser", method = RequestMethod.GET)
    @ResponseBody
    public Object getMapperDbUserGET(ParamObject param, Integer mapperId) {
        return getMapperDbUser(param, mapperId);
    }

    @RequestMapping(value = "/getMapperDbUser", method = RequestMethod.POST)
    @ResponseBody
    public Object getMapperDbUser(ParamObject param, Integer mapperId) {
        if (mapperId == null) {
            param.setResult(false);
            param.setMessage("mapperId未指定");
            param.setCode(-1);
            return param;
        }
        List<DBSrcInfoEntity> data = databaseSrcService.getMapperDbUser(mapperId);
        return FilterData.splitPage(param, data);
    }


    @RequestMapping(value = "/saveDbSrc", method = RequestMethod.POST)
    @ResponseBody
    public Object saveDbSrc(ParamObject param, DBSrcInfoEntity entity) {
        boolean result = databaseSrcService.saveDbSrc(entity);
        if (!result) {
            param.setResult(false);
            param.setMessage("添加数据库失败！");
        } else {
            param.setMessage("添加数据库成功！");
        }
        return param;
    }

    @RequestMapping(value = "/deleteDbSrc", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteDbSrc(ParamObject param, Integer[] ids) {
        boolean result = databaseSrcService.deleteDbSrc(ids);
        if (!result) {
            param.setResult(false);
            param.setMessage("删除数据库失败！");
        } else {
            param.setMessage("删除数据库成功！");
        }
        return param;
    }

    @RequestMapping(value = "/updateDbSrc", method = RequestMethod.POST)
    @ResponseBody
    public Object updateDbSrc(ParamObject param, String entityString) {
        DBSrcInfoEntity entity = JSONObject.parseObject(entityString, DBSrcInfoEntity.class);
        entity.setUpdateTime(new Date());
        boolean result = databaseSrcService.updateDbSrc(entity);
        if (!result) {
            param.setResult(false);
            param.setMessage("更新该数据库失败！");
        } else {
            param.setMessage("更新该数据库成功！");
        }
        return param;
    }
}
