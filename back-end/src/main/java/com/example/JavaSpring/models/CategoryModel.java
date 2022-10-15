package com.example.JavaSpring.models;

import com.mongodb.lang.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Category")
public class CategoryModel {
    @Id
    private String _id;
    @Field("cateID")
    String cateID;
    @Field("status")
    int status;
    @Field("cateName")
    String cateName;

    public CategoryModel(String cateID, int status, String cateName) {
        this.cateID = cateID;
        this.cateName = cateName;
        this.status = status;

    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                ", cateID='" + cateID + '\'' +
                ", status=" + status +
                ", cateName='" + cateName + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
