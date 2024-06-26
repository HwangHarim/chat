package com.service.chat.response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ResponseDto <T>{

    private final String message;
    private final String serverDateTime;
    private final T data;

    public ResponseDto(ResponseMessage message, T data){
        this.message = message.name();
        this.data = data;
        this.serverDateTime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static <T> ResponseEntity<ResponseDto<T>> toResponseEntity(ResponseMessage message, T data){
        return ResponseEntity
            .status(message.getStatus())
            .body(new ResponseDto<>(message, data));
    }
}
