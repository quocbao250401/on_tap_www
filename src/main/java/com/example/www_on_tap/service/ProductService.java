package com.example.www_on_tap.service;

import com.example.www_on_tap.entity.Product;
import com.example.www_on_tap.entity.ProductPrice;
import com.example.www_on_tap.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Page<Product> getAllProducts(int page, int size, String sort, String field){
        Sort sortDir = sort.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(field).ascending() : Sort.by(field).descending();
        Pageable pageable = PageRequest.of(page, size, sortDir);
        return productRepository.findAll(pageable);
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public boolean deleteProductById(Long id){
        productRepository.deleteById(id);
        return true;
    }

    public List<ProductPrice> getAllProductPrices(Long id){
        return productRepository.findById(id).orElse(null).getProductPrices();
    }

    public Product updateProduct(Product product){
        Product existingProduct = productRepository.findById(product.getProduct_id()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setProductImages(product.getProductImages());
        existingProduct.setProductPrices(product.getProductPrices());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setManufacturer(product.getManufacturer());
        existingProduct.setOrderDetails(product.getOrderDetails());
        existingProduct.setStatus(product.getStatus());
        existingProduct.setUnit(product.getUnit());
        return productRepository.save(existingProduct);
    }
}
