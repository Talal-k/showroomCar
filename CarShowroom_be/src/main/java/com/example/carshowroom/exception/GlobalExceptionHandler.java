package com.example.carshowroom.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidExceptionException(MethodArgumentNotValidException ex) {
        String errorMessageAr = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> messageSource.getMessage(Objects.requireNonNull(error.getDefaultMessage()),null,Locale.forLanguageTag("ar")))
                .orElse("Validation error occurred");
        String errorMessageEn = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> messageSource.getMessage(Objects.requireNonNull(error.getDefaultMessage()),null,Locale.forLanguageTag("en")))
                .orElse("Validation error occurred");
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .messageAr(errorMessageAr)
                .messageEn(errorMessageEn)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceAlreadyExistsException(ResourceNotFoundException ex) {
        String errorMessageAr = messageSource.getMessage(ex.getMessageKey(), null,Locale.forLanguageTag("ar"));
        String errorMessageEn = messageSource.getMessage(ex.getMessageKey(), null,Locale.forLanguageTag("en"));

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .messageAr(errorMessageAr)
                .messageEn(errorMessageEn)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(400).body(errorResponse);
    }
}
