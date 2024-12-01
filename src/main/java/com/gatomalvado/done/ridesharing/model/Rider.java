package com.gatomalvado.done.ridesharing.model;

import com.gatomalvado.done.ridesharing.model.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class Rider extends UserDetails {
    private UserType userType;
    private boolean preferredRider;

    public Rider(String userId, String name, String email) {
        super(userId, name, email);
        this.userType = UserType.RIDER;
    }
}
