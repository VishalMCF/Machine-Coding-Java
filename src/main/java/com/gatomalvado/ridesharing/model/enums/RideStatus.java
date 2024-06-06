package com.gatomalvado.ridesharing.model.enums;

import lombok.Getter;

@Getter
public enum RideStatus {
    BOOKED("booked"),
    WITHDRAWN("withdrawn"),
    CLOSED("closed");

    private String name;

    RideStatus(String name) {
        this.name = name;
    }
}
