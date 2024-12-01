package com.gatomalvado.done.ridesharing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    private String userId;
    private String name;
    private String email;
}
