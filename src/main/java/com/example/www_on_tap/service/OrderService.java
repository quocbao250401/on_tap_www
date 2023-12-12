package com.example.www_on_tap.service;

import com.example.www_on_tap.entity.Order;
import com.example.www_on_tap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public boolean deleteOrderById(Long id){
        orderRepository.deleteById(id);
        return true;
    }

    public Order updateOrder(Order order){
        Order existingOrder = orderRepository.findById(order.getId()).orElse(null);
        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setOrderDetails(order.getOrderDetails());
        existingOrder.setCustomer(order.getCustomer());
        existingOrder.setEmployee(order.getEmployee());
        return orderRepository.save(existingOrder);
    }

    public List<Order> getOrderByOrderDate(LocalDateTime orderDate){
        return orderRepository.findAllByOrderDate(orderDate);
    }

    public List<Order> getOrderByOrderDateBetween(LocalDateTime from, LocalDateTime to){
        System.out.println(from);
        System.out.println(to);
        return orderRepository.findAllByOrderDateBetween(from, to);
    }
}
