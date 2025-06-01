package com.codingrecipe.member.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExerciseDTO {
    private Long id;
    private Long memberId;
    private String exerciseDate;
    private String exerciseType;
    private Integer duration;
    private Double calories;

}

