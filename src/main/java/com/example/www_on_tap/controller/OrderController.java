package com.example.www_on_tap.controller;

import com.example.www_on_tap.entity.Order;
import com.example.www_on_tap.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getOrderBetween(@RequestParam @Nullable String from, @RequestParam @Nullable String to) {
        if (from == null || to == null) {
            return ResponseEntity.ok(orderService.getAllOrders());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromDate = LocalDate.parse(from, formatter);
        LocalDate toDate = LocalDate.parse(to, formatter);
        LocalDateTime fromDateTime = fromDate.atStartOfDay();
        LocalDateTime toDateTime = toDate.atStartOfDay();
        return ResponseEntity.ok(orderService.getOrderByOrderDateBetween(fromDateTime, toDateTime));
    }
}