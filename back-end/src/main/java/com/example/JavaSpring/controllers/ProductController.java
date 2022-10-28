package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.BlogModel;
import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/product") //localhost:8080/api/v1/product
@CrossOrigin(origins ="http://localhost:4200")

public class ProductController {

    @Autowired
    ProductService productService;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    // GET all : localhost:8080/api/v1/product
    @GetMapping("getAllProductAdmin")
    ResponseEntity<ResponseObject> getAllProductAdmin() {
        List<ProductModel> check = productService.getAllProduct();
        return check.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true, check)
                );
=======
    public ProductController() {
    }

    // GET all : localhost:8080/api/v1/product/getAllProduct/?Type=0&page=1
    @GetMapping("getAllProduct")
    ResponseEntity<ResponseObject>getAllProduct(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "1") int Type){
        Pageable paging = PageRequest.of(page,size);
        Page<ProductModel> check ;
       if(Type == 1){
           check = productService.getAllProductUser(paging);
       }
       else {
           check = productService.getAllProduct(paging);
       }
        if(check.isEmpty() == true){
            return ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,""));
        }
        else {
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE, check)); }
>>>>>>> Stashed changes
    }

    @GetMapping("getAllProductUser")
    ResponseEntity<ResponseObject> getAllProductUser() {
        List<ProductModel> kq = productService.getAllProduct();
        List<ProductModel> check =  new ArrayList<ProductModel>();
        for (int i = 0; i < kq.size();i++){
            if(kq.get(i).getStatus() == 1 ){
                check.add(kq.get(i));
            }
        }
        return check.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true, check)
                );
    }
    // GET by id: localhost:8080/api/v1/product/:id
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getProductById(@PathVariable("id") String id) {
        Optional<ProductModel> check = Optional.ofNullable(productService.getProductById(id));
        return check.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true, check)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                );
    }
<<<<<<< Updated upstream
    //localhost:8080/api/v1/product/getProduct?cateId=?
=======
=======
    public ProductController() {
    }

>>>>>>> Stashed changes
    ProductModel getProductByProID( String id) {
        return productService.getProductById(id);
    }

<<<<<<< Updated upstream

    //GET : localhost:8080/api/v1/product/getProduct?cateId=MBA&Type=0
    // Type = 0 lay theo binh thuong, Type = 1 lay theo a->z , Type = 2 lay theo z->a , Type = 3 lay gia thap den cao , Type = 4 lay gia cao den thap
