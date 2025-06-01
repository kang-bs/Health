package com.codingrecipe.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfileDTO {
    private Long id;
    private Long memberId; // member_table의 id
    private Double weight;
    private Double height;
    private String hobby;
    private String introduction; // 자기소개
    private String favoriteExercises;
}
