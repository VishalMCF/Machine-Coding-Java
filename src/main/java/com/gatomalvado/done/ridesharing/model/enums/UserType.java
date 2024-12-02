package com.gatomalvado.done.ridesharing.model.enums;

import lombok.Getter;

@Getter
public enum UserType {
    RIDER("rider"),
    DRIVER("driver");

    private String name;

    UserType(String name) {
        this.name = name;
    }
}
