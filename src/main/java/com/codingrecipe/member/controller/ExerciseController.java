package com.codingrecipe.member.controller;

import com.codingrecipe.member.dto.ExerciseDTO;
import com.codingrecipe.member.dto.ExerciseStatDTO;
import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.dto.ProfileDTO;
import com.codingrecipe.member.service.ExerciseService;
import com.codingrecipe.member.service.MemberService;
import com.codingrecipe.member.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final MemberService memberService;
    private final ProfileService profileService;

    @GetMapping("/exerciseAdd")
    public String addForm(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        model.addAttribute("exerciseTypes", List.of(
                "볼링", "줄넘기", "태권도", "유도", "당구", "검도", "등산", "요가", "수영", "스키", "골프",
                "탁구", "테니스", "배드민턴", "야구", "배구", "농구", "축구", "자전거", "런닝", "헬스"
        ));
        MemberDTO member = memberService.findByMemberEmail(loginEmail);
        ProfileDTO profile = profileService.findByMemberId(member.getId());
        boolean profileIncomplete =
                profile == null ||
                        profile.getWeight() == null ||
                        profile.getHeight() == null ||
                        profile.getHobby() == null || profile.getHobby().trim().isEmpty() ||
                        profile.getIntroduction() == null || profile.getIntroduction().trim().isEmpty() ||
                        profile.getFavoriteExercises() == null || profile.getFavoriteExercises().trim().isEmpty();

        if (profileIncomplete) {
            model.addAttribute("message", "프로필을 다시 입력해주세요!!");
            return "profileSetupAlert";
        }
        return "exerciseAdd";
    }

    @PostMapping("/exerciseAdd")
    public String add(@ModelAttribute ExerciseDTO exerciseDTO, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        MemberDTO member = memberService.findByMemberEmail(loginEmail);
        exerciseDTO.setMemberId(member.getId());

        ProfileDTO profile = profileService.findByMemberId(member.getId());
        double weight = profile.getWeight() != null ? profile.getWeight() : 70.0;
        Map<String, Double> metMap = Map.ofEntries(
                new AbstractMap.SimpleEntry<>("볼링", 3.8),
                new AbstractMap.SimpleEntry<>("줄넘기", 12.3),
                new AbstractMap.SimpleEntry<>("태권도", 7.8),
                new AbstractMap.SimpleEntry<>("유도", 10.3),
                new AbstractMap.SimpleEntry<>("당구", 2.5),
                new AbstractMap.SimpleEntry<>("검도", 8.0),
                new AbstractMap.SimpleEntry<>("등산", 6.0),
                new AbstractMap.SimpleEntry<>("요가", 3.0),
                new AbstractMap.SimpleEntry<>("수영", 7.0),
                new AbstractMap.SimpleEntry<>("스키", 7.0),
                new AbstractMap.SimpleEntry<>("골프", 4.8),
                new AbstractMap.SimpleEntry<>("탁구", 4.0),
                new AbstractMap.SimpleEntry<>("테니스", 7.3),
                new AbstractMap.SimpleEntry<>("배드민턴", 4.5),
                new AbstractMap.SimpleEntry<>("야구", 5.0),
                new AbstractMap.SimpleEntry<>("배구", 4.0),
                new AbstractMap.SimpleEntry<>("농구", 6.5),
                new AbstractMap.SimpleEntry<>("축구", 7.0),
                new AbstractMap.SimpleEntry<>("자전거", 7.0),
                new AbstractMap.SimpleEntry<>("런닝", 9.8),
                new AbstractMap.SimpleEntry<>("헬스", 6.0)
        );
        double met = metMap.getOrDefault(exerciseDTO.getExerciseType(), 5.0);
        double hours = exerciseDTO.getDuration() / 60.0;
        double calories = met * weight * hours * 1.05;
        exerciseDTO.setCalories(calories);

        exerciseService.save(exerciseDTO);
        return "redirect:/member/exerciseList";
    }

    @GetMapping("/exerciseList")
    public String list(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        MemberDTO member = memberService.findByMemberEmail(loginEmail);
        List<ExerciseDTO> records = exerciseService.findByMemberId(member.getId());
        double totalCalories = records.stream().mapToDouble(ExerciseDTO::getCalories).sum();
        model.addAttribute("records", records);
        model.addAttribute("totalCalories", totalCalories);
        return "exerciseList";
    }

    @GetMapping("/exerciseStat")
    public String exerciseStat(
            @RequestParam(defaultValue = "date") String type,
            HttpSession session, Model model
    ) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        MemberDTO member = memberService.findByMemberEmail(loginEmail);

        List<ExerciseStatDTO> stats;
        switch (type) {
            case "week":
                stats = exerciseService.statByWeek(member.getId());
                break;
            case "month":
                stats = exerciseService.statByMonth(member.getId());
                break;
            default:
                stats = exerciseService.statByDate(member.getId());
        }
        model.addAttribute("stats", stats);
        model.addAttribute("statType", type);
        return "exerciseStat";
    }

    @PostMapping("/exerciseDeleteByDate")
    public String deleteByDate(@RequestParam String exerciseDate, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        MemberDTO member = memberService.findByMemberEmail(loginEmail);
        exerciseService.deleteByDate(member.getId(), exerciseDate);
        return "redirect:/member/exerciseList";
    }

    @PostMapping("/exerciseDeleteById")
    public String deleteById(@RequestParam Long id, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) return "redirect:/member/login";
        exerciseService.deleteById(id);
        return "redirect:/member/exerciseList";
    }
}