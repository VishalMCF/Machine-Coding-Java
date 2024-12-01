package com.gatomalvado.todo.contactmgmt.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.gatomalvado.todo.contactmgmt.entities.Contact;
import com.gatomalvado.todo.contactmgmt.entities.SearchResult;

public class MapBasedStrategy implements SearchStrategy {

    private Map<String, Contact> contactsMap;

    public MapBasedStrategy() {
        this.contactsMap = new HashMap<>();
    }

    @Override
    public void access(Contact contact) {
        contactsMap.put(contact.getId(), contact);
    }

    @Override
    public void remove(Contact contact) {
        this.contactsMap.remove(contact.getId());
    }

    @Override
    public SearchResult search(String fieldValue, String fieldName) {
        switch (fieldName) {
            case "firstName":
                return searchByFirstName(fieldValue);
            case "lastName":
                return searchByLastName(fieldValue);
            case "phone":
                return searchByPhone(fieldValue);
            default:
                return new SearchResult(0, new ArrayList<>());
        }
    }

    private SearchResult searchByFirstName(String firstName) {
        List<Contact> results = this.contactsMap.values()
            .stream()
            .filter((c) -> matchPrefix(c.getFirstName(), firstName))
            .collect(Collectors.toUnmodifiableList());

        return new SearchResult(results.size(), results);
    }

    private SearchResult searchByLastName(String lastName) {
        List<Contact> results = this.contactsMap.values()
            .stream()
            .filter((c) -> matchPrefix(c.getLastName(), lastName))
            .collect(Collectors.toUnmodifiableList());

        return new SearchResult(results.size(), results);
    }

    private SearchResult searchByPhone(String phone) {
        List<Contact> results = this.contactsMap.values()
            .stream()
            .filter((c) -> matchPrefix(c.getPhoneNumber(), phone))
            .collect(Collectors.toUnmodifiableList());

        return new SearchResult(results.size(), results);
    }

    private boolean matchPrefix(String value, String prefix) {
        return value.startsWith(prefix);
    }

}
