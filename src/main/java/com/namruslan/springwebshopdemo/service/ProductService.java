package com.namruslan.springwebshopdemo.service;

import com.namruslan.springwebshopdemo.entities.Product;
import com.namruslan.springwebshopdemo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById (Long id) {
        return productRepository.findById(id).get();
    }

    public void deleteProductById (Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductByTitle(String title) {
        return productRepository.findOneByTitle(title);
    }

    public List<Product> getAllProducts () {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsDesc () {
        return productRepository.findByOrderByIdDesc();
    }

    public List<Product> getAllProductsOrderByTitle () {
        return productRepository.findByOrderByTitle();
    }

    public List<Product> getAllProductsOrderByTitleDesc () {
        return productRepository.findByOrderByTitleDesc();
    }

    public List<Product> getAllProductsOrderByPrice () {
        return productRepository.findByOrderByPrice();
    }

    public List<Product> getAllProductsOrderByPriceDesc () {
        return productRepository.findByOrderByPriceDesc();
    }



}
