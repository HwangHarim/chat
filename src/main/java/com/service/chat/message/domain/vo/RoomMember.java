package com.service.chat.message.domain.vo;

import com.service.chat.message.dto.response.RoomInfo;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class RoomMember {

    private RoomInfo[] memberArr = new RoomInfo[RoomLimit.ROOM_MAX_SIZE];
    private boolean[] memberVisited = new boolean[RoomLimit.ROOM_MAX_SIZE];

    public void addMember(String memberId) {
        for (int i = 0; i < memberVisited.length; i++) {
            if (memberArr[i].getMemberId().equals(memberId)) {
                log.info(memberId, "중복된 참가 입니다.");
                return;
            } else if (!memberVisited[i]) {
                memberVisited[i] = true;
                memberArr[i] = new RoomInfo(memberId, i);
            } else if (i == RoomLimit.ROOM_MAX_SIZE - 1) {
                //방이 다 찼습니다.
                log.info(memberId, "방이 전부 찼습니다.");
                return;
            }
        }
    }

    public void deleteMember(String memberId) {
        IntStream.range(0, memberArr.length)
            .filter(i -> memberArr[i].getMemberId().equals(memberId))
            .forEach(i -> {
                memberArr[i] = null;
                memberVisited[i] = false;
            });
    }

    public int getMemberIndex(String memberId) {
        return IntStream.range(0, memberArr.length)
            .filter(i -> memberArr[i].getMemberId().equals(memberId))
            .findFirst()
            .orElse(Integer.MAX_VALUE); // 해당하는 memberId가 없을 경우 -1을 반환
    }
}
