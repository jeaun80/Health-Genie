package com.example.healthgenie.entity;

import com.example.healthgenie.dto.TrainerProfileModifyRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "trainer_profile_tb")
public class TrainerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_profile_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "prize")
    private String prize;

    @Column(name = "certification")
    private String certification;

    @Lob
    private Blob pics;


    @Column(name = "matching_times")
    private Long matchingTimes;

    @Column(name = "avg_star_score")
    private Long avgSarScore;

    @OneToOne
    @JoinColumn(name = "trainer_id")
    private User trainer;


    public void setAvgSarScore(Long avgSarScore) {
        this.avgSarScore = avgSarScore;
    }
    public TrainerProfile ModifiedProfile(TrainerProfileModifyRequestDto dto){
        this.description = dto.getDescription();
        this.certification = dto.getCertification();
        this.prize = dto.getPrize();
        this.pics = dto.getPics();
        return this;
    }
}
