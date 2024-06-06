package com.gatomalvado.ridesharing.model;

import com.gatomalvado.ridesharing.model.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Driver extends UserDetails{
    private UserType userType;
    private Boolean isFree;

    public Driver(String userId, String name, String email) {
        super(userId, name, email);
        this.userType = UserType.DRIVER;
        this.isFree = true;
    }
}
