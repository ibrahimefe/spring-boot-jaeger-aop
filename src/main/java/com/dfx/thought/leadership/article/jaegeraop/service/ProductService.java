package com.dfx.thought.leadership.article.jaegeraop.service;

import com.dfx.thought.leadership.article.jaegeraop.data.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Product getProductById(String productId);

    Product saveProduct(Product product);
}
