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

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class ProfileController {

    private final MemberService memberService;
    private final ProfileService profileService;
    private final FriendService friendService;

    @GetMapping("/profile")
    public String viewProfile(@RequestParam(value = "memberId", required = false) Long memberId,
                              HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        MemberDTO me = memberService.findByMemberEmail(loginEmail);

        Long targetId = (memberId != null) ? memberId : me.getId();
        MemberDTO targetMember = memberService.finById(targetId);
        ProfileDTO profile = profileService.findByMemberId(targetId);

        boolean isFriend = friendService.isFriend(me.getId(), targetId);

        model.addAttribute("profile", profile);
        model.addAttribute("member", targetMember);
        model.addAttribute("isMe", me.getId().equals(targetId));
        model.addAttribute("isFriend", isFriend);
        return "profile";
    }

    @GetMapping("/profileEdit")
    public String editProfileForm(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/member/login";
        }
        MemberDTO member = memberService.findByMemberEmail(loginEmail);
        ProfileDTO profile = profileService.findByMemberId(member.getId());
        model.addAttribute("profile", profile);
        return "profileEdit";
    }

    @PostMapping("/profileEdit")
    public String editProfile(@ModelAttribute ProfileDTO profileDTO,
                              @RequestParam(value = "favoriteExercises", required = false) String[] favoriteExercises,
                              HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/member/login";
        }
        MemberDTO member = memberService.findByMemberEmail(loginEmail);
        profileDTO.setMemberId(member.getId());

        if (favoriteExercises != null && favoriteExercises.length > 0) {
            profileDTO.setFavoriteExercises(String.join(",", favoriteExercises));
        } else {
            profileDTO.setFavoriteExercises(null);
        }

        ProfileDTO existingProfile = profileService.findByMemberId(member.getId());
        if (existingProfile == null) {
            profileService.save(profileDTO);
        } else {
            profileService.update(profileDTO);
        }
        return "redirect:/member/main";
    }
}
