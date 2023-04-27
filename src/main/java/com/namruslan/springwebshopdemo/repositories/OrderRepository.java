package com.namruslan.springwebshopdemo.repositories;

import com.namruslan.springwebshopdemo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