>>>>>>> Stashed changes
    @GetMapping("/getProduct")
    ResponseEntity<ResponseObject> getProductByCateID(@RequestParam(required = false) String cateId) {
        List<ProductModel> check = productService.getProductByCateID(cateId);
        return check.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(false, "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(true, check)
                );
    }

    // POST : localhost:8080/api/v1/product/add
    @PostMapping("/add")
    ResponseEntity<ResponseObject> addnewProduct(@RequestBody ProductModel productModel) {
        Optional<ProductModel> check = Optional.ofNullable(productService.getProductById(productModel.getproId()));
        if (check.isPresent() == true) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, "")
            );
        } else {
            List<ProductModel> list = productService.getAllProduct();
            Long max = Long.valueOf(0);
            for (int i=0;i<list.size();i++){
                if(max < list.get(i).get_id()){
                    max = list.get(i).get_id();
                }
            }
            productModel.set_id(max+1);
            productService.saveProduct(productModel);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true, "")
            );
        }
    }

    @DeleteMapping("/{proId}") /// Đang viết lõ nen để tạm đây
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable String proId){
        productService.deleteProduct(proId);
        return null;
//        if(check.isPresent() == true){
//                return ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject(true,"")
//                );}
=======
    // GET all : localhost:8080/api/v1/product/getAllProduct/?Type=0&page=1
//    @GetMapping("getAllProduct")
//    ResponseEntity<ResponseObject>getAllProduct(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "1") int Type) {
//        Pageable paging = PageRequest.of(page, size);
//        Page<ProductModel> check;
//        if (Type == 1) {
//            check = productService.getAllProductUser(paging);
//        } else {
//            check = productService.getAllProduct(paging);
//        }
//        if (check.isEmpty() == true) {
//            return ResponseEntity.status(Error.LIST_EMPTY).body(
//                    new ResponseObject(false, Error.LIST_EMPTY_MESSAGE, check)
//            );
//        }
//    }
    // GET all : localhost:8080/api/v1/product
//    @GetMapping("getAllProductAdmin")
//    ResponseEntity<ResponseObject> getAllProductAdmin() {
//        Optional<List<ProductModel>> check = Optional.ofNullable(productService.getAllProduct());
//        return check.isEmpty() ?
//                ResponseEntity.status(Error.LIST_EMPTY).body(
//                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE, "")
//                ) :
//                ResponseEntity.status(Error.OK).body(
//                        new ResponseObject(true, Error.OK_MESSAGE, "")
//                );
//    }
//
//
//    // GET all : localhost:8080/api/v1/product/getAllProduct/?Type=0&page=1
//    @GetMapping("getAllProduct")
//    ResponseEntity<ResponseObject>getAllProduct(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "1") int Type){
//        Pageable paging = PageRequest.of(page,size);
//        Page<ProductModel> check ;
//       if(Type == 1){
//           check = productService.getAllProductUser(paging);
//       }
//       else {
//           check = productService.getAllProduct(paging);
//       }
//        if(check.isEmpty() == true){
//            return ResponseEntity.status(Error.LIST_EMPTY).body(
//                    new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,""));
//        }
//        else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject(false, "")
//            return ResponseEntity.status(Error.OK).body(
//                    new ResponseObject(true,Error.OK_MESSAGE, check)); }
//    }
//
//    @GetMapping("getAllProductUser")
//    ResponseEntity<ResponseObject> getAllProductUser() {
//        List<ProductModel> kq = productService.getAllProduct();
//        List<ProductModel> check =  new ArrayList<ProductModel>();
//        for (int i = 0; i < kq.size();i++){
//            if(kq.get(i).getStatus() == 1 ){
//                check.add(kq.get(i));
//            }
//        }
>>>>>>> Stashed changes
//        else {
//            return ResponseEntity.status(Error.OK).body(
//                    new ResponseObject(true,Error.OK_MESSAGE, check));
//        }
//    }
//
//    // GET by id: localhost:8080/api/v1/product/abc
//    @GetMapping("/{id}")
//    ResponseEntity<ResponseObject> getProductById(@PathVariable("id") String id) {
//        Optional<ProductModel> check = Optional.ofNullable(productService.getProductById(id));
//        return check.isPresent() ?
//                ResponseEntity.status(Error.OK).body(
//                        new ResponseObject(true, Error.OK_MESSAGE,check)
//                ) :
//                ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
//                        new ResponseObject(false, Error.NO_VALUE_BY_ID_MESSAGE,"")
//                );
//    }
//
//
//
//    //GET : localhost:8080/api/v1/product/getProduct?cateId=MBA&Type=0
//    // Type = 0 lay theo binh thuong, Type = 1 lay theo a->z , Type = 2 lay theo z->a , Type = 3 lay gia thap den cao , Type = 4 lay gia cao den thap
//    @GetMapping("/getProduct")
//    ResponseEntity<ResponseObject> getProductByCateID(@RequestParam(required = false) String cateId,@RequestParam(required = false) int Type) {
//        List<ProductModel> check = productService.getProductByCateID(cateId,Type);
//        return check.isEmpty() ?
//                ResponseEntity.status(Error.LIST_EMPTY).body(
//                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
//                ) :
//                ResponseEntity.status(Error.OK).body(
//                        new ResponseObject(true,Error.OK_MESSAGE, check)
//                );
//    }
//
//    // POST : localhost:8080/api/v1/product/add         +    JSON(ProductModel)
//    @PostMapping("/add")
//    ResponseEntity<ResponseObject> addnewProduct(@RequestBody ProductModel productModel) {
//        Optional<ProductModel> check = Optional.ofNullable(productService.getProductById(productModel.getproId()));
//        if (check.isPresent() == true) {
//            return ResponseEntity.status(Error.DUPLICATE_ID).body(
//                    new ResponseObject(false,Error.DUPLICATE_ID_MESSAGE, "")
//            );
//        } else {
//            productService.saveProduct(productModel);
//            return ResponseEntity.status(Error.OK).body(
//                    new ResponseObject(true,Error.OK_MESSAGE,"")
//            );
//        }
//    }
//
//
//    //Ko dung den
//    @DeleteMapping("/{proId}") /// Đang viết lõ nen để tạm đây
//    ResponseEntity<ResponseObject> deleteProduct(@PathVariable String proId){
//        productService.deleteProduct(proId);
//        return null;
////        if(check.isPresent() == true){
////                return ResponseEntity.status(HttpStatus.OK).body(
////                        new ResponseObject(true,"")
////                );}
////        else {
////                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
////                        new ResponseObject(false, "")
////                );}
//    }
//
//    // POST : localhost:8080/api/v1/product/changestatus?proId=d17866b0-ad70-4501-9ff6-5d46644dff01&status=1
//    @PostMapping("/changestatus")
//    ResponseEntity<ResponseObject> changestatus(@RequestParam String proId,@RequestParam int status){
//        ProductModel productModel = productService.getProductById(proId);
//        Optional<ProductModel> check = Optional.ofNullable(productModel);
//        if(check.isPresent() != true ){
//            return ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
//                    new ResponseObject(false,Error.NO_VALUE_BY_ID_MESSAGE, "")
//            );}
//        else if(productModel.getStatus() == status) {
//            return ResponseEntity.status(Error.FAIL_STATUS_CHANGE).body(
//                new ResponseObject(false, Error.FAIL_STATUS_CHANGE_MESSAGE,"")
//                );}
<<<<<<< Updated upstream
    }

    @PostMapping("/statusHide")
    ResponseEntity<ResponseObject> statusHide(@RequestParam(required = false) String proId){
        ProductModel productModel = productService.getProductById(proId);
        Optional<ProductModel> check = Optional.ofNullable(productModel);
        if(check.isPresent() == true && productModel.getStatus() != 0){
            productService.statusHide(proId);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(true,"")
                );}
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(false, "")
                );}
    }

    @PostMapping("/statusShow")
    ResponseEntity<ResponseObject> statusShow(@RequestParam(required = false) String proId){
        ProductModel productModel = productService.getProductById(proId);
        Optional<ProductModel> check = Optional.ofNullable(productModel);
        if(check.isPresent() == true && productModel.getStatus() != 1){
            productService.statusShow(proId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true,"")
            );}
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, "")
            );}
    }

    @PostMapping("/setPrice")
    ResponseEntity<ResponseObject> setPrice(@RequestParam(required = false) String proId,@RequestParam(required = false) Long price){
        ProductModel productModel = productService.getProductById(proId);
        Optional<ProductModel> check = Optional.ofNullable(productModel);
        if(check.isPresent() == true && productModel.getPrice() != price){
            productService.updatePrice(proId,price);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true,"")
            );}
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, "")
            );}
    }
