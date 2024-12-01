package com.gatomalvado.done.libmgmt.model.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateBookDetails {
    private String title;
    private List<String> author;
    private List<String> publisher;
    private String bookId;
    private List<String> bookCopyIds;
}
