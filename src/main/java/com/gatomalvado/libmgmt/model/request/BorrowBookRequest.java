package com.gatomalvado.libmgmt.model.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class BorrowBookRequest {
    private String bookId;
    private String userId;
    private Date dueDate;
}
