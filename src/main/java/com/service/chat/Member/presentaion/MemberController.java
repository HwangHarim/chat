package com.service.chat.Member.presentaion;

import com.service.chat.Member.application.MemberService;
import com.service.chat.response.ResponseDto;
import com.service.chat.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<?> getUUID(){
        var id = memberService.createMember();

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, id);
    }
}