=======
//        else if(status == 0) {
//            productService.statusHide(proId);
//            return ResponseEntity.status(Error.OK).body(
//                    new ResponseObject(true,Error.OK_MESSAGE,"")
//            );
//        }
//        else {
//            productService.statusShow(proId);
//            return ResponseEntity.status(Error.OK).body(
//                    new ResponseObject(true,Error.OK_MESSAGE,"")
//            );
//        }
//    }
//
//    // POST : localhost:8080/api/v1/product/setPrice?proId=abc&price=100
//    @PostMapping("/setPrice")
//    ResponseEntity<ResponseObject> setPrice(@RequestParam String proId,@RequestParam Long price){
//        ProductModel productModel = productService.getProductById(proId);
//        Optional<ProductModel> check = Optional.ofNullable(productModel);
//        if(check.isPresent() == true ){
//            productService.updatePrice(proId,price);
//            return ResponseEntity.status(Error.OK).body(
//                    new ResponseObject(true,Error.OK_MESSAGE,"")
//            );}
//        else {
//            return ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
//                    new ResponseObject(false, Error.NO_VALUE_BY_ID_MESSAGE,"")
//            );}
//    }
//
//    // POST : localhost:8080/api/v1/product/updateProduct?proId=abc        + JSON(ProductModel)  // ko thay doi dc proId + status
//    @PostMapping("updateProduct")
//    ResponseEntity<ResponseObject> updateProduct(@RequestBody ProductModel newProduct){
//        ProductModel productModel = productService.getProductById(newProduct.getproId());
//        Optional<ProductModel> check = Optional.ofNullable(productModel);
//        if(check.isPresent() == true ){
//            productService.updateProduct(newProduct,newProduct.getproId());
//            return ResponseEntity.status(Error.OK).body(
//                    new ResponseObject(true,Error.OK_MESSAGE,"")
//            );}
//        else {
//            return ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
//                    new ResponseObject(false, Error.NO_VALUE_BY_ID_MESSAGE,"")
//            );}
//    }
//
//    // GET : localhost:8080/api/v1/product/search/?type=1&text=mac&page=1
//    @GetMapping("/search")
//    ResponseEntity<ResponseObject>search(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "1") int type,@RequestParam(defaultValue = "") String text){
//        Pageable paging = PageRequest.of(page,size);
//        Page<ProductModel> check ;
//        if(type == 1){
//            check = productService.searchProductUser(paging,text);
//        }
//        else {
//            check = productService.searchProductAdmin(paging,text);
//        }
//        if(check.isEmpty() == true){
//            return ResponseEntity.status(Error.LIST_EMPTY).body(
//                    new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,""));
//        }
//        else {
//            return ResponseEntity.status(Error.OK).body(
//                    new ResponseObject(true,Error.OK_MESSAGE, check)); }
//    }
//    // GET : localhost:8080/api/v1/product/searchUser?proName=abc
>>>>>>> Stashed changes
}