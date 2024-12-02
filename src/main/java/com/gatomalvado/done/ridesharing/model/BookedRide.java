package com.gatomalvado.done.ridesharing.model;

import java.util.UUID;

import com.gatomalvado.done.ridesharing.model.enums.RideStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookedRide {

    private Rider rider;
    private Driver driver;
    private Ride rideDetails;
    private String id;
    private RideStatus rideStatus;

    public BookedRide(Rider rider, Driver driver, Ride rideDetails, RideStatus rideStatus) {
        this.rider = rider;
        this.driver = driver;
        this.rideDetails = rideDetails;
        this.id = UUID.randomUUID().toString();
        this.rideStatus = rideStatus;
    }
}
