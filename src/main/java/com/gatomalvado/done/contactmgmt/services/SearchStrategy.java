package com.gatomalvado.done.contactmgmt.services;

import com.gatomalvado.done.contactmgmt.entities.Contact;
import com.gatomalvado.done.contactmgmt.entities.SearchResult;

public interface SearchStrategy {

    void access(Contact contact);

    void remove(Contact contact);

    SearchResult search(String fieldValue, String fieldName);

}
