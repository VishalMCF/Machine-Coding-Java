package com.gatomalvado.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {
    private String userId;
    private String name;
    private String email;
    private String mobile;
}
