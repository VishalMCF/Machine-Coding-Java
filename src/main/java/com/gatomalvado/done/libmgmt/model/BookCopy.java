package com.gatomalvado.done.libmgmt.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BookCopy {
    private Book book;
    private String id;
    private boolean isIssued;
    private Date dueDate;
    private int rack;
    private String issuerId;
}
