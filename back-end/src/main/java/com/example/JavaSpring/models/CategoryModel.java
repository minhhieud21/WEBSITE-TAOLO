package com.example.JavaSpring.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Category")
public class CategoryModel {
    @Id
    String cateID;
    @Field("status")
    int status;
    @Field("cateName")
    String cateName;

    public CategoryModel(String cateID, int status, String cateName) {
        this.cateID = cateID;
        this.status = status;
        this.cateName = cateName;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
