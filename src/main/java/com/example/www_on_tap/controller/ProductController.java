package com.example.www_on_tap.controller;

import com.example.www_on_tap.entity.Product;
import com.example.www_on_tap.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/add")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("productPrices", product.getProductPrices());
        return "addProduct";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProductPost(@ModelAttribute("product") Product product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setProductPrices(Arrays.asList());
        newProduct.setManufacturer(product.getManufacturer());
        newProduct.setDescription(product.getDescription());
        newProduct.setStatus(product.getStatus());
        newProduct.setUnit(product.getUnit());
        newProduct.setProductImages(Arrays.asList());
        productService.saveProduct(newProduct);
        return "redirect:/product/list";
    }

    @RequestMapping("/detail")
    public String productDetail(@RequestParam("id") long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("productPrices", product.getProductPrices());
        return "productDetail";
    }

    @RequestMapping("/list")
    public String productList(Model model, @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "2") int size,
                              @RequestParam(defaultValue = "ASC") String sort,
                              @RequestParam(defaultValue = "name") String field ){
        Page<Product> productList = productService.getAllProducts(page, size, sort, field);
        model.addAttribute("productList", productList);
        return "productList";
    }

    @RequestMapping("/edit")
    public String editProduct(@RequestParam("id") long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("productPrices", product.getProductPrices());
        return "editProduct";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editProductPost(@ModelAttribute("product") Product product) {
        Product newProduct = new Product();
        newProduct.setProduct_id(product.getProduct_id());
        newProduct.setName(product.getName());
        newProduct.setProductPrices(Arrays.asList());
        newProduct.setManufacturer(product.getManufacturer());
        newProduct.setDescription(product.getDescription());
        newProduct.setStatus(product.getStatus());
        newProduct.setUnit(product.getUnit());
        newProduct.setProductImages(Arrays.asList());
        System.out.println(newProduct);
        productService.updateProduct(newProduct);
        return "redirect:/product/list";
    }

    @RequestMapping("/delete")
    public String deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/product/list";
    }

}
