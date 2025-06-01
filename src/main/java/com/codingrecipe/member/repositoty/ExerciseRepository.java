package com.codingrecipe.member.repository;

import com.codingrecipe.member.dto.ExerciseDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.codingrecipe.member.dto.ExerciseStatDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ExerciseRepository {
    private final SqlSessionTemplate sql;

    public void save(ExerciseDTO exerciseDTO) {
        System.out.println("exerciseRecord" + exerciseDTO);
        sql.insert("ExerciseRecord.save", exerciseDTO);
    }

    public List<ExerciseDTO> findByMemberId(Long memberId) {
        return sql.selectList("ExerciseRecord.findByMemberId", memberId);
    }

    public List<ExerciseStatDTO> statByDate(Long memberId) {
        return sql.selectList("ExerciseRecord.statByDate", memberId);
    }
    public List<ExerciseStatDTO> statByWeek(Long memberId) {
        return sql.selectList("ExerciseRecord.statByWeek", memberId);
    }
    public List<ExerciseStatDTO> statByMonth(Long memberId) {
        return sql.selectList("ExerciseRecord.statByMonth", memberId);
    }

    public void deleteByDate(Long memberId, String exerciseDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("memberId", memberId);
        params.put("exerciseDate", exerciseDate);
        sql.delete("ExerciseRecord.deleteByDate", params);
    }

    public void deleteById(Long id) {
        sql.delete("ExerciseRecord.deleteById", id);
    }

}
