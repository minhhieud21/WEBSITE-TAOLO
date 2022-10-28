package com.example.JavaSpring.models;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("BillDetail")
public class BillDetailModel {

    @Id
    private Long _id;
    @Field("billDID")
    String billDID;
    @Field("billID")
    String billID;
    @Field("proID")
    String proID;
    @Field("cost")
    long cost;
    @Field("quantity")
    int quantity;
    @Field("imeiCode")
    String imeiCode;
    @Field("warrantyStart")
    String warrantyStart;
    @Field("warrantyEnd")
    String warrantyEnd;

    public BillDetailModel(Long _id, String billDID, String billID, String proID, long cost, int quantity, String imeiCode, String warrantyStart, String warrantyEnd) {
        this._id = _id;
        this.billDID = billDID;
        this.billID = billID;
        this.proID = proID;
        this.cost = cost;
        this.quantity = quantity;
        this.imeiCode = imeiCode;
        this.warrantyStart = warrantyStart;
        this.warrantyEnd = warrantyEnd;
    }

    @Override
    public String toString() {
        return "BillDetailModel{" +
                "_id=" + _id +
                ", billDID='" + billDID + '\'' +
                ", billID='" + billID + '\'' +
                ", proID='" + proID + '\'' +
                ", cost=" + cost +
                ", quantity=" + quantity +
                ", imeiCode='" + imeiCode + '\'' +
                ", warrantyStart='" + warrantyStart + '\'' +
                ", warrantyEnd='" + warrantyEnd + '\'' +
                '}';
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getBillDID() {
        return billDID;
    }

    public void setBillDID(String billDID) {
        this.billDID = billDID;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImeiCode() {
        return imeiCode;
    }

    public void setImeiCode(String imeiCode) {
        this.imeiCode = imeiCode;
    }

    public String getWarrantyStart() {
        return warrantyStart;
    }

    public void setWarrantyStart(String warrantyStart) {
        this.warrantyStart = warrantyStart;
    }

    public String getWarrantyEnd() {
        return warrantyEnd;
    }

    public void setWarrantyEnd(String warrantyEnd) {
        this.warrantyEnd = warrantyEnd;
    }
}
