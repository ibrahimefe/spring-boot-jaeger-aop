package com.dfx.thought.leadership.article.jaegeraop.controller;

import com.dfx.thought.leadership.article.jaegeraop.data.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/product")
public interface ProductController {

    @GetMapping(path = "/getProduct")
    ResponseEntity<Product> getProduct(@RequestParam String productId);

    @PostMapping(path = "/saveProduct")
    ResponseEntity<Product> saveProduct(@RequestBody Product product);
}

