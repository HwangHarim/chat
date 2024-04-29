package com.service.chat.message.domain.vo;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class RoomMember {

    @Getter
    private List<String> members = new ArrayList<>();
    private boolean flag;

    public void addMember(String memberId) {
        if (!flag) {
            flag = true;
            members.add(memberId);
        } else if (!members.contains(memberId) && members.size() < RoomLimit.ROOM_MAX_SIZE) {
            members.add(memberId);
        }
    }

    public void deleteMember(String memberId) {
      if (members.contains(memberId) && flag) {
          if(members.size() == 1){
            flag = false;
          }
          members.remove(memberId);
      }
    }

    public int getMemberIndex(String memberId) {
        return members.indexOf(memberId);
    }
}
