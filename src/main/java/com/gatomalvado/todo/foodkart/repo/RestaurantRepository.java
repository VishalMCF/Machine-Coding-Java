package com.gatomalvado.todo.foodkart.repo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.gatomalvado.todo.foodkart.entities.Customer;
import com.gatomalvado.todo.foodkart.entities.Menu;
import com.gatomalvado.todo.foodkart.entities.MenuItem;
import com.gatomalvado.todo.foodkart.entities.Order;
import com.gatomalvado.todo.foodkart.entities.Restaurant;

public class RestaurantRepository {

    private Map<String, Restaurant> restaurants;
    private Map<String, Order> orders;
    private Map<String, Customer> customers;
    private Map<String, MenuItem> menuItems;
    private Map<String, Menu> menus;

    public RestaurantRepository() {
        this.restaurants = new ConcurrentHashMap<>();
        this.orders = new ConcurrentHashMap<>();
        this.customers = new ConcurrentHashMap<>();
        this.menuItems = new ConcurrentHashMap<>();
        this.menus = new ConcurrentHashMap<>();
    }

}
