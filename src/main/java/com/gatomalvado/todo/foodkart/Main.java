package com.gatomalvado.todo.foodkart;

import java.util.List;

import com.gatomalvado.todo.foodkart.entities.Customer;
import com.gatomalvado.todo.foodkart.entities.Menu;
import com.gatomalvado.todo.foodkart.entities.MenuItem;
import com.gatomalvado.todo.foodkart.entities.OrderItem;
import com.gatomalvado.todo.foodkart.entities.Restaurant;
import com.gatomalvado.todo.foodkart.repo.RestaurantRepository;
import com.gatomalvado.todo.foodkart.service.RestaurantService;

public class Main {

    // add_restaurant(resta1, {‘king_burger’: 10, ‘samosa_pizza’: 20, ‘alu_pasta’: 19}, 15)

    public static void main(String[] args) {
        System.out.println("Hello Food Kart!");
        Customer customer1 = Customer.builder().id("uId1").name("username-1").build();
        Customer customer2 = Customer.builder().id("uId2").name("username-2").build();
        Customer customer3 = Customer.builder().id("uId3").name("username-3").build();

        List<MenuItem> menuList1 = List.of(MenuItem.builder().itemName("king_burger").price(10).id("m1").build(),
            MenuItem.builder().itemName("samosa").price(40).id("m2").build(),
            MenuItem.builder().itemName("pizza").price(60).id("m3").build(),
            MenuItem.builder().itemName("kachori").price(90).id("m4").build());

        List<MenuItem> menuList2 = List.of(MenuItem.builder().itemName("ladoo").price(10).id("m5").build(),
            MenuItem.builder().itemName("peda").price(10).id("m6").build(),
            MenuItem.builder().itemName("barfi").price(110).id("m7").build(),
            MenuItem.builder().itemName("khaja").price(2000).id("m8").build(),
            MenuItem.builder().itemName("chenna").price(10).id("m9").build());

        List<MenuItem> menuList3 = List.of(MenuItem.builder().itemName("cake").price(10).id("m10").build(),
            MenuItem.builder().itemName("choco_biscuit").price(10).id("m11").build(),
            MenuItem.builder().itemName("bun").price(10).id("m12").build());

        List<MenuItem> menuList4 = List.of(MenuItem.builder().itemName("white_bread").price(10).id("m13").build(),
            MenuItem.builder().itemName("paneer_sandwich").price(80).id("m14").build(),
            MenuItem.builder().itemName("chicken_sandwich").price(1000).id("m15").build(),
            MenuItem.builder().itemName("greek_salad").price(10).id("m16").build());

        List<MenuItem> menuList5 = List.of(MenuItem.builder().itemName("aloo_paratha").price(90).id("m17").build(),
            MenuItem.builder().itemName("biryani").price(50).id("m18").build(),
            MenuItem.builder().itemName("naan").price(400).id("m19").build(),
            MenuItem.builder().itemName("butter_chicken").price(100).id("m20").build(),
            MenuItem.builder().itemName("tandoori_chicken").price(40).id("m21").build());

        Menu menu1 = Menu.builder().menuItems(menuList1).id("menu-1").build();
        Menu menu2 = Menu.builder().menuItems(menuList2).id("menu-2").build();
        Menu menu3 = Menu.builder().menuItems(menuList3).id("menu-3").build();
        Menu menu4 = Menu.builder().menuItems(menuList4).id("menu-4").build();
        Menu menu5 = Menu.builder().menuItems(menuList5).id("menu-5").build();

        Restaurant restaurant1 = Restaurant.builder().name("rest1").id("r1").menu(menu1).maxCap(5).build();
        Restaurant restaurant2 = Restaurant.builder().name("rest2").id("r2").menu(menu2).maxCap(5).build();
        Restaurant restaurant3 = Restaurant.builder().name("rest3").id("r3").menu(menu3).maxCap(5).build();
        Restaurant restaurant4 = Restaurant.builder().name("rest4").id("r4").menu(menu4).maxCap(5).build();
        Restaurant restaurant5 = Restaurant.builder().name("rest5").id("r5").menu(menu5).maxCap(5).build();

        RestaurantRepository repository = new RestaurantRepository();
        RestaurantService restaurantService = new RestaurantService();

        restaurantService.addRestaurant(restaurant1);
        restaurantService.addRestaurant(restaurant2);
        restaurantService.addRestaurant(restaurant3);
        restaurantService.addRestaurant(restaurant4);
        restaurantService.addRestaurant(restaurant5);

//        Menu updatedMenu = Menu.builder().menuItems(List.of(MenuItem.builder().itemName("aloo_paratha").price(90).id("m17").build(),
//            MenuItem.builder().itemName("biryani").price(50).id("m18").build())).id("menu-5").build();

//        restaurantService.updateMenu(restaurant1, updatedMenu);
        restaurantService.printAllRestaurant();
        restaurantService.bookOrder("uId1", new OrderItem(List.of("aloo_paratha", "paneer_sandwich", "king_burger")));
        restaurantService.bookOrder("uId2", new OrderItem(List.of("aloo_paratha", "paneer_sandwich", "king_burger")));
        restaurantService.bookOrder("uId3", new OrderItem(List.of("aloo_paratha", "paneer_sandwich", "king_burger")));
        restaurantService.bookOrder("uId4", new OrderItem(List.of("aloo_paratha", "paneer_sandwich", "king_burger")));
        restaurantService.bookOrder("uId5", new OrderItem(List.of("aloo_paratha", "paneer_sandwich", "king_burger")));
        restaurantService.bookOrder("uId2", new OrderItem(List.of("aloo_paratha", "paneer_sandwich", "king_burger")));
        restaurantService.bookOrder("uId2", new OrderItem(List.of("aloo_paratha", "paneer_sandwich", "king_burger")));
        restaurantService.updateOrderStatusDelivered("order-1");
        restaurantService.updateOrderStatusDelivered("order-2");
        restaurantService.updateOrderStatusDelivered("order-3");
        restaurantService.updateOrderStatusDelivered("order-4");
        restaurantService.updateOrderStatusDelivered("order-5");
        restaurantService.bookOrder("uId4", new OrderItem(List.of("aloo_paratha", "paneer_sandwich", "king_burger")));
        restaurantService.printAllOrders();
    }

}
