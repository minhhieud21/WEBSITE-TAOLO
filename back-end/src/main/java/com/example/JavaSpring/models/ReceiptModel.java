package com.example.JavaSpring.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Receipt")
public class ReceiptModel {
    @Id
    private String _id;
    @Field("recID")
    String recID;
    @Field("recDate")
    String recDate;
    @Field("recQuantity")
    int recQuantity;
    @Field("recCost")
    long recCost;
    @Field("status")
    int status;

    public ReceiptModel(String _id, String recID, String recDate, int recQuantity, long recCost, int status) {
        this._id = _id;
        this.recID = recID;
        this.recDate = recDate;
        this.recQuantity = recQuantity;
        this.recCost = recCost;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReceiptModel{" +
                "_id='" + _id + '\'' +
                ", recID='" + recID + '\'' +
                ", recDate='" + recDate + '\'' +
                ", recQuantity=" + recQuantity +
                ", recCost=" + recCost +
                ", status=" + status +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getRecID() {
        return recID;
    }

    public void setRecID(String recID) {
        this.recID = recID;
    }

    public String getRecDate() {
        return recDate;
    }

    public void setRecDate(String recDate) {
        this.recDate = recDate;
    }

    public int getRecQuantity() {
        return recQuantity;
    }

    public void setRecQuantity(int recQuantity) {
        this.recQuantity = recQuantity;
    }

    public long getRecCost() {
        return recCost;
    }

    public void setRecCost(long recCost) {
        this.recCost = recCost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
