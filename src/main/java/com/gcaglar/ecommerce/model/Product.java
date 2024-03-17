package com.gcaglar.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private String Category;
    private String Name;
    private double Price;
    private int Stock;
}
