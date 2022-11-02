package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.ImageModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.ImageService;
import com.example.JavaSpring.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/image") //localhost:8080/api/v1/image
@CrossOrigin(origins ="*")
public class ImageController {
    @Autowired
    ImageService imageService;
    public ImageController(){};
    @GetMapping("/{id}")
    ImageModel getPathImageByID(@PathVariable("id")String id) {
        ImageModel check = imageService.getPathImageByID(id);
        if (check == null ){
            return null;
        }
        else
            return check;
    }

    List<String> getPathImage(String id) {
        List<String> check = new ArrayList<>();
        ImageModel a = imageService.getPathImageByID(id);
        if(a != null){
            check.add(imageService.getPathImageByID(id).getImgPath());}
        List<ImageModel> temp = imageService.getPathImage(id);
        if(temp.isEmpty() == false){
            for(int i = 0;i<temp.size();i++){
                check.add(temp.get(i).getImgPath());
            }}
        if (check == null ){
            return null;
        }
        else
            return check;
    }

    @GetMapping("/getAllImage")
    List<ImageModel> getAllImage (){
        List<ImageModel> imageModels = imageService.getAllImage();
        System.out.println(imageModels);
        return  imageModels;
    }

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addImage(@RequestParam("image") MultipartFile[] image,@RequestParam("proID") String proID) throws IOException {
        if(image.length == 1 && image[0].getOriginalFilename().equals("") == true ) {
            return ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false,Error.LIST_EMPTY_MESSAGE,"")
            );
          }
        for(int i = 0;i<image.length;i++){
                    if (StringUtils.cleanPath(image[i].getContentType()).equals("image/jpeg") || StringUtils.cleanPath(image[i].getContentType()).equals("image/png")){
                    }
                    else {
                        return ResponseEntity.status(Error.NOT_IMAGE).body(
                                new ResponseObject(false,Error.NOT_IMAGE_MESSAGE,"")
                        );
                    }
        }
        String uploadDir = "src/Image/"+proID+"/";
        for(int i = 0;i<image.length;i++){
            String fileName = StringUtils.cleanPath(image[i].getOriginalFilename());
            imageService.saveImage("/Image/"+proID+"/"+fileName,proID);
            imageService.FileUpload(uploadDir,fileName,image[i]);
        }
        return ResponseEntity.status(Error.OK).body(
                new ResponseObject(true,Error.OK_MESSAGE,"")
        );
    }

    @PostMapping("/changestatus")
        ResponseEntity<ResponseObject> changestatus(@RequestParam("nameImage") String nameImage,@RequestParam("proID") String proID,@RequestParam("status") int status){
        if((status > 1 || status < -1) || nameImage.length()==0||proID.length()==0){
            return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                    new ResponseObject(false,Error.DATA_REQUEST_ERROR_MESSAGE,"")
            );
        }
        if(status == 1){
            return ResponseEntity.status(Error.WRONG_ACCESS_RIGHTS).body(
                    new ResponseObject(false,Error.WRONG_ACCESS_RIGHTS_MESSAGE, "")
            );
        }
        ImageModel imageModel = imageService.getImagebyName(nameImage,proID);
        if( imageModel == null ){
            return ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
                    new ResponseObject(false,Error.NO_VALUE_BY_ID_MESSAGE, "")
            );}
        else if(imageModel.getStatus() == status) {
            return ResponseEntity.status(Error.FAIL_STATUS_CHANGE).body(
                    new ResponseObject(false, Error.FAIL_STATUS_CHANGE_MESSAGE,"")
            );}
        else {
            imageService.changestatus(imageModel,status);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );
        }
    }

    @PostMapping("/mainImage")
    ResponseEntity<ResponseObject> mainImage(@RequestParam("nameImage") String nameImage,@RequestParam("proID") String proID){
        if(nameImage.length()==0||proID.length()==0){
            return ResponseEntity.status(Error.WRONG_ACCESS_RIGHTS).body(
                    new ResponseObject(false,Error.WRONG_ACCESS_RIGHTS_MESSAGE,"")
            );
        }
        ImageModel imageModel = imageService.getImagebyName(nameImage,proID);
        if( imageModel == null ){
            return ResponseEntity.status(Error.WRONG_ACCESS_RIGHTS).body(
                    new ResponseObject(false,Error.WRONG_ACCESS_RIGHTS_MESSAGE, "")
            );}
        else if(imageModel.getStatus() == 1) {
            return ResponseEntity.status(Error.FAIL_STATUS_CHANGE).body(
                    new ResponseObject(false, Error.FAIL_STATUS_CHANGE_MESSAGE,"")
            );}
        else {
            ImageModel imageModel1 = this.getPathImageByID(proID);
            if(imageModel1 != null){
                imageService.changestatus(imageModel1,0);}
            imageService.changestatus(imageModel,1);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );
        }
    }

    @DeleteMapping("/deleteImage")
    ResponseEntity<ResponseObject> deleteCartDetail(@RequestParam("listNameImage") String[] listNameImagess,@RequestParam("proID") String proID) {
        if(listNameImagess.length==0||proID.length()==0){
            return ResponseEntity.status(Error.WRONG_ACCESS_RIGHTS).body(
                    new ResponseObject(false,Error.WRONG_ACCESS_RIGHTS_MESSAGE,"")
            );
        }
        for(int i =0 ;i< listNameImagess.length;i++){
            ImageModel imageModel = imageService.getImagebyName(listNameImagess[i],proID);
            if( imageModel != null){
                File myObj = new File("src"+imageModel.getImgPath());
                myObj.delete();
                imageService.deleteImage(imageModel.get_id());
            }
            else {
                return ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,""));
            }
        }
        return ResponseEntity.status(Error.OK).body(
                new ResponseObject(true,Error.OK_MESSAGE,"")
        );
    }
}
