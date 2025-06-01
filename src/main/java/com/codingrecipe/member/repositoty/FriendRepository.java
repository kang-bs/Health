package com.codingrecipe.member.repository;

import com.codingrecipe.member.dto.FriendDTO;
import com.codingrecipe.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
@RequiredArgsConstructor
public class FriendRepository {
    private final SqlSessionTemplate sql;

    // 친구 요청 보내기
    public void sendFriendRequest(Long requesterId, Long addresseeId) {
        Map<String, Object> params = new HashMap<>();
        params.put("requesterId", requesterId);
        params.put("addresseeId", addresseeId);
        sql.insert("Friend.sendFriendRequest", params);
    }

    // 친구 요청 수락
    public void acceptFriendRequest(Long requesterId, Long addresseeId) {
        Map<String, Object> params = new HashMap<>();
        params.put("requesterId", requesterId);
        params.put("addresseeId", addresseeId);
        sql.update("Friend.acceptFriendRequest", params);
    }

    // 친구 요청 거절
    public void rejectFriendRequest(Long requesterId, Long addresseeId) {
        Map<String, Object> params = new HashMap<>();
        params.put("requesterId", requesterId);
        params.put("addresseeId", addresseeId);
        sql.update("Friend.rejectFriendRequest", params);
    }

    // 친구 목록 조회 (수락된 친구만)
    public List<MemberDTO> findFriends(Long memberId) {
        return sql.selectList("Friend.findFriends", memberId);
    }

    // 받은 친구 요청 목록
    public List<MemberDTO> findReceivedRequests(Long memberId) {
        return sql.selectList("Friend.findReceivedRequests", memberId);
    }

    // 보낸 친구 요청 목록
    public List<MemberDTO> findSentRequests(Long memberId) {
        return sql.selectList("Friend.findSentRequests", memberId);
    }

    public boolean isFriend(Long myId, Long otherId) {
        Map<String, Object> params = new HashMap<>();
        params.put("myId", myId);
        params.put("otherId", otherId);
        Integer count = sql.selectOne("Friend.isFriend", params);
        return count != null && count > 0;
    }

    public List<MemberDTO> findRecommendedFriends(Long myId, List<String> myInterests) {
        Map<String, Object> params = new HashMap<>();
        params.put("myId", myId);
        params.put("myInterests", myInterests);
        return sql.selectList("Friend.findRecommendedFriends", params);
    }

    public void deleteFriend(Long myId, Long friendId) {
        Map<String, Object> params = new HashMap<>();
        params.put("myId", myId);
        params.put("friendId", friendId);
        sql.delete("Friend.deleteFriend", params);
    }

    //친구 칼로리 순위
    public List<Map<String, Object>> findFriendsByCaloriesRank(Long memberId) {
        return sql.selectList("Friend.findFriendsByCaloriesRank", memberId);
    }

}