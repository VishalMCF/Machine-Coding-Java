package com.gatomalvado.done.ridesharing;

import com.gatomalvado.done.ridesharing.model.Driver;
import com.gatomalvado.done.ridesharing.model.Rider;
import com.gatomalvado.done.ridesharing.orchestrator.RideManager;
import com.gatomalvado.done.ridesharing.strategy.CostCalculatorFactory;

public class Main {
    public static void main(String[] args){
        Rider rider1 = new Rider("r1","rider1", "email1");
        Rider rider2 = new Rider("r2","rider2", "email2");

        Driver driver1 = new Driver("d1","driver1", "email3");
        Driver driver2 = new Driver("d2","driver2", "email4");
        Driver driver3 = new Driver("d3","driver3", "email5");
        Driver driver4 = new Driver("d4","driver4", "email6");

        CostCalculatorFactory costCalculatorFactory = new CostCalculatorFactory();
        RideManager riderManager  = new RideManager(costCalculatorFactory);

        riderManager.addRider(rider1);
        riderManager.addRider(rider2);

        riderManager.addDriver(driver1);
        riderManager.addDriver(driver2);
        riderManager.addDriver(driver3);
        riderManager.addDriver(driver4);

        // take input

        // create ride
    }

}
