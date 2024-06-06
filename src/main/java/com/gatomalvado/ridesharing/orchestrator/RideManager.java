package com.gatomalvado.ridesharing.orchestrator;

import static com.gatomalvado.ridesharing.ConstantConfig.MSG_CODE1;
import static com.gatomalvado.ridesharing.ConstantConfig.MSG_CODE2;
import static com.gatomalvado.ridesharing.ConstantConfig.MSG_CODE3;
import static com.gatomalvado.ridesharing.ConstantConfig.MSG_CODE4;
import static com.gatomalvado.ridesharing.ConstantConfig.MSG_CODE5;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.gatomalvado.ridesharing.exception.InvalidInputException;
import com.gatomalvado.ridesharing.model.BookedRide;
import com.gatomalvado.ridesharing.model.Driver;
import com.gatomalvado.ridesharing.model.Ride;
import com.gatomalvado.ridesharing.model.Rider;
import com.gatomalvado.ridesharing.model.enums.RideStatus;
import com.gatomalvado.ridesharing.strategy.CostCalculator;
import com.gatomalvado.ridesharing.strategy.CostCalculatorFactory;

public class RideManager {

    private Map<String, Driver> driverDetailsMap;
    private Map<String, Rider> riderDetailsMap;

    private Map<String, BookedRide> bookedRideMap;

    private CostCalculatorFactory costCalculatorFactory;

    public RideManager(CostCalculatorFactory costCalculatorFactory) {
        this.driverDetailsMap = new HashMap<>();
        this.riderDetailsMap = new HashMap<>();
        this.bookedRideMap = new HashMap<>();
        this.costCalculatorFactory = new CostCalculatorFactory();
    }

    public void addRider(Rider rider) {
        this.riderDetailsMap.put(rider.getUserId(), rider);
    }

    public void addDriver(Driver driver) {
        this.driverDetailsMap.put(driver.getUserId(), driver);
    }

    private double calculateRider(BookedRide bookedRide) {
        CostCalculator costCalculator = costCalculatorFactory.getCostCalculator(bookedRide);
        return costCalculator.calculateRideCost(bookedRide);
    }

    public BookedRide createRide(Rider rider, double destination, double origin, int noOfSeats) {
        // create a ride object
        Ride rideDetails = new Ride(origin, destination, noOfSeats);

        // find the free driver
        Driver freeDriver = driverDetailsMap.values().stream().filter((d) -> d.getIsFree()).collect(Collectors.toList()).getFirst();

        // if not driver find print some message
        if(freeDriver == null){
            System.out.println("No driver is free");
            return null;
        }
        freeDriver.setIsFree(false);

        // create the ride with booked status
        BookedRide bookedRide = new BookedRide(rider, freeDriver, rideDetails, RideStatus.BOOKED);

        // store the ride
        bookedRideMap.put(bookedRide.getId(), bookedRide);
        return bookedRide;
    }

    public BookedRide updateRide(String id, double destination, double origin, int noOfSeats) {
        // validate ride
        validateUpdateRideInput(id, destination, origin, noOfSeats);

        BookedRide  bookedRide = this.bookedRideMap.get(id);

        Ride rideDetails = new Ride(origin, destination, noOfSeats);

        // update the ride
        bookedRide.setRideDetails(rideDetails);
        return bookedRide;
    }

    public BookedRide withDrawRide(String id) {

        // validate the id
        validateTheId(id);

        // change status of ride
        // change status of the driver matched
        BookedRide bookedRide = bookedRideMap.get(id);

        // validate
        validateRideStatus(bookedRide);

        bookedRide.setRideStatus(RideStatus.WITHDRAWN);
        return bookedRide;
    }

    public BookedRide closeRide(String id) {

        // validate the id
        validateTheId(id);

        // calculate the amount
        BookedRide bookedRide = bookedRideMap.get(id);

        // validate
        validateRideStatus(bookedRide);

        CostCalculator costCalculator = costCalculatorFactory.getCostCalculator(bookedRide);
        double amount = costCalculator.calculateRideCost(bookedRide);
        // print the amount
        System.out.println("Total amount for the ride id: "+bookedRide.getId() + " is -> "+amount);

        // free the driver
        bookedRide.getDriver().setIsFree(true);

        // change the status of the ride
        bookedRide.setRideStatus(RideStatus.CLOSED);
        return bookedRide;
    }

    private void validateUpdateRideInput(String id, double destination, double origin, int noOfSeats) {
        validateTheId(id);

        // validate destination origin noOfSeats
        if(noOfSeats <= 0 || noOfSeats > 4) {
            throw new InvalidInputException(MSG_CODE2);
        }

        if(destination < origin){
            throw new InvalidInputException(MSG_CODE3);
        }

        if(destination < 0 || origin < 0){
            throw new InvalidInputException(MSG_CODE4);
        }
    }

    private void validateTheId(String id){
        // validate the id
        if(!bookedRideMap.containsKey(id)){
            throw new InvalidInputException(MSG_CODE1+id);
        }
    }


    private void validateRideStatus(BookedRide bookedRide) {
        if(bookedRide.getRideStatus().equals(RideStatus.CLOSED) || bookedRide.getRideStatus().equals(RideStatus.WITHDRAWN)) {
            throw new InvalidInputException(MSG_CODE5+bookedRide.getId());
        }
    }
}
