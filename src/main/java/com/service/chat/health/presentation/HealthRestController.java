package com.service.chat.health.presentation;

import com.service.chat.response.ResponseDto;
import com.service.chat.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthRestController {
    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, "health good!");
    }
}