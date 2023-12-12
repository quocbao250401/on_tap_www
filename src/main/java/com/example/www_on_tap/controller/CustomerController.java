package com.example.www_on_tap.controller;

import com.example.www_on_tap.entity.Customer;
import com.example.www_on_tap.service.CustomerService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getCustomerList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "size", defaultValue = "10") int size,
                                                          @RequestParam(value = "sort", defaultValue = "ASC") String sort,
                                                          @RequestParam(value = "field", defaultValue = "id") String field) {
        return ResponseEntity.ok(customerService.getAllCustomers(page, size, sort, field).stream().toList());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerList(@PathVariable("id") String id) {
        return ResponseEntity.ok(customerService.getCustomerById(Long.parseLong(id)));
    }
}
