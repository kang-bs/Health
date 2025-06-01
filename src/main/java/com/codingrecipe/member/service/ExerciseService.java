package com.codingrecipe.member.service;

import com.codingrecipe.member.dto.ExerciseDTO;
import com.codingrecipe.member.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.codingrecipe.member.dto.ExerciseStatDTO;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public void save(ExerciseDTO exerciseDTO) {
        exerciseRepository.save(exerciseDTO);
    }

    public List<ExerciseDTO> findByMemberId(Long memberId) {
        return exerciseRepository.findByMemberId(memberId);
    }

    public List<ExerciseStatDTO> statByDate(Long memberId) {
        return exerciseRepository.statByDate(memberId);
    }
    public List<ExerciseStatDTO> statByWeek(Long memberId) {
        return exerciseRepository.statByWeek(memberId);
    }
    public List<ExerciseStatDTO> statByMonth(Long memberId) {
        return exerciseRepository.statByMonth(memberId);
    }


    public void deleteByDate(Long memberId, String exerciseDate) {
        exerciseRepository.deleteByDate(memberId, exerciseDate);
    }

    public void deleteById(Long id) {
        exerciseRepository.deleteById(id);
    }

}
