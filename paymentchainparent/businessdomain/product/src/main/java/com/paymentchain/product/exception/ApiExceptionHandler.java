/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.product.exception;

import com.paymentchain.product.common.StandarizedApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author DELL
 */

@RestControllerAdvice
public class ApiExceptionHandler {
    
    
        //Manejador personalizado
    @ExceptionHandler(Exception.class)
    
    public ResponseEntity<?> handleUnknownHostException(Exception ex){
        StandarizedApiExceptionResponse standarizedApiExceptionResponse = new StandarizedApiExceptionResponse("TECNICO", "Input Ouput error", "1024", ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standarizedApiExceptionResponse);
    }
    
   
    @ExceptionHandler(BussinesRuleException.class)
    public ResponseEntity<?> handleBussinesRuleException(BussinesRuleException ex){
        StandarizedApiExceptionResponse standarizedApiExceptionResponse = new StandarizedApiExceptionResponse("BUSSINES", "Error de validacion", ex.getCode(), ex.getMessage());
    return ResponseEntity.status(ex.getHttpStatus()).body(standarizedApiExceptionResponse);
    }
    
}
