package com.codingrecipe.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExerciseStatDTO {
    private String period;
    private Integer totalDuration;
    private Double totalCalories;
}
