package com.example.JavaSpring.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("Product")
public class ProductModel {
    String ProID,ProName,Desciption,Price,CateID,ImageID;
    int Quantity,WarrantyMonth,Status;

    public ProductModel(String proID, String proName, String desciption, String price, String cateID, String imageID, int quantity, int warrantyMonth, int status) {
        ProID = proID;
        ProName = proName;
        Desciption = desciption;
        Price = price;
        CateID = cateID;
        ImageID = imageID;
        Quantity = quantity;
        WarrantyMonth = warrantyMonth;
        Status = status;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "ProID='" + ProID + '\'' +
                ", ProName='" + ProName + '\'' +
                ", Desciption='" + Desciption + '\'' +
                ", Price='" + Price + '\'' +
                ", CateID='" + CateID + '\'' +
                ", ImageID='" + ImageID + '\'' +
                ", Quantity=" + Quantity +
                ", WarrantyMonth=" + WarrantyMonth +
                ", Status=" + Status +
                '}';
    }

    public String getProID() {
        return ProID;
    }

    public void setProID(String proID) {
        ProID = proID;
    }

    public String getProName() {
        return ProName;
    }

    public void setProName(String proName) {
        ProName = proName;
    }

    public String getDesciption() {
        return Desciption;
    }

    public void setDesciption(String desciption) {
        Desciption = desciption;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getCateID() {
        return CateID;
    }

    public void setCateID(String cateID) {
        CateID = cateID;
    }

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getWarrantyMonth() {
        return WarrantyMonth;
    }

    public void setWarrantyMonth(int warrantyMonth) {
        WarrantyMonth = warrantyMonth;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
