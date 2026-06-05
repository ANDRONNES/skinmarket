package com.skinmarket.project.exception;

import com.skinmarket.project.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SellErrorException.class)
    public ResponseEntity<ErrorResponseDTO> handleSellException(SellErrorException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(NotFoundException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BuyErrorException.class)
    public ResponseEntity<ErrorResponseDTO> handleBuyException(BuyErrorException ex){
        ErrorResponseDTO error = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex){
        ErrorResponseDTO error = new ErrorResponseDTO(
                "Something went wrong",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(BadRequestException ex){
        ErrorResponseDTO error = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
