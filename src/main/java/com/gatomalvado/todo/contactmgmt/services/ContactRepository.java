package com.gatomalvado.todo.contactmgmt.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.gatomalvado.todo.contactmgmt.entities.Contact;
import com.gatomalvado.todo.contactmgmt.entities.SearchResult;

public class ContactRepository {

    private Map<String, Contact> contactsMap;
    private SearchStrategy searchStrategy;


    public ContactRepository(SearchStrategy searchStrategy) {
        this.contactsMap = new HashMap<>();
        this.searchStrategy = searchStrategy;
    }

    public void add(Contact contact) {
        this.contactsMap.put(contact.getId(), contact);
        this.searchStrategy.access(contact);
    }

    public void remove(String contactId) {
        Contact contact = this.contactsMap.get(contactId);
        this.contactsMap.remove(contactId);
        this.searchStrategy.remove(contact);
    }

    public void updateFirstName(String contactId, String firstName) {
        Contact contact = this.contactsMap.get(contactId);
        contact.setFirstName(firstName);
        this.searchStrategy.access(contact);
    }

    public void updateLastName(String contactId, String lastname) {
        Contact contact = this.contactsMap.get(contactId);
        contact.setLastName(lastname);
        this.searchStrategy.access(contact);
    }

    public void updatePhone(String contactId, String phone, String countryCode) {
        Contact contact = this.contactsMap.get(contactId);
        contact.setPhoneNumber(phone);
        contact.setCountryCode(countryCode);
        this.searchStrategy.access(contact);
    }

    public SearchResult searchByFirstName(String input) {
        return this.searchStrategy.search(input, "firstName");
    }

    public SearchResult searchByLastName(String input) {
        return this.searchStrategy.search(input, "lastName");
    }

    public SearchResult searchByPhone(String input) {
        return this.searchStrategy.search(input, "phone");
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(this.contactsMap.values());
    }

    private void validatePhone(String phone) {
        for (Character c : phone.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Phone number must be digits");
            }
        }
    }

    private void validateCountryCode(String countryCode) {
        for (Character c : countryCode.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Country code must be digits");
            }
        }
        int value = Integer.parseInt(countryCode);
        if(value < 1 || value > 210) {
            throw new IllegalArgumentException("Country code must be between 1 and 210");
        }
    }




}
