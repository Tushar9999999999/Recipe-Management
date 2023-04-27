package com.namruslan.springwebshopdemo.controller;

import com.namruslan.springwebshopdemo.entities.Order;
import com.namruslan.springwebshopdemo.entities.OrderItem;
import com.namruslan.springwebshopdemo.service.OrderService;
import com.namruslan.springwebshopdemo.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ShoppingCart cart;

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    @GetMapping("")
    public String showCart(Model model) {
        model.addAttribute("items", cart.getView());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(Model model, @PathVariable("id") Long id) {
        cart.addProductById(id);
        return "redirect:/shop";
    }

    @GetMapping("/create_order")
    public String createOrder(Principal principal) {

        if (principal == null) return "redirect:/cart";
        Order order = new Order();
        order.setItems(new ArrayList<>());
        order.setUsername(principal.getName());
        cart.getItems().stream().forEach(i -> {
            order.getItems().add(i);
            i.setOrder(order);
        });
        cart.getItems().clear();
        orderService.saveOrder(order);
        return "redirect:/shop";
    }

    @GetMapping("/delete/{id}")
    public String deleteFromCart(@PathVariable("id")Long id) {
        cart.deleteProductById(id);
        return "redirect:/cart";
    }

}
