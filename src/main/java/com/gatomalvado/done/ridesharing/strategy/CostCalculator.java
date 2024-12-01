package com.gatomalvado.done.ridesharing.strategy;

import com.gatomalvado.done.ridesharing.model.BookedRide;

public interface CostCalculator {
    double calculateRideCost(BookedRide bookedRide);
    void setCostCalculator(CostCalculator costCalculator);
}
