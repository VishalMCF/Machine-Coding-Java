package com.gatomalvado.libmgmt.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SearchRequest {
    private String param;
    private String value;
}
