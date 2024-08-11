package com.depp.restaurant.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {

    private int statusCode;
    private String message;
    private T data;

    public ApiResponse(int statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;
    }
    public ApiResponse(int statusCode, String message, T data){
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
