package com.valakyr.market.web.controller;

import com.valakyr.market.domain.Product;
import com.valakyr.market.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @Operation(summary = "Get All", description = "Get all products")
    @ApiResponse(responseCode = "200", description = "OK")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Product Id", description = "Search a product with an Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public Optional<Product> getProduct(@PathVariable("id") int productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/category/{id}")
    public Optional<List<Product>> getByCategory(@PathVariable("id") int categoryId) {
        return productService.getByCategory(categoryId);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable("id") int productId) {
        return productService.delete(productId);
    }

}

