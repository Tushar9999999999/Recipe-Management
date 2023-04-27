package com.namruslan.springwebshopdemo.utils;

import com.namruslan.springwebshopdemo.entities.OrderItem;
import com.namruslan.springwebshopdemo.entities.Product;
import com.namruslan.springwebshopdemo.view.OrderView;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrderViewUtil {

    public List<OrderView> toView(List<OrderItem> items) {

        Map<Product, OrderView> map = new HashMap<>();
        List<OrderView> resultList;

        for (OrderItem orderItem : items) {
            Product product = orderItem.getProduct();
            if (map.containsKey(product)) {
                map.get(product).addCount();
            } else {
                map.put(product, new OrderView(product));
            }
        }

        resultList = new ArrayList<>(map.values());

        return resultList;
    }
}
