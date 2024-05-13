package com.service.chat.message.domain.vo;

import com.service.chat.message.dto.response.RoomInfo;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
@Getter
public class RoomMember {

    private RoomInfo[] memberArr = new RoomInfo[RoomLimit.ROOM_MAX_SIZE];
    private boolean[] memberVisited = new boolean[RoomLimit.ROOM_MAX_SIZE];

    public void addMember(String memberId) {
        for (int i = 0; i < memberVisited.length; i++) {
            System.out.println(memberArr.length);
            if (!memberVisited[i]) {
                if (!isContain(memberId)) {
                    log.info(memberId, ": 입장했습니다.");
                    memberVisited[i] = true;
                    memberArr[i].setMemberId(memberId);
                    memberArr[i].setRoomIndex(i);
                }
            } else if (i == RoomLimit.ROOM_MAX_SIZE - 1) {
                log.info(memberId, "방이 전부 찼습니다.");
                return;
            }
        }
    }

    public RoomMember() {
        for (int i = 0; i < RoomLimit.ROOM_MAX_SIZE; i++) {
            memberArr[i] = new RoomInfo();
        }
    }

    public boolean isContain(String memberId) {
        for (RoomInfo info : memberArr) {
            if (info.getMemberId() != null && info.getMemberId().equals(memberId)) {
                return true;
            }
        }
        return false;
    }

    public void deleteMember(String memberId) {
        for (int i = 0; i < RoomLimit.ROOM_MAX_SIZE; i++) {
            if (memberArr[i].getMemberId().equals(memberId)) {
                memberArr[i].setMemberId(null);
                memberArr[i].setRoomIndex(-1);
                memberVisited[i] = false;
                return;
            }
        }
    }

    public int getMemberIndex(String memberId) {
        return IntStream.range(0, memberArr.length)
                .filter(i -> memberArr[i].getMemberId().equals(memberId))
                .findFirst()
                .orElse(Integer.MAX_VALUE); // 해당하는 memberId가 없을 경우 -1을 반환
    }
}
