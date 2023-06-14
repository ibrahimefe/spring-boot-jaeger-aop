package com.dfx.thought.leadership.article.jaegeraop.controller;

import com.dfx.thought.leadership.article.jaegeraop.data.model.Product;
import com.dfx.thought.leadership.article.jaegeraop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    public ResponseEntity<Product> getProduct(String productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @Override
    public ResponseEntity<Product> saveProduct(Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }
}

