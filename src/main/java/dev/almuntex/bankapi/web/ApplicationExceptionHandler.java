package dev.almuntex.bankapi.web;

import dev.almuntex.bankapi.dto.TransactionDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public TransactionDto.ValidationErrorDto handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> invalidFields = ex.getFieldErrors().stream().map(FieldError::getField).toList();
        return new TransactionDto.ValidationErrorDto(ex.getMessage(), invalidFields);
    }
}
