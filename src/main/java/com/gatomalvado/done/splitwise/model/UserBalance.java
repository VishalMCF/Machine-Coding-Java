package com.gatomalvado.done.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBalance {
    private String owedTo;
    private String userId;
    private Double amount;
}
