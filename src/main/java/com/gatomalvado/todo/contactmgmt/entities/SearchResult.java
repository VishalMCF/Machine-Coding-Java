package com.gatomalvado.todo.contactmgmt.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SearchResult {

    private int totalCount;
    private List<Contact> contacts;

}
