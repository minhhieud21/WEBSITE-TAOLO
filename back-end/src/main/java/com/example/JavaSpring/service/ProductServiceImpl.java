package com.example.JavaSpring.service;

import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductModel> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public ProductModel getProductById(String id) {
        return productRepository.getProductByID(id);
    }

    @Override
    public void saveProduct(ProductModel productModel){
        productRepository.save(productModel);
    }
    @Override
    public List<ProductModel> getProductByCateID(String cateId) {
        return productRepository.getProductByCateID(cateId);
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
        productModel.setStatus(productModel1.getStatus());
        productRepository.save(productModel);
    }

    @Override
    public List<ProductModel> searchProduct(String proName) {
        return productRepository.getBlogByName(proName);
    }


}
