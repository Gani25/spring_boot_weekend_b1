package com.sprk.student_management.dto;

import lombok.Data;

@Data

public class ResponseDto<D> {

    private int statusCode;

    private String message;

    private D data;
}
