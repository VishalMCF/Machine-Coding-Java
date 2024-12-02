package com.gatomalvado.todo.foodkart.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Restaurant {
    private String id;
    private String name;
    private Menu menu;
    private int maxCap;
    private int currentCapacity;
}
