package com.codingrecipe.member.service;


import com.codingrecipe.member.dto.ProfileDTO;
import com.codingrecipe.member.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public void save(ProfileDTO profileDTO) {
        profileRepository.save(profileDTO);
    }

    public void update(ProfileDTO profileDTO) {
        profileRepository.update(profileDTO);
    }

    public ProfileDTO findByMemberId(Long memberId) {
        return profileRepository.findByMemberId(memberId);
    }
}