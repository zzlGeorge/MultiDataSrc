package com.george.dao.mappers.multi;

import com.george.dao.entities.multi.SafetyStock;
import com.george.utils.generators.mybatis.annotation.MyBatisMapper;
import com.george.utils.generators.mybatis.mapper.CRUDMapper;
import com.george.web.paramObject.SafetyStockParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by George on 2018/1/11.
 */
@MyBatisMapper
public interface SafetyStockMapper extends CRUDMapper<SafetyStock, Long> {
    List<Object> getSafetyStockData();

    List<Object> getSafetyStockDataByEntity(@Param("entity") SafetyStockParam Param);
}
