package com.gatomalvado.ridesharing.strategy;

import com.gatomalvado.ridesharing.model.BookedRide;

public interface CostCalculator {
    double calculateRideCost(BookedRide bookedRide);
    void setCostCalculator(CostCalculator costCalculator);
}
