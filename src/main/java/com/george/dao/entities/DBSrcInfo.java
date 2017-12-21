package com.george.dao.entities;

import com.george.utils.generators.mybatis.generator.annotation.Table;

import java.util.Date;

@Table(name = "db_src_info")
public class DBSrcInfo {
    private Integer id;
    private String srcName;
    private Integer srcUrlId;
    private String srcDbName;    //数据库名
    private Integer type;    //类型
    private Integer status;    //状态；1：启用  0：不启用
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public Integer getSrcUrlId() {
        return srcUrlId;
    }

    public void setSrcUrlId(Integer srcUrlId) {
        this.srcUrlId = srcUrlId;
    }

    public String getSrcDbName() {
        return srcDbName;
    }

    public void setSrcDbName(String srcDbName) {
        this.srcDbName = srcDbName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
