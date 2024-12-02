package com.gatomalvado.todo.foodkart.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetail {
    private String itemName;
    private int quantity;
}
