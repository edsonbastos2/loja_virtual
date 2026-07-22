package com.exemploEcommerce.lojaVirtual.controller.handlers;


import com.exemploEcommerce.lojaVirtual.Dto.CustomError;
import com.exemploEcommerce.lojaVirtual.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> ressourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomError err = new CustomError(Instant.now(),status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
