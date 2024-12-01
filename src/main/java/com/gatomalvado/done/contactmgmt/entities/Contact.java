package com.gatomalvado.done.contactmgmt.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String countryCode;

    public String toString() {
        return "{ id: "+ id + " 'firstName': "+firstName + ", 'lastName':" + lastName + ", 'phoneNumber':  +" + countryCode + "-"+ phoneNumber +"}";
    }

}
