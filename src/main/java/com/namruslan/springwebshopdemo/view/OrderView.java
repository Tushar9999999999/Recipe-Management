package com.namruslan.springwebshopdemo.view;

import com.namruslan.springwebshopdemo.entities.Product;

public class OrderView {

    private Product product;
    private int productCount;

    public OrderView (Product product) {
        productCount = 1;
        this.product = product;
    }

    public void addCount() {
        productCount++;
    }

    public void decreaseCount() {
        productCount--;
    }

    public Product getProduct() {
        return product;
    }

    public int getProductCount() {
        return productCount;
    }

    public int getTotalPrice() {
        return productCount * product.getPrice();
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
}
