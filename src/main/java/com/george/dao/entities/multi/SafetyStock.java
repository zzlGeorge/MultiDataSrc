package com.george.dao.entities.multi;

/**
 * Created by George on 2018/1/11.
 */
public class SafetyStock {
    private Long id;
    private String storeroomPosition;
    private String materialsType;
    private String materialID;
    private String materialsNum;
    private String materialsName;
    private String specifications;
    private String batchNum;
    private String unit;
    private String largeUnit;
    private String quantity;
    private String largeQuantity;
    private String stockQuantity;
    private String stockSlowLimit;
    private String expdate;
    private String safetyStock;
    private String stype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreroomPosition() {
        return storeroomPosition;
    }

    public void setStoreroomPosition(String storeroomPosition) {
        this.storeroomPosition = storeroomPosition;
    }

    public String getMaterialsType() {
        return materialsType;
    }

    public void setMaterialsType(String materialsType) {
        this.materialsType = materialsType;
    }

    public String getMaterialID() {
        return materialID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public String getMaterialsNum() {
        return materialsNum;
    }

    public void setMaterialsNum(String materialsNum) {
        this.materialsNum = materialsNum;
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLargeUnit() {
        return largeUnit;
    }

    public void setLargeUnit(String largeUnit) {
        this.largeUnit = largeUnit;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLargeQuantity() {
        return largeQuantity;
    }

    public void setLargeQuantity(String largeQuantity) {
        this.largeQuantity = largeQuantity;
    }

    public String getStockSlowLimit() {
        return stockSlowLimit;
    }

    public void setStockSlowLimit(String stockSlowLimit) {
        this.stockSlowLimit = stockSlowLimit;
    }

    public String getSafetyStock() {
        return safetyStock;
    }

    public void setSafetyStock(String safetyStock) {
        this.safetyStock = safetyStock;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(String stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }
}
