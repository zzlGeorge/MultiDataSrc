package com.george.web.controller;

import com.george.dao.entities.DBSrcMappersEntity;
import com.george.service.DatabaseSrcMapperService;
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
 * Created by George on 2018/1/29.
 */

@Controller
@RequestMapping(value = "/dbSrc")
public class DBSrcMapperController {

    @Autowired
    private DatabaseSrcMapperService databaseSrcMapperService;

    /**
     * ����ԴMapper��Ϣҳ��
     */
    @RequestMapping(value = "/dbMappersView", method = RequestMethod.GET)
    public String dbMappersViewPage() {
        return "page/mappersMgr/mappersMgr";
    }

    /**
     * ����Դmappers�ֿ���Ϣ��ѯ
     */
    @RequestMapping(value = "/getSrcMappers", method = RequestMethod.GET)
    @ResponseBody
    public Object getSrcMappersGET(ParamObject param, DBSrcMappersEntity entity) {
        return getSrcMappers(param, entity);
    }

    @RequestMapping(value = "/getSrcMappers", method = RequestMethod.POST)
    @ResponseBody
    public Object getSrcMappers(ParamObject param, DBSrcMappersEntity entity) {
        List<DBSrcMappersEntity> data = databaseSrcMapperService.getSrcMappersInfo(entity);
        return FilterData.splitPage(param, data);
    }
}
