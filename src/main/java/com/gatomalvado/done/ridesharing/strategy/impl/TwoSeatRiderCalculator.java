package com.gatomalvado.done.ridesharing.strategy.impl;

import com.gatomalvado.done.ridesharing.ConstantConfig;
import com.gatomalvado.done.ridesharing.model.BookedRide;
import com.gatomalvado.done.ridesharing.strategy.CostCalculator;

public class TwoSeatRiderCalculator implements CostCalculator {

    private CostCalculator costCalculator;

    @Override
    public double calculateRideCost(BookedRide bookedRide) {
        double cost = 0.0;
        if(costCalculator != null){
            cost = costCalculator.calculateRideCost(bookedRide);
        }
        return cost + getCustomCost(bookedRide);
    }

    private double getCustomCost(BookedRide bookedRide) {
        double distance = Math.abs(bookedRide.getRideDetails().getDestination() - bookedRide.getRideDetails().getOrigin());

        return distance * 0.75 * ConstantConfig.amountCharged * bookedRide.getRideDetails().getNoOfSeats();
    }

    @Override
    public void setCostCalculator(CostCalculator costCalculator) {
        this.costCalculator = costCalculator;
    }
}