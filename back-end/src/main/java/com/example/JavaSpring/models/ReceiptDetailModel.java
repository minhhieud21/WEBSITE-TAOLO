package com.example.JavaSpring.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("ReceiptDetail")
public class ReceiptDetailModel {
    @Id
    private String _id;
    @Field("recDID")
    String recDID;
    @Field("recID")
    String recID;
    @Field("proID")
    String proID;
    @Field("proName")
    String proName;
    @Field("description")
    String description;
    @Field("quantity")
    int quantity;
    @Field("price")
    long price;
    @Field("cost")
    long cost;
    @Field("totalCost")
    long totalCost;
    @Field("warrantyMonth")
    int warrantyMonth;

    public ReceiptDetailModel(String _id, String recDID, String recID, String proID, String proName, String description, int quantity, long price, long cost, long totalCost, int warrantyMonth) {
        this._id = _id;
        this.recDID = recDID;
        this.recID = recID;
        this.proID = proID;
        this.proName = proName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.cost = cost;
        this.totalCost = totalCost;
        this.warrantyMonth = warrantyMonth;
    }

    @Override
    public String toString() {
        return "ReceiptDetailModel{" +
                "_id='" + _id + '\'' +
                ", recDID='" + recDID + '\'' +
                ", recID='" + recID + '\'' +
                ", proID='" + proID + '\'' +
                ", proName='" + proName + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", cost=" + cost +
                ", totalCost=" + totalCost +
                ", warrantyMonth=" + warrantyMonth +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getRecDID() {
        return recDID;
    }

    public void setRecDID(String recDID) {
        this.recDID = recDID;
    }

    public String getRecID() {
        return recID;
    }

    public void setRecID(String recID) {
        this.recID = recID;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(long totalCost) {
        this.totalCost = totalCost;
    }

    public int getWarrantyMonth() {
        return warrantyMonth;
    }

    public void setWarrantyMonth(int warrantyMonth) {
        this.warrantyMonth = warrantyMonth;
    }
}