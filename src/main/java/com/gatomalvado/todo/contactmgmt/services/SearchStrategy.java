package com.gatomalvado.todo.contactmgmt.services;

import java.util.List;

import com.gatomalvado.todo.contactmgmt.entities.Contact;
import com.gatomalvado.todo.contactmgmt.entities.SearchResult;

public interface SearchStrategy {

    void access(Contact contact);

    void remove(Contact contact);

    SearchResult search(String fieldValue, String fieldName);

}
