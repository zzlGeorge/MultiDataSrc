package com.george.dao.entities;

import com.george.utils.generators.mybatis.generator.annotation.Table;

import java.util.Date;
import java.util.List;

@Table(name = "db_details")
public class DBDetailsEntity {
    private Integer id;
    private String serverName;
    private String driverName;
    private String url;
    private String userName;
    private String password;
    private Date updateTime;
    private Integer status;    //状态；1：启用  0：不启用
    private Integer deleteFlag; //删除标记
    private List<DBSrcInfoEntity> dataBases;//子表

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<DBSrcInfoEntity> getDataBases() {
        return dataBases;
    }

    public void setDataBases(List<DBSrcInfoEntity> dataBases) {
        this.dataBases = dataBases;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
