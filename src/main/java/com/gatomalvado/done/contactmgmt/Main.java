package com.gatomalvado.done.contactmgmt;

import java.util.UUID;

import com.gatomalvado.done.contactmgmt.entities.Contact;
import com.gatomalvado.done.contactmgmt.services.ContactRepository;
import com.gatomalvado.done.contactmgmt.services.MapBasedStrategy;
import com.gatomalvado.done.contactmgmt.services.SearchStrategy;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Contact Mgmt System!");
        SearchStrategy searchStrategy = new MapBasedStrategy();
        ContactRepository contactRepository = new ContactRepository(searchStrategy);

        Contact ct0 = Contact.builder()
            .id(UUID.randomUUID().toString())
            .countryCode("91").firstName("f1").lastName("l1")
            .phoneNumber("8881899999").build();

        Contact ct1 = Contact.builder()
            .id(UUID.randomUUID().toString())
            .countryCode("91").firstName("f1").lastName("abl1")
            .phoneNumber("8888299999").build();

        Contact ct2 = Contact.builder()
            .id(UUID.randomUUID().toString())
            .countryCode("91").firstName("kf1").lastName("al1")
            .phoneNumber("8888839999").build();

        Contact ct3 = Contact.builder()
            .id(UUID.randomUUID().toString())
            .countryCode("91").firstName("kjsh1").lastName("cdl1")
            .phoneNumber("8888894999").build();

        Contact ct4 = Contact.builder()
            .id(UUID.randomUUID().toString())
            .countryCode("91").firstName("kjhf1").lastName("cdel1")
            .phoneNumber("8888899599").build();

        Contact ct5 = Contact.builder()
            .id(UUID.randomUUID().toString())
            .countryCode("91").firstName("ffg1").lastName("cdl2")
            .phoneNumber("8888899996").build();

        contactRepository.add(ct1); contactRepository.add(ct3); contactRepository.add(ct5); contactRepository.add(ct0);
        contactRepository.add(ct2); contactRepository.add(ct4);

        System.out.println(contactRepository.searchByFirstName("f1"));
        System.out.println(contactRepository.searchByFirstName("kj"));
        System.out.println(contactRepository.searchByFirstName("ff"));
        System.out.println(contactRepository.searchByLastName("cd"));
        System.out.println(contactRepository.searchByPhone("888889959"));
    }

}
