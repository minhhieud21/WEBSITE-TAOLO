package com.example.JavaSpring.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Blog")
public class BlogModel {
    @Id
    Long id;
    String title,content;
    String dateUpload;
    int like,disLike;

    public BlogModel(Long id, String title, String content,int like, int disLike, String dateUpload) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateUpload = dateUpload;
        this.like = like;
        this.disLike = disLike;
    }

    @Override
    public String toString() {
        return "BlogModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", dateUpload='" + dateUpload + '\'' +
                ", like=" + like +
                ", disLike=" + disLike +
                '}';
    }

    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(String dateUpload) {
        this.dateUpload = dateUpload;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDisLike() {
        return disLike;
    }

    public void setDisLike(int disLike) {
        this.disLike = disLike;
    }
}
