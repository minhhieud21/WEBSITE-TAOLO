package com.example.JavaSpring.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Product")
public class ProductModel {

    @Id
    private Long _id;
    @Field("proId")
    String proId;
    @Field("proName")
    String proName;
    @Field("description")
    String description;
    @Field("price")
    Long price;
    @Field("cateId")
    String cateId;
    @Field("color")
    String color;
    @Field("quantity")
    int quantity;
    @Field("warrantyMonth")
    int warrantyMonth;
    @Field("status")
    int status;

    public ProductModel(Long _id, String proId, String proName, String description, Long price, String cateId, String color, int quantity, int warrantyMonth, int status) {
        this._id = _id;
        this.proId = proId;
        this.proName = proName;
        this.description = description;
        this.price = price;
        this.cateId = cateId;
        this.color = color;
        this.quantity = quantity;
        this.warrantyMonth = warrantyMonth;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                ", proId='" + proId + '\'' +
                ", proName='" + proName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", cateId='" + cateId + '\'' +
                ", color='" + color + '\'' +
                ", quantity=" + quantity +
                ", warrantyMonth=" + warrantyMonth +
                ", status=" + status +
                '}';
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }



    public String getproId() {
        return proId;
    }

    public void setproId(String proId) {
        this.proId = proId;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getWarrantyMonth() {
        return warrantyMonth;
    }

    public void setWarrantyMonth(int warrantyMonth) {
        this.warrantyMonth = warrantyMonth;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
