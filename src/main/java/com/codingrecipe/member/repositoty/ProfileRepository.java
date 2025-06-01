package com.codingrecipe.member.repository;

import com.codingrecipe.member.dto.ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProfileRepository {
    private final SqlSessionTemplate sql;

    public void save(ProfileDTO profileDTO) {
        sql.insert("Profile.save", profileDTO);
    }

    public void update(ProfileDTO profileDTO) {
        sql.update("Profile.update", profileDTO);
    }

    public ProfileDTO findByMemberId(Long memberId) {
        return sql.selectOne("Profile.findByMemberId", memberId);
    }
}