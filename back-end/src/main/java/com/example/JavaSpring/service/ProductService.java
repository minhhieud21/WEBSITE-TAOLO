package com.example.JavaSpring.service;

import com.example.JavaSpring.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Page<ProductModel> getAllProduct(Pageable paging);

    Page<ProductModel> getAllProductUser(Pageable paging);

    ProductModel getProductById(String id);


    void saveProduct(ProductModel productModel);

    List<ProductModel> getProductByCateID(String cateId,int Type);

    void deleteProduct(String proId);

    void statusHide(String proId);

    void statusShow(String proId);

    void updatePrice(String proId, Long price);

    void updateProduct(ProductModel productModel, String proId);

    Page<ProductModel> searchProductAdmin(Pageable paging,String text);

    Page<ProductModel> searchProductUser(Pageable paging, String text);
}
