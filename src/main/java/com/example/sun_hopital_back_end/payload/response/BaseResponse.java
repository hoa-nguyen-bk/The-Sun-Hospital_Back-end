package com.example.sun_hopital_back_end.payload.response;

import lombok.Data;

@Data
public class BaseResponse {
    private int code;
    private String message;
    private Object data;

}
