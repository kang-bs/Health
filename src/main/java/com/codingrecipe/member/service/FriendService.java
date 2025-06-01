package com.codingrecipe.member.service;

import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;

    // 친구 요청 보내기
    public void sendFriendRequest(Long requesterId, Long addresseeId) {
        friendRepository.sendFriendRequest(requesterId, addresseeId);
    }

    // 친구 요청 수락
    public void acceptFriendRequest(Long requesterId, Long addresseeId) {
        friendRepository.acceptFriendRequest(requesterId, addresseeId);
    }

    // 친구 요청 거절
    public void rejectFriendRequest(Long requesterId, Long addresseeId) {
        friendRepository.rejectFriendRequest(requesterId, addresseeId);
    }

    // 친구 목록 조회
    public List<MemberDTO> findFriends(Long memberId) {
        return friendRepository.findFriends(memberId);
    }

    // 받은 친구 요청 목록
    public List<MemberDTO> findReceivedRequests(Long memberId) {
        return friendRepository.findReceivedRequests(memberId);
    }

    // 보낸 친구 요청 목록
    public List<MemberDTO> findSentRequests(Long memberId) {
        return friendRepository.findSentRequests(memberId);
    }

    public boolean isFriend(Long myId, Long otherId) {
        return friendRepository.isFriend(myId, otherId);
    }
    //추천 친구
    public List<MemberDTO> findRecommendedFriends(Long myId, List<String> myInterests) {
        return friendRepository.findRecommendedFriends(myId, myInterests);
    }
    //친구 삭제
    public void deleteFriend(Long myId, Long friendId) {
        friendRepository.deleteFriend(myId, friendId);
    }

    // 친구 칼로리 순위
    public List<Map<String, Object>> findFriendsByCaloriesRank(Long memberId) {
        return friendRepository.findFriendsByCaloriesRank(memberId);
    }

}
