package com.gatomalvado.done.ridesharing.strategy;

import com.gatomalvado.done.ridesharing.model.BookedRide;
import com.gatomalvado.done.ridesharing.strategy.impl.PreferredRiderCostCalculator;
import com.gatomalvado.done.ridesharing.strategy.impl.SingleSeatRideCalculator;
import com.gatomalvado.done.ridesharing.strategy.impl.TwoSeatRiderCalculator;

public class CostCalculatorFactory {

    public CostCalculator getCostCalculator(BookedRide bookedRide) {
        CostCalculator costCalculator = null;
        if(bookedRide.getRideDetails().getNoOfSeats() > 2){
            costCalculator =  new TwoSeatRiderCalculator();
        }else {
            costCalculator = new SingleSeatRideCalculator();
        }

        // logic to add preferred Rider
        if(bookedRide.getRider().isPreferredRider()){
            costCalculator.setCostCalculator(new PreferredRiderCostCalculator());
        }

        return costCalculator;
    }
}
