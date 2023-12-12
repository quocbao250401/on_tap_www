package com.example.www_on_tap.repository;

import com.example.www_on_tap.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderDate(LocalDateTime orderDate);

    List<Order> findAllByOrderDateBetween(LocalDateTime from, LocalDateTime to);
}
