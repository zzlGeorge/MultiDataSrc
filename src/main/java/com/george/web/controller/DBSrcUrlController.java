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
     * ����Դ������Ϣҳ��
     */
    @RequestMapping(value = "/dbSrcUrlView", method = RequestMethod.GET)
    public String dbSrcUrlViewPage() {
        return "page/dbSrcUrlView";
    }


    /**
     * ����Դ��������Ϣ������������Ϣ��
     */
    @RequestMapping(value = "/getSrcInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object getSrcInfoGET(ParamObject param, DBDetailsEntity entity) {
        return getSrcInfo(param, entity);
    }

    @RequestMapping(value = "/getSrcInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object getSrcInfo(ParamObject param, DBDetailsEntity entity) {
        List<DBDetailsEntity> data = databaseSrcUrlService.getSrcDetailsInfo(entity);
        param.setDataList(data);
        return param;
    }

    /**
     * ����Դ��������Ϣ������������Ϣ��
     */
    @RequestMapping(value = "/getSrcUrlInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object getSrcUrlInfoGET(ParamObject param, DBDetailsEntity entity) {
        return getSrcUrlInfo(param, entity);
    }

    @RequestMapping(value = "/getSrcUrlInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object getSrcUrlInfo(ParamObject param, DBDetailsEntity entity) {
        List<DBDetailsEntity> data = databaseSrcUrlService.getSrcUrlInfo(entity);
        return FilterData.splitPage(param, data);
    }

    /**
     * ��ȡ�������ݿ�������ӵ���Ϣ
     */
    @RequestMapping(value = "/getAllSrcUrlInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllSrcUrlInfo(ParamObject param, DBDetailsEntity entity) {
        List<DBDetailsEntity> data = databaseSrcUrlService.getSrcUrlInfo(entity);
        param.setDataList(data);
        return param;
    }

    /**
     * ��ȡĳ�����ݿ��������Ϣ
     */
    @RequestMapping(value = "/getOneUrlInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object getSrcUrlInfoGET(DBDetailsEntity entity) {
        return getSrcUrlInfo(entity);
    }

    @RequestMapping(value = "/getOneUrlInfo", method = RequestMethod.POST)
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
            param.setMessage("������ݿ������Ϣʧ�ܣ�");
        } else {
            param.setMessage("������ݿ������Ϣ�ɹ���");
        }
        return param;
    }

    @RequestMapping(value = "/deleteDbUrlSrc", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteDbUrlSrc(ParamObject param, Integer[] ids) {
        boolean result = databaseSrcUrlService.deleteDbUrlSrc(ids);
        if (!result) {
            param.setResult(false);
            param.setMessage("ɾ�����ݿ����ʧ�ܣ�");
        } else {
            param.setMessage("ɾ�����ݿ����ɹ���");
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
            param.setMessage("���¸����ݿ����ʧ�ܣ�");
        } else {
            param.setMessage("���¸����ݿ����ɹ���");
        }
        return param;
    }
}
