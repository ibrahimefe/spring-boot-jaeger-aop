package com.dfx.thought.leadership.article.jaegeraop.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = -2758263676622574388L;

    private Long productId;
    private String name;
    private Integer stock;
    private Integer price;

    public Product() {
        this.productId = 1L;
        this.name = "Computer";
        this.stock = 10;
        this.price = 100;
    }
}
