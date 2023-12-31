package com.robotdreams.insurance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomerNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<InsuranceAppErrorResponse> handleException(CustomerNotFoundException exc){
        InsuranceAppErrorResponse response = prepareErrorResponse(HttpStatus.NOT_FOUND, exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({VehicleNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<InsuranceAppErrorResponse> handleException(VehicleNotFoundException exc){
        InsuranceAppErrorResponse response = prepareErrorResponse(HttpStatus.NOT_FOUND, exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({CreditCardValidationException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<InsuranceAppErrorResponse> handleException(CreditCardValidationException exc){
        InsuranceAppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private InsuranceAppErrorResponse prepareErrorResponse(HttpStatus httpStatus, String message) {
        InsuranceAppErrorResponse response = new InsuranceAppErrorResponse();
        response.setStatus(httpStatus.value());
        response.setMessage(message);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }

}
