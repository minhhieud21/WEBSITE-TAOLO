package com.example.JavaSpring.service;

import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<ProductModel> getAllProduct(Pageable paging) {
        return productRepository.findAll(paging);
    }
    @Override
    public Page<ProductModel> getAllProductUser(Pageable paging) {
        return productRepository.getAllProductUser(paging,1);
    }

    @Override
    public ProductModel getProductById(String id) {
        return productRepository.getProductByID(id);
    }

    @Override
    public void saveProduct(ProductModel productModel){
        productModel.setStatus(0);
        productRepository.save(productModel);
    }
    @Override
    public List<ProductModel> getProductByCateID(String cateId,int Type) {
        if(Type == 0 ){
            return productRepository.getProductByCateID(cateId,null);}
        else if( Type == 1 ){
            return productRepository.getProductByCateID(cateId,Sort.by(Sort.Direction.ASC,"proName"));
        }
        else if( Type == 2 ){
            return productRepository.getProductByCateID(cateId,Sort.by(Sort.Direction.DESC,"proName"));
        }
        else if( Type == 3 ){
            return productRepository.getProductByCateID(cateId,Sort.by(Sort.Direction.ASC,"price"));
        }
        else {
            return productRepository.getProductByCateID(cateId,Sort.by(Sort.Direction.DESC,"price"));
        }
    }

    @Override
    public void deleteProduct(String proId) {
        productRepository.deleteById(proId);
    }

    @Override
    public void statusHide(String proId){
        ProductModel productModel = productRepository.getProductByID(proId);
        productModel.setStatus(0);
        productRepository.save(productModel);
    }
    @Override
    public void statusShow(String proId){
        ProductModel productModel = productRepository.getProductByID(proId);
        productModel.setStatus(1);
        productRepository.save(productModel);
    }

    @Override
    public void updatePrice(String proId, Long price) {
        ProductModel productModel = productRepository.getProductByID(proId);
        productModel.setPrice(price);
        productRepository.save(productModel);
    }

    @Override
    public void updateProduct(ProductModel productModel, String proId) {
        ProductModel productModel1 = productRepository.getProductByID(proId);
        productModel.set_id(productModel1.get_id());
        productModel.setproId(productModel1.getproId());
        productModel.setCateId(productModel1.getCateId())   ;
        productModel.setStatus(productModel1.getStatus());
        productRepository.save(productModel);
    }

    @Override
    public Page<ProductModel> searchProductAdmin(Pageable paging,String text) {
        return productRepository.getProductAdmin(paging,text);
    }
    @Override
    public Page<ProductModel> searchProductUser(Pageable paging, String text) {
        return productRepository.getProductUser(paging,text,1);
    }
}
