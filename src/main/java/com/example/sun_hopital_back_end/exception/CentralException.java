package com.example.sun_hopital_back_end.exception;

import com.example.sun_hopital_back_end.payload.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CentralException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(Exception exception) {
        BaseResponse response = new BaseResponse();
        response.setCode(500);
        response.setMessage(exception.getMessage());

        return ResponseEntity.status(500).body(response);
    }


}
