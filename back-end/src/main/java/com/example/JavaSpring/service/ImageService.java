package com.example.JavaSpring.service;

import com.example.JavaSpring.models.ImageModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    ImageModel getPathImageByID(String id);

    List<ImageModel> getPathImage(String id);

    List<ImageModel> getAllImage();

    void statusHide(String proId);

    void FileUpload(String dir, String name, MultipartFile multipartFile) throws IOException;


    void saveImage(String imgPath, String proID);

    ImageModel getImagebyName(String imgPath,String proID);

    void deleteImage(String imageModel);

    void changestatus(ImageModel imageModel, int Status);
}
