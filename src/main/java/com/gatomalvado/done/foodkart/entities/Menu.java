package com.gatomalvado.done.foodkart.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu {
    private String id;
    private List<MenuItem> menuItems;
}
