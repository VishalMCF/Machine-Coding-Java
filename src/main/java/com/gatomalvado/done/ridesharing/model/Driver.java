package com.gatomalvado.done.ridesharing.model;

import com.gatomalvado.done.ridesharing.model.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Driver extends UserDetails {
    private UserType userType;
    private Boolean isFree;

    public Driver(String userId, String name, String email) {
        super(userId, name, email);
        this.userType = UserType.DRIVER;
        this.isFree = true;
    }
}
