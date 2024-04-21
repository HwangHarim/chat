package com.service.chat.Member.application;

import com.service.chat.Member.dto.response.MemberResponse;
import com.service.chat.Member.infrastructure.MemberRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;
    public MemberResponse createMember(){
        UUID id = UUID.randomUUID();
        return new MemberResponse(id.toString().replaceAll("-",""));
    }

}
