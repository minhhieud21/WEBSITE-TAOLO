package com.example.JavaSpring.service;

import com.example.JavaSpring.models.BlogModel;
import com.example.JavaSpring.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {


    Page<ProductModel> getAllProductAdmin(Pageable paging);

    List<ProductModel> getAllProductUser();

    ProductModel getProductById(String id);


    void saveProduct(ProductModel productModel);

    List<ProductModel> getProductByCateID(String cateId,int Type);

    void deleteProduct(String proId);


    void statusHide(String proId);

    void statusShow(String proId);

    void updatePrice(String proId, Long price);

    void updateProduct(ProductModel productModel, String proId);

    List<ProductModel> searchProduct(String proName);
}
