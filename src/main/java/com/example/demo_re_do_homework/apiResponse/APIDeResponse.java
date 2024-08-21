package com.example.demo_re_do_homework.apiResponse;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIDeResponse<T> {
    private String message;
    private HttpStatus status;
    private LocalDateTime localDateTime;
}
