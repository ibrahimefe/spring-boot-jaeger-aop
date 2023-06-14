package com.dfx.thought.leadership.article.jaegeraop.service;

import com.dfx.thought.leadership.article.jaegeraop.configuration.GenericException;
import com.dfx.thought.leadership.article.jaegeraop.data.model.Order;
import com.dfx.thought.leadership.article.jaegeraop.data.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.dfx.thought.leadership.article.jaegeraop.feign.OrderClient;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final OrderClient orderClient;
    private static final String ERR_MESSAGE = "Product Id invalid!";
    private static final String ZERO = "0";

    @Override
    public Product getProductById(String productId) {
        if(productId.equals(ZERO)) {
            throw new GenericException(ERR_MESSAGE);
        }
        return new Product();
    }

    @Override
    public Product saveProduct(Product product) {
        orderClient.saveOrder(new Order());
        return new Product();
    }
}
