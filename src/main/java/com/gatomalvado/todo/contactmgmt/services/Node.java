package com.gatomalvado.todo.contactmgmt.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gatomalvado.todo.contactmgmt.entities.Contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Node {
    private boolean isEnd;
    private Map<Character, Node> node;
    private List<Contact> contacts;

    public Node() {
        node = new HashMap<>();
        contacts = new ArrayList<>();
    }
}
