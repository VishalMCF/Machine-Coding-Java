package com.gatomalvado.done.libmgmt.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Book {
    private String title;
    private List<String> author;
    private List<String> publisher;
    private String bookId;
}
