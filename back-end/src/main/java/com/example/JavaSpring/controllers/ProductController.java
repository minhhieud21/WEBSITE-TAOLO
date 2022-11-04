package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.util.Error;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping(path = "/api/v1/product") //localhost:8080/api/v1/product
@CrossOrigin(origins ="*")

public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ImageController imageController = new ImageController();



    // GET all : localhost:8080/api/v1/product/getAllProduct/?Type=0&page=1 
    @GetMapping("getAllProduct")
    ResponseEntity<ResponseObject>getAllProduct(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "1") int Type){
        if(Type > 1 || Type < 0){
            return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                    new ResponseObject(false,Error.DATA_REQUEST_ERROR_MESSAGE,"")
            );
        }
        Pageable paging = PageRequest.of(page,size);
        Page<ProductModel> check ;
        Map<String,String> object = new HashMap<String,String>();
        Map<String,Object> kq = new HashMap<String,Object>();
        if(Type < 0 || Type > 1){
            return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                    new ResponseObject(false,Error.DATA_REQUEST_ERROR_MESSAGE,"")
            );
        }
        if(Type == 1){
           check = productService.getAllProductUser(paging);
       }
       else {
           check = productService.getAllProduct(paging);
        }
        List<ProductModel> a =check.getContent();
        for(int i=0;i<a.size();i++) {
            if(imageController.getPathImageByID(a.get(i).getproId()) != null){
                object.put(a.get(i).getproId(),imageController.getPathImageByID(a.get(i).getproId()).getImgPath());
            }}
        kq.put("Product",a);
        kq.put("Image",object);
        if(check.isEmpty() == true){
            return ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,""));
        }
        else {
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,kq)); }
    }

    // GET by id: localhost:8080/api/v1/product/abc
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getProductById(@PathVariable("id") String id) {
        Optional<ProductModel> check = Optional.ofNullable(productService.getProductById(id));
        if (check.isPresent()== true ){
                Map<String,Object> kq = new HashMap<String,Object>();
                ProductModel a =check.get();
                kq.put("Product",a);
                if(imageController.getPathImage(id).isEmpty()==false){
                    kq.put("Image",imageController.getPathImage(id));}
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true, Error.OK_MESSAGE,kq)
                );}
        else{
                return ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
                        new ResponseObject(false, Error.NO_VALUE_BY_ID_MESSAGE,"")
                );}
    }

    //GET : localhost:8080/api/v1/product/getProduct?cateId=MBA&Type=0
    // Type = 0 lay theo binh thuong, Type = 1 lay theo a->z , Type = 2 lay theo z->a , Type = 3 lay gia thap den cao , Type = 4 lay gia cao den thap
    @GetMapping("/getProduct")
    ResponseEntity<ResponseObject> getProductByCateID(@RequestParam(required = false) String cateId,@RequestParam(required = false) int Type) {
        if( Type < 0 || Type > 1 || cateId.length()==0){
            return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                    new ResponseObject(false,Error.DATA_REQUEST_ERROR_MESSAGE,"")
            );
        }
        List<ProductModel> check = productService.getProductByCateID(cateId,Type);
        Map<String,String> object = new HashMap<String,String>();
        Map<String,Object> kq = new HashMap<String,Object>();
        if(check.isEmpty() ==true){
                return ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
                );}
        else {
            for(int i=0;i<check.size();i++) {
                if(imageController.getPathImageByID(check.get(i).getproId()) != null){
                    object.put(check.get(i).getproId(),imageController.getPathImageByID(check.get(i).getproId()).getImgPath());
                }};
            kq.put("Product",check);
            kq.put("Image",object);
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE, kq)
                );}
    }

    // POST : localhost:8080/api/v1/product/add         +    JSON(ProductModel)
    @PostMapping("/addProduct")
    ResponseEntity<ResponseObject> addnewProduct(@RequestParam("proId") String proId,@RequestParam("proName") String proName, @RequestParam("description")String description,@RequestParam("price") Long price, @RequestParam("cateId") String cateId,@RequestParam("color") String color,@RequestParam("quantity") int quantity,@RequestParam("warrantyMonth") int warrantyMonth,@RequestParam("image") MultipartFile[] image) throws IOException {
        Optional<ProductModel> check1 = Optional.ofNullable(productService.getProductById(proId));
        if(proId.length() == 0 ||proName.length() == 0 || description.length() == 0|| price <= 0 || cateId.length() == 0 || color.length() == 0 || warrantyMonth == 0){
            return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                    new ResponseObject(false,Error.DATA_REQUEST_ERROR_MESSAGE,"")
            );
        }
        if (check1.isPresent() != true) {
            ProductModel productModel = new ProductModel();
            productModel.setproId(proId);
            productModel.setProName(proName);
            productModel.setDescription(description);
            productModel.setCateId(cateId);
            productModel.setColor(color);
            productModel.setWarrantyMonth(warrantyMonth);
            productModel.setQuantity(quantity);
            productModel.setPrice(price);
            Pageable paging = PageRequest.of(0,300);
            Page<ProductModel> check = productService.getAllProduct(paging);
            List<ProductModel> list =check.getContent();
            int max = 0;
            for (int i=0;i<list.size();i++){
                if(max < list.get(i).get_id()){
                    max = list.get(i).get_id();
                }
            }
            productModel.set_id(max+1);
            if(image.length == 1 && !image[0].getOriginalFilename().equals("") || image.length > 1) {
                ResponseEntity<ResponseObject> aa = imageController.addImage(image, proId);
                if(aa.getStatusCodeValue()!=200){
                    return aa;
                }
            }
            productService.saveProduct(productModel);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE, "")
            );
        } else {
            return ResponseEntity.status(Error.DUPLICATE_ID).body(
                    new ResponseObject(false,Error.DUPLICATE_ID_MESSAGE,"")
            );
        }
    }


    //Ko dung den
    @DeleteMapping("/{proId}") /// Đang viết lõ nen để tạm đây
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable String proId){
        productService.deleteProduct(proId);
        return null;
//        if(check.isPresent() == true){
//                return ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject(true,"")
//                );}
//        else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject(false, "")
//                );}
    }

    // POST : localhost:8080/api/v1/product/statusHide?proId=abc
    @PostMapping("/changestatus")
    ResponseEntity<ResponseObject> changestatus(@RequestParam String proId,@RequestParam int status){
        if(status > 1 || status < 0 || proId.length()==0){
                return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                        new ResponseObject(false,Error.DATA_REQUEST_ERROR_MESSAGE,"")
                );
        }
        ProductModel productModel = productService.getProductById(proId);
        Optional<ProductModel> check = Optional.ofNullable(productModel);
        if(check.isPresent() != true ){
            return ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
                    new ResponseObject(false,Error.NO_VALUE_BY_ID_MESSAGE, "")
            );}
        else if(productModel.getStatus() == status) {
            return ResponseEntity.status(Error.FAIL_STATUS_CHANGE).body(
                new ResponseObject(false, Error.FAIL_STATUS_CHANGE_MESSAGE,"")
                );}
        else if(status == 0) {
            productService.statusHide(proId);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );
        }
        else {
            productService.statusShow(proId);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );
        }
    }

    // POST : localhost:8080/api/v1/product/setPrice?proId=abc&price=100
    @PostMapping("/setPrice")
    ResponseEntity<ResponseObject> setPrice(@RequestParam String proId,@RequestParam Long price){
        if(proId.length()==0){
            return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                    new ResponseObject(false,Error.DATA_REQUEST_ERROR_MESSAGE,"")
            );
        }
        ProductModel productModel = productService.getProductById(proId);
        Optional<ProductModel> check = Optional.ofNullable(productModel);
        if(check.isPresent() == true ){
            productService.updatePrice(proId,price);
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );}
        else {
            return ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
                    new ResponseObject(false, Error.NO_VALUE_BY_ID_MESSAGE,"")
            );}
    }

    // POST : localhost:8080/api/v1/product/updateProduct?proId=abc        + JSON(ProductModel)  // ko thay doi dc proId + status
    @PostMapping("updateProduct")
    ResponseEntity<ResponseObject> updateProduct(@RequestParam("proId") String proId,@RequestParam("proName") String proName, @RequestParam("description")String description,@RequestParam("price") Long price, @RequestParam("cateId") String cateId,@RequestParam("color") String color,@RequestParam("quantity") int quantity,@RequestParam("warrantyMonth") int warrantyMonth){
        if(proName.length() == 0 || description.length() == 0|| price <= 0 || cateId.length() == 0 || color.length() == 0 || warrantyMonth == 0){
            return ResponseEntity.status(Error.WRONG_ACCESS_RIGHTS).body(
                    new ResponseObject(false,Error.WRONG_ACCESS_RIGHTS_MESSAGE,"")
            );
        }
        ProductModel newProduct = new ProductModel();
        newProduct.setproId(proId);
        newProduct.setProName(proName);
        newProduct.setDescription(description);
        newProduct.setPrice(price);
        newProduct.setCateId(cateId);
        newProduct.setColor(color);
        newProduct.setWarrantyMonth(warrantyMonth);
        newProduct.setQuantity(quantity);
        ProductModel productModel = productService.getProductById(proId);
        if(productModel != null ){
            productService.updateProduct(newProduct,newProduct.getproId());
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE,"")
            );}
        else {
            return ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
                    new ResponseObject(false, Error.NO_VALUE_BY_ID_MESSAGE,"")
            );}
    }

    // GET : localhost:8080/api/v1/product/search/?type=1&text=Macbook
    @GetMapping("/search")
    ResponseEntity<ResponseObject>search(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "1") int type,@RequestParam(defaultValue = "") String text){
        if(type<0 || type>1){
            return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                    new ResponseObject(false,Error.DATA_REQUEST_ERROR_MESSAGE,"")
            );
        }
        Pageable paging = PageRequest.of(page,size);
        Page<ProductModel> check ;
        Map<String,String> object = new HashMap<String,String>();
        Map<String,Object> kq = new HashMap<String,Object>();
        if(type == 1){
            check = productService.searchProductUser(paging,text);
        }
        else {
            check = productService.searchProductAdmin(paging,text);
        }
        List<ProductModel> a =check.getContent();
        for(int i=0;i<a.size();i++) {
            if(imageController.getPathImageByID(a.get(i).getproId()) != null){
                object.put(a.get(i).getproId(),imageController.getPathImageByID(a.get(i).getproId()).getImgPath());
            }}
        kq.put("Product",a);
        kq.put("Image",object);
        if(check.isEmpty() == true){
            return ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,""));
        }
        else {
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE, kq)); }
    }
    // GET : localhost:8080/api/v1/product/searchUser?proName=abc
}