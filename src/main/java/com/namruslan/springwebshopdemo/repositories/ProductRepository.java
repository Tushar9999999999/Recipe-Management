package com.namruslan.springwebshopdemo.repositories;

import com.namruslan.springwebshopdemo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findOneByTitle(String title);

    List<Product> findByOrderByIdDesc();

    List<Product> findByOrderByTitle();

    List<Product> findByOrderByTitleDesc();

    List<Product> findByOrderByPrice();

    List<Product> findByOrderByPriceDesc();
}
