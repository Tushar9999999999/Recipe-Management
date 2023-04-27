package com.namruslan.springwebshopdemo.controller;

import com.namruslan.springwebshopdemo.entities.Product;
import com.namruslan.springwebshopdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/index")
    public String homepage() {
        return "index";
    }

    @GetMapping("/details/delete/{id}")
    public String deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/shop";
    }

    @GetMapping("/data")
    @ResponseBody
    public String dataExample(@RequestParam(value = "serial", required = false) Long serial,
                              @RequestParam(value = "number", required = false) Long number) {
        return "S/N: " + serial + " / " + number;
    }

    @GetMapping("/find_by_title")
    public String byTitle(Model model, @RequestParam("title") String title) {
        Product selectedProduct = productService.getProductByTitle(title);
        model.addAttribute("selectedProduct", selectedProduct);
        return "details";
    }

    @GetMapping("/details/{id}")
    public String detailsPage(Model model, @PathVariable("id") Long id) {
        Product selectProduct = productService.getProductById(id);
        model.addAttribute("selectedProduct", selectProduct);
        return "details";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/shop")
    public String shopPage (Model model,
                            @RequestParam(value = "sort", required = false, defaultValue = "") String sort) {

        List<Product> allProducts;

        switch (sort) {

            case "idDesc":
                allProducts = productService.getAllProductsDesc();
                break;

            case "title":
                allProducts = productService.getAllProductsOrderByTitle();
                break;

            case "titleDesc":
                allProducts = productService.getAllProductsOrderByTitleDesc();
                break;

            case "price":
                allProducts = productService.getAllProductsOrderByPrice();
                break;

            case "priceDesc":
                allProducts = productService.getAllProductsOrderByPriceDesc();
                break;

            default:
                allProducts = productService.getAllProducts();
                break;
        }

        model.addAttribute("products", allProducts);
        return "shop";
    }
}
