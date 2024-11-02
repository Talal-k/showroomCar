package com.example.carshowroom.exception;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ErrorResponseDto extends RuntimeException{
    private String messageEn;
    private String messageAr;
    private LocalDateTime timestamp;
}
