package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.ImageModel;
import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.ImageService;
import com.example.JavaSpring.service.ProductService;
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
    @Autowired
    ProductService productService;

    public ImageController(){};
    @GetMapping("/{id}")
    ImageModel getPathImageByID(@PathVariable("id")String id) {
        ImageModel check = imageService.getPathImageByID(id);
        if (check == null){
            List<ImageModel> temp = imageService.getPathImage(id);
            if(temp.isEmpty() == true){
                return null;
            }
            return temp.get(0);
        }
        else{
            return check;}
    }

    List<String> getPathImage(String id) {
        List<String> check = new ArrayList<>();
        ImageModel a = this.getPathImageByID(id);
        if(a != null){
            check.add(a.getImgPath());}
        List<ImageModel> temp = imageService.getPathImage(id);
        if(temp.isEmpty() == false){
            for(int i = 0;i<temp.size();i++){
                if(a.getImgPath().equals(temp.get(i).getImgPath()) == false){
                    check.add(temp.get(i).getImgPath());}
            }}
        return check;
    }
    
    @GetMapping("/getAllImage")
    List<ImageModel> getAllImage (){
        return imageService.getAllImage();
    }

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addImage(@RequestParam("image") MultipartFile[] image,@RequestParam("proID") String proID) throws IOException {
        if(image.length == 1 && image[0].getOriginalFilename().equals("") == true ) {
            return ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false,Error.LIST_EMPTY_MESSAGE,"")
            );
          }
        ProductModel a = productService.getProductById(proID);
        if (a == null){
            return ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false,"Ma san pham khong ton tai","")
            );
        }
        List<String> temp =  getPathImage(proID);
        for (int i = 0; i < temp.size(); i++){
            for(int j = 0 ; j < image.length;j++){
                if(temp.get(i).equals(StringUtils.cleanPath(image[j].getOriginalFilename()))){
                    return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                            new ResponseObject(false,"Anh dang them da ton tai","")
                    );
                }
            }
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
            imageService.saveImage(fileName,proID);
            imageService.FileUpload(uploadDir,fileName,image[i]);
        }
        return ResponseEntity.status(Error.OK).body(
                new ResponseObject(true,Error.OK_MESSAGE,"")
        );
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
                    new ResponseObject(false,"Ma san pham khong ton tai", "")
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
            List<String> temp =  getPathImage(proID);
            if(temp.size() >= 1 || temp.isEmpty() == true ){
                return ResponseEntity.status(Error.FAIL).body(
                        new ResponseObject(false,"San pham hien con 1 anh hoac ma san pham sai",""));
            }
            ImageModel imageModel = imageService.getImagebyName(listNameImagess[i],proID);
                File myObj = new File("src"+imageModel.getImgPath());
                myObj.delete();
                imageService.deleteImage(imageModel.get_id());
        }
        return ResponseEntity.status(Error.OK).body(
                new ResponseObject(true,Error.OK_MESSAGE,"")
        );
    }
}
