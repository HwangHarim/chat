package com.service.chat.Member.infrastructure;

import com.service.chat.Member.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    Member findByUuid(String uuid);
}
