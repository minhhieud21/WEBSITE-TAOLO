package com.example.JavaSpring.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Image")

public class ImageModel {
    @Id
    private String _id;
    @Field("proID")
    String proID;
    @Field("imgPath")
    String imgPath;
    @Field("status")
    int status;

    public ImageModel(){}

    public ImageModel(String _id,String proID, String imgPath, int status) {
        this._id = _id;
        this.proID = proID;
        this.imgPath = imgPath;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "_id='" + _id + '\'' +
                ", proID='" + proID + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", status=" + status +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }



    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
