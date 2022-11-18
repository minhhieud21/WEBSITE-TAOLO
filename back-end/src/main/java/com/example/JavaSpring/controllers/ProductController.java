package com.example.JavaSpring.controllers;

import com.example.JavaSpring.models.AccountModel;
import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.AccountService;
import com.example.JavaSpring.service.JwtService;
import com.example.JavaSpring.service.ProductService;
import com.example.JavaSpring.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    JwtService jwtService;

    @Autowired
    AccountService accountService;

    // GET all : localhost:8080/api/v1/product/getAllProduct/?Type=0&page=1 
    @GetMapping("getAllProduct")
    ResponseEntity<ResponseObject>getAllProduct(ServletRequest request, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "1") int type){
        if(type > 1 || type < 0){
            return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                    new ResponseObject(false,Error.DATA_REQUEST_ERROR_MESSAGE,"")
            );
        }
        Pageable paging = PageRequest.of(page,size);
        Page<ProductModel> check = null;
        List<Object> kq = new ArrayList<Object>();
        if(type == 1){
           check = productService.getAllProductUser(paging);
       }
       else {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String authToken = httpRequest.getHeader("authorization");
            String accID = null;
            if (jwtService.validateTokenLogin(authToken)) {
                accID = jwtService.getAccIDFromToken(authToken);
                AccountModel temp = accountService.getUserByAccID(accID);
                if (String.valueOf(accID.charAt(0)).equals("A")) {
                    check = productService.getAllProduct(paging);
                }
                else {
                    return ResponseEntity.status(Error.WRONG_ACCESS_RIGHTS).body(
                            new ResponseObject(false,"Can quyen Admin","")
                    );
                }
            }
            else {
                return ResponseEntity.status(Error.WRONG_ACCESS_RIGHTS).body(
                        new ResponseObject(false,"Can quyen Admin","")
                );
            }
        }
        List<ProductModel> a = check.getContent();
        for(int i=0;i<a.size();i++) {
            HashMap<String,String> object = new HashMap<String,String>();
            object.put("_id",String.valueOf(a.get(i).get_id()));
            object.put("proId",String.valueOf(a.get(i).getproId()));
            object.put("proName",String.valueOf(a.get(i).getProName()));
            object.put("description",String.valueOf(a.get(i).getDescription()));
            object.put("price",String.valueOf(a.get(i).getPrice()));
            object.put("cateId",String.valueOf(a.get(i).getCateId()));
            object.put("color",String.valueOf(a.get(i).getColor()));
            object.put("quantity",String.valueOf(a.get(i).getQuantity()));
            object.put("warrantyMonth",String.valueOf(a.get(i).getWarrantyMonth()));
            object.put("status",String.valueOf(a.get(i).getStatus()));
            if(imageController.getPathImageByID(a.get(i).getproId()) != null){
                object.put("image",imageController.getPathImageByID(a.get(i).getproId()).getImgPath());
            }
            kq.add(object);
        }
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
                HashMap<String,Object> object = new HashMap<String,Object>();
                ProductModel a = check.get();
                object.put("_id", (String.valueOf(a.get_id())));
                object.put("proId", (String.valueOf(a.getproId())));
                object.put("proName", (String.valueOf(a.getProName())));
                object.put("description", (String.valueOf(a.getDescription())));
                object.put("price", (String.valueOf(a.getPrice())));
                object.put("cateId", (String.valueOf(a.getCateId())));
                object.put("color", (String.valueOf(a.getColor())));
                object.put("quantity", (String.valueOf(a.getQuantity())));
                object.put("warrantyMonth", (String.valueOf(a.getWarrantyMonth())));
                object.put("status", (String.valueOf(a.getStatus())));
                object.put("Image",(imageController.getPathImage(id)));
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true, Error.OK_MESSAGE,object)
                );}
        else{
                return ResponseEntity.status(Error.NO_VALUE_BY_ID).body(
                        new ResponseObject(false, Error.NO_VALUE_BY_ID_MESSAGE,"")
                );}
    }

    //GET : localhost:8080/api/v1/product/getProduct?cateId=MBA&Type=0
    // Type = 0 lay theo binh thuong, Type = 1 lay theo a->z , Type = 2 lay theo z->a , Type = 3 lay gia thap den cao , Type = 4 lay gia cao den thap
    @GetMapping("/getProduct")
    ResponseEntity<ResponseObject> getProductByCateID(ServletRequest request,@RequestParam(required = false) String cateId,@RequestParam(defaultValue = "1") int type,@RequestParam(defaultValue = "0") int sort) {
        if( type < 0 || type > 1 || cateId.length()==0 || sort<0 || sort>4){
            return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                    new ResponseObject(false,Error.DATA_REQUEST_ERROR_MESSAGE,"")
            );
        }
        List<ProductModel> a;
        if(type == 0){
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                String authToken = httpRequest.getHeader("authorization");
                String accID = null;
                if (jwtService.validateTokenLogin(authToken)) {
                    accID = jwtService.getAccIDFromToken(authToken);
                    AccountModel temp = accountService.getUserByAccID(accID);
                    if (String.valueOf(accID.charAt(0)).equals("A")) {
                        a   = productService.getProductByCateID(cateId,sort);
                    }
                    else {
                        return ResponseEntity.status(Error.WRONG_ACCESS_RIGHTS).body(
                                new ResponseObject(false,"Can quyen Admin","")
                        );
                    }
                }
                else {
                    return ResponseEntity.status(Error.WRONG_ACCESS_RIGHTS).body(
                            new ResponseObject(false,"Can quyen Admin","")
                    );
                }
         }
        else {
            a = productService.getProductByCateIDUser(cateId,sort);}
        List<Object> kq = new ArrayList<Object>();
        if(a.isEmpty() ==true){
                return ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,"")
                );}
        else {
            for(int i=0;i<a.size();i++) {
                HashMap<String,String> object = new HashMap<String,String>();
                object.put("_id",String.valueOf(a.get(i).get_id()));
                object.put("proId",String.valueOf(a.get(i).getproId()));
                object.put("proName",String.valueOf(a.get(i).getProName()));
                object.put("description",String.valueOf(a.get(i).getDescription()));
                object.put("price",String.valueOf(a.get(i).getPrice()));
                object.put("cateId",String.valueOf(a.get(i).getCateId()));
                object.put("color",String.valueOf(a.get(i).getColor()));
                object.put("quantity",String.valueOf(a.get(i).getQuantity()));
                object.put("warrantyMonth",String.valueOf(a.get(i).getWarrantyMonth()));
                object.put("status",String.valueOf(a.get(i).getStatus()));
                if(imageController.getPathImageByID(a.get(i).getproId()) != null){
                    object.put("image",imageController.getPathImageByID(a.get(i).getproId()).getImgPath());
                }
                kq.add(object);
            }
            if(a.isEmpty() == true){
                return ResponseEntity.status(Error.LIST_EMPTY).body(
                        new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,""));
            }
            else {
                return ResponseEntity.status(Error.OK).body(
                        new ResponseObject(true,Error.OK_MESSAGE,kq)); }
    }}

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
            if(image.length == 1 && image[0].getOriginalFilename().equals("") == true) {
                return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                        new ResponseObject(false,"Thieu anh khi them san pham","")
                );
            }
            else{
                productService.saveProduct(productModel);
                ResponseEntity<ResponseObject> aa = imageController.addImage(image, proId);
                if(aa.getStatusCodeValue()!=200){
                    return aa;
                }
            }
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE, "")
            );
        } else {
            return ResponseEntity.status(Error.DUPLICATE_ID).body(
                    new ResponseObject(false,Error.DUPLICATE_ID_MESSAGE,"")
            );
        }
    }

    @PostMapping("/addListProduct")
    ResponseEntity<ResponseObject> addListProduct(@RequestParam("proId") String[] proId,@RequestParam("proName") String[] proName, @RequestParam("description")String[] description,@RequestParam("price") long[] price, @RequestParam("cateId") String[] cateId,@RequestParam("color") String[] color,@RequestParam("quantity") int[] quantity,@RequestParam("warrantyMonth") int[] warrantyMonth,@RequestParam("image") MultipartFile[] image) throws IOException {
        if(proId.length == 0 ||proName.length == 0 || description.length == 0|| price.length == 0 || cateId.length == 0 || color.length == 0 || warrantyMonth.length == 0){
            return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                    new ResponseObject(false,Error.DATA_REQUEST_ERROR_MESSAGE,"")
            );
        }
        int size = proId.length;
        if(size != proName.length || size != description.length || size != price.length || size != cateId.length || size != color.length || size != quantity.length || size != warrantyMonth.length || size != image.length){
            return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                    new ResponseObject(false,Error.DATA_REQUEST_ERROR_MESSAGE,"")
            );}
        for (int i = 0 ; i < size; i++){
            ProductModel productModel = productService.getProductById(proId[i]);
            Optional<ProductModel> check = Optional.ofNullable(productModel);
            if(check.isPresent() == true){
//                 return ResponseEntity.status(Error.DUPLICATE_ID).body(
//                         new ResponseObject(false,Error.DUPLICATE_ID_MESSAGE,"")
//                 );
                int newQuantity  = productModel.getQuantity() + quantity[i];
                productModel.setQuantity(newQuantity);
                productService.updateProduct(productModel,proId[i]);
            }
        }
        for (int i = 0 ; i < size ; i++){
            MultipartFile[] multipartFile = new MultipartFile[1];
            multipartFile[0]=image[i];
            addnewProduct(proId[i],proName[i],description[i],price[i],cateId[i],color[i],quantity[i],warrantyMonth[i],multipartFile);
        }
        return ResponseEntity.status(Error.OK).body(
                new ResponseObject(true,Error.OK_MESSAGE, "")
        );
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
    ResponseEntity<ResponseObject>search(ServletRequest request,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "1") int type,@RequestParam(defaultValue = "") String text){
        if(type<0 || type>1){
            return ResponseEntity.status(Error.DATA_REQUEST_ERROR).body(
                    new ResponseObject(false,Error.DATA_REQUEST_ERROR_MESSAGE,"")
            );
        }
        Pageable paging = PageRequest.of(page,size);
        Page<ProductModel> check ;
        List<Object> kq = new ArrayList<Object>();
        if(type == 1){
            check = productService.searchProductUser(paging,text);
        }
        else {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String authToken = httpRequest.getHeader("authorization");
            String accID = null;
            if (jwtService.validateTokenLogin(authToken)) {
                accID = jwtService.getAccIDFromToken(authToken);
                AccountModel temp = accountService.getUserByAccID(accID);
                if (String.valueOf(accID.charAt(0)).equals("A")) {
                    check = productService.searchProductAdmin(paging,text);
                }
                else {
                    return ResponseEntity.status(Error.WRONG_ACCESS_RIGHTS).body(
                            new ResponseObject(false,"Can quyen Admin","")
                    );
                }
            }
            else {
                return ResponseEntity.status(Error.WRONG_ACCESS_RIGHTS).body(
                        new ResponseObject(false,"Can quyen Admin","")
                );
            }
        }
        List<ProductModel> a =check.getContent();
        for(int i=0;i<a.size();i++) {
            HashMap<String,String> object = new HashMap<String,String>();
            object.put("_id",String.valueOf(a.get(i).get_id()));
            object.put("proId",String.valueOf(a.get(i).getproId()));
            object.put("proName",String.valueOf(a.get(i).getProName()));
            object.put("description",String.valueOf(a.get(i).getDescription()));
            object.put("price",String.valueOf(a.get(i).getPrice()));
            object.put("cateId",String.valueOf(a.get(i).getCateId()));
            object.put("color",String.valueOf(a.get(i).getColor()));
            object.put("quantity",String.valueOf(a.get(i).getQuantity()));
            object.put("warrantyMonth",String.valueOf(a.get(i).getWarrantyMonth()));
            object.put("status",String.valueOf(a.get(i).getStatus()));
            if(imageController.getPathImageByID(a.get(i).getproId()) != null){
                object.put("image",imageController.getPathImageByID(a.get(i).getproId()).getImgPath());
            }
            kq.add(object);
        }
        if(check.isEmpty() == true){
            return ResponseEntity.status(Error.LIST_EMPTY).body(
                    new ResponseObject(false, Error.LIST_EMPTY_MESSAGE,""));
        }
        else {
            return ResponseEntity.status(Error.OK).body(
                    new ResponseObject(true,Error.OK_MESSAGE, kq)); }
    }
}