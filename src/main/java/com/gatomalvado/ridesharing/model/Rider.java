package com.gatomalvado.ridesharing.model;

import com.gatomalvado.ridesharing.model.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
