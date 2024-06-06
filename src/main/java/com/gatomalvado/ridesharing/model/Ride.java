package com.gatomalvado.ridesharing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ride {
    private Double origin;
    private Double destination;
    private Integer noOfSeats;
}
