package com.gatomalvado.done.foodkart.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.gatomalvado.done.foodkart.entities.Customer;
import com.gatomalvado.done.foodkart.entities.Menu;
import com.gatomalvado.done.foodkart.entities.MenuItem;
import com.gatomalvado.done.foodkart.entities.Order;
import com.gatomalvado.done.foodkart.entities.OrderItem;
import com.gatomalvado.done.foodkart.entities.OrderStatus;
import com.gatomalvado.done.foodkart.entities.Restaurant;

public class RestaurantService {

    private Map<String, Restaurant> restaurants;
    private Map<String, Order> orders;
    private Map<String, Customer> customers;
    private Map<String, MenuItem> menuItems;
    private Map<String, Menu> menus;
    private Map<String, List<Order>> customerToOrderMap;

    private int orderId = 0;

    public RestaurantService() {
        this.restaurants = new ConcurrentHashMap<>();
        this.orders = new ConcurrentHashMap<>();
        this.customers = new ConcurrentHashMap<>();
        this.menuItems = new ConcurrentHashMap<>();
        this.menus = new ConcurrentHashMap<>();
        this.customerToOrderMap = new ConcurrentHashMap<>();
    }

    public void printAllRestaurant() {
        System.out.println(restaurants);
    }

    public Order  bookOrder(String userId, OrderItem orderItem) {
        Set<String> itemNames = new HashSet<>(orderItem.getItems());
        Set<String> prepNames = new HashSet<>();
        Set<String> restaurantNames = new HashSet<>();
        for(Restaurant r : restaurants.values()) {
            List<MenuItem> currMenuItems = r.getMenu().getMenuItems();
            for(MenuItem m : currMenuItems) {
                if(itemNames.contains(m.getItemName()) && r.getCurrentCapacity() < r.getMaxCap()) {
                    itemNames.remove(m.getItemName());
                    prepNames.add(m.getItemName());
                    restaurantNames.add(r.getId());
                    r.setCurrentCapacity(r.getCurrentCapacity()+1);
                }
            }
        }
        if(!itemNames.isEmpty()) {
            System.out.println("Could not place any order");
            return null;
        }
        Order order = Order.builder().orderId("order-"+(++orderId)).build();
        order.setRestName(restaurantNames);
        System.out.println(order);
        if(!customerToOrderMap.containsKey(userId)) {
            customerToOrderMap.put(userId, new ArrayList<>());
        }
        orders.put(order.getOrderId(), order);
        customerToOrderMap.get(userId).add(order);
        return order;
    }

    public void updateOrderStatusDelivered(String id) {
        Order order = orders.get(id);
        if(order != null) {
            Set<String>  restaurantNames = order.getRestName();
            for(String name : restaurantNames) {
                Restaurant restaurant = restaurants.get(name);
                restaurant.setCurrentCapacity(restaurant.getCurrentCapacity() - 1);
            }
        }
        order.setOrderStatus(OrderStatus.DELIVERED);
    }

    public void printAllOrders() {
        System.out.println(orders);
    }

    public void updateMenu(Restaurant restaurant, Menu updatedMenu) {
        restaurant.setMenu(updatedMenu);
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.put(restaurant.getId(), restaurant);
    }
}
