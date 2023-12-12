package com.example.www_on_tap.entity;

import com.example.www_on_tap.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long product_id;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductPrice> productPrices;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductImage> productImages;
    private String name;
    private String description;
    private ProductStatus status;
    private String manufacturer;
    private String unit;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;
}
