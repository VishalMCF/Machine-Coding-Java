package com.gatomalvado.done.foodkart.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {
    private String id;
    private String itemName;
    private double price;
}
