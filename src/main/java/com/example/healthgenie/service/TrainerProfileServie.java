package com.example.healthgenie.service;

import com.example.healthgenie.dto.TrainerProfileModifiyResponseDto;
import com.example.healthgenie.dto.TrainerProfileModifyRequestDto;
import com.example.healthgenie.dto.TrainerProfileRequestDto;
import com.example.healthgenie.dto.TrainerProfileResponseDto;
import com.example.healthgenie.entity.TrainerProfile;
import com.example.healthgenie.entity.User;
import com.example.healthgenie.repository.TrainerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainerProfileServie {
    private final TrainerProfileRepository trainerProfileRepository;


    //trainer와 onetoone관계 - 트레이너Id넣고 save하면 같은거 찾아서 업데이트함
    //약력작성 전에 trainer정보를 미리 보내놓기 때문에 trainer정보가 틀릴일이 없다.
    //트레이너 정보를 보내는 과정에서 트레이너 id가 틀릴 경우가 검증되어있음 그냥 save하면 된다.

    //약력 작성
    public TrainerProfileResponseDto profileAdd(TrainerProfileRequestDto profileRequestDto, Long Id){

        TrainerProfile saveProfile = TrainerProfile.builder()
                .avgSarScore(profileRequestDto.getAvgSarScore())
                .name(profileRequestDto.getName())
                .description(profileRequestDto.getDescription())
                .certification(profileRequestDto.getCertification())
                .matchingTimes(profileRequestDto.getMatchingTimes())
                .pics(profileRequestDto.getPics())
                .prize(profileRequestDto.getPrize())
                .trainer(User.builder().id(Id).build())
                .build();

        TrainerProfileResponseDto result = new TrainerProfileResponseDto(trainerProfileRepository.save(saveProfile).getId());
        return result;
    }

    //약력 수정
    public TrainerProfileModifiyResponseDto profileModify(TrainerProfileModifyRequestDto dto, Long Id){
        Optional<TrainerProfile> findProfileOp = trainerProfileRepository.findById(dto.getProfileId());
        TrainerProfile findProfile = findProfileOp.get();

        TrainerProfile saveProfile = TrainerProfile.builder()
                .matchingTimes(findProfile.getMatchingTimes())
                .avgSarScore(findProfile.getAvgSarScore())
                .name(findProfile.getName())
                .certification(dto.getCertification())
                .pics(dto.getPics())
                .prize(dto.getPrize())
                .description(dto.getDescription())
                .id(findProfile.getId())
                .trainer(findProfile.getTrainer())
                .build();

        TrainerProfile resultProfile = trainerProfileRepository.save(saveProfile);
        return TrainerProfileModifiyResponseDto.builder().profileId(resultProfile.getId()).build();
    }
}
