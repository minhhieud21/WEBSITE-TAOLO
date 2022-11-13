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
    @Field("accID")
    String accID;
    @Field("status")
    int status;
    @Field("address")
    String address;
    @Field("methodPay")
    String methodPay;

    public BillModel(String _id, String billID, Long totalCost, int toTalQuantity, String accID, int status, String address, String methodPay) {
        this._id = _id;
        this.billID = billID;
        this.totalCost = totalCost;
        this.toTalQuantity = toTalQuantity;
        this.accID = accID;
        this.status = status;
        this.address = address;
        this.methodPay = methodPay;
    }

    @Override
    public String toString() {
        return "BillModel{" +
                "_id='" + _id + '\'' +
                ", billID='" + billID + '\'' +
                ", totalCost=" + totalCost +
                ", toTalQuantity=" + toTalQuantity +
                ", accID='" + accID + '\'' +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", methodPay='" + methodPay + '\'' +
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

    public String getAccID() {
        return accID;
    }

    public void setAccID(String accID) {
        this.accID = accID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMethodPay() {
        return methodPay;
    }

    public void setMethodPay(String methodPay) {
        this.methodPay = methodPay;
    }
}
