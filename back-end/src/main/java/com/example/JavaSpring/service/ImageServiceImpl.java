package com.example.JavaSpring.service;

import com.example.JavaSpring.models.ImageModel;
import com.example.JavaSpring.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    ImageRepository imageRepository;
    @Override
    public ImageModel getPathImageByID(String proID) {
        return imageRepository.getPathImageByID(proID,1);
    }
    @Override
    public List<ImageModel> getPathImage(String id) {
        return imageRepository.getPathImage(id,0);
    }
    @Override
    public List<ImageModel> getAllImage(){
        return imageRepository.findAll();
    }
    @Override
    public void statusHide(String proId){
        ImageModel productModel = imageRepository.getPathImageByID(proId,1);
        productModel.setStatus(0);
        imageRepository.save(productModel);
    }
    @Override
    public void FileUpload(String uploadDir, String fileName,MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    @Override
    public void update(ImageModel imageModel){
        imageRepository.save(imageModel);
    }

    @Override
    public void saveImage(String imgPath, String proID){
        ImageModel imageModel = new ImageModel();
        imageModel.setImgPath(imgPath);
        imageModel.setProID(proID);
        imageModel.setStatus(0);
        imageRepository.save(imageModel);
    }
    @Override
    public ImageModel getImagebyName(String imgPath,String proID){
        List<ImageModel> aa = imageRepository.getImagebyName(imgPath);
        for (int i =0;i<aa.size();i++){
            if(aa.get(i).getProID().equals(proID)){
                return aa.get(i);
            }
        }
        return null;
    }
    @Override
    public void deleteImage(String imageModel){
        imageRepository.deleteById(imageModel);
    }

    @Override
    public void changestatus(ImageModel imageModel, int Status){
        imageModel.setStatus(Status);
        imageRepository.save(imageModel);
    }
}
