package com.codingrecipe.member.controller;

import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.dto.ProfileDTO;
import com.codingrecipe.member.service.FriendService;
import com.codingrecipe.member.service.MemberService;
import com.codingrecipe.member.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class FriendController {

    private final MemberService memberService;
    private final FriendService friendService;
    private final ProfileService profileService;

    @GetMapping("/friend/list")
    public String friendList(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        MemberDTO me = memberService.findByMemberEmail(loginEmail);
        List<MemberDTO> friends = friendService.findFriends(me.getId());
        model.addAttribute("friends", friends);
        return "friendList";
    }

    @GetMapping("/friend/received")
    public String receivedFriendRequests(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        MemberDTO me = memberService.findByMemberEmail(loginEmail);
        List<MemberDTO> received = friendService.findReceivedRequests(me.getId());
        model.addAttribute("receivedRequests", received);
        return "friendReceivedList";
    }

    @GetMapping("/friend/sent")
    public String sentFriendRequests(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        MemberDTO me = memberService.findByMemberEmail(loginEmail);
        List<MemberDTO> sent = friendService.findSentRequests(me.getId());
        model.addAttribute("sentRequests", sent);
        return "friendSentList";
    }

    @PostMapping("/friend/request")
    public String sendFriendRequest(@RequestParam Long toId, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        MemberDTO me = memberService.findByMemberEmail(loginEmail);
        friendService.sendFriendRequest(me.getId(), toId);
        return "redirect:/member/profile?memberId=" + toId;
    }

    @PostMapping("/friend/accept")
    public String acceptFriendRequest(@RequestParam Long fromId, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        MemberDTO me = memberService.findByMemberEmail(loginEmail);
        friendService.acceptFriendRequest(fromId, me.getId());
        return "redirect:/member/friend/received";
    }

    @PostMapping("/friend/reject")
    public String rejectFriendRequest(@RequestParam Long fromId, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        MemberDTO me = memberService.findByMemberEmail(loginEmail);
        friendService.rejectFriendRequest(fromId, me.getId());
        return "redirect:/member/friend/received";
    }

    @PostMapping("/friend/delete")
    public String deleteFriend(@RequestParam Long friendId, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        MemberDTO me = memberService.findByMemberEmail(loginEmail);
        friendService.deleteFriend(me.getId(), friendId);
        return "redirect:/member/friend/list";
    }

    @GetMapping("/recommend")
    public String recommendFriends(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        MemberDTO me = memberService.findByMemberEmail(loginEmail);
        ProfileDTO myProfile = profileService.findByMemberId(me.getId());

        // 프로필 필수값 체크
        boolean profileIncomplete =
                myProfile == null ||
                        myProfile.getWeight() == null ||
                        myProfile.getHeight() == null ||
                        myProfile.getHobby() == null || myProfile.getHobby().trim().isEmpty() ||
                        myProfile.getIntroduction() == null || myProfile.getIntroduction().trim().isEmpty() ||
                        myProfile.getFavoriteExercises() == null || myProfile.getFavoriteExercises().trim().isEmpty();

        if (profileIncomplete) {
            model.addAttribute("message", "프로필을 다시 입력해주세요!!");
            return "profileSetupAlert";
        }

        List<String> myInterests = new ArrayList<>();
        if (myProfile.getFavoriteExercises() != null && !myProfile.getFavoriteExercises().isEmpty()) {
            String[] arr = myProfile.getFavoriteExercises().split(",");
            for (String s : arr) {
                myInterests.add(s.trim());
            }
        }

        List<MemberDTO> recommendedFriends = friendService.findRecommendedFriends(me.getId(), myInterests);
        model.addAttribute("recommendedFriends", recommendedFriends);
        return "recommendFriendList";
    }

    @GetMapping("/friend/rank")
    public String friendCaloriesRank(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        MemberDTO me = memberService.findByMemberEmail(loginEmail);

        List<Map<String, Object>> rankList = friendService.findFriendsByCaloriesRank(me.getId());
        model.addAttribute("rankList", rankList);
        return "friendCaloriesRank";
    }
}
