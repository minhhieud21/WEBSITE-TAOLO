package com.example.JavaSpring.models;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Bill")
public class BillModel {

    @Id
    private String _id;
    @Field("billID")
    String billID;
    @Field("totalCost")
    Long totalCost;
    @Field("totalQuantity")
    int toTalQuantity;
    @Field("cusID")
    String cusID;
    @Field("status")
    int status;

    public BillModel(String _id, String billID, Long totalCost, int toTalQuantity, String cusID, int status) {
        this._id = _id;
        this.billID = billID;
        this.totalCost = totalCost;
        this.toTalQuantity = toTalQuantity;
        this.cusID = cusID;
        this.status = status;
    }

    @Override
    public String toString() {
        return "BillModel{" +
                "_id=" + _id +
                ", billID='" + billID + '\'' +
                ", totalCost=" + totalCost +
                ", toTalQuantity=" + toTalQuantity +
                ", cusID='" + cusID + '\'' +
                ", status=" + status +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public int getToTalQuantity() {
        return toTalQuantity;
    }

    public void setToTalQuantity(int toTalQuantity) {
        this.toTalQuantity = toTalQuantity;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
