package com.example.healthgenie.dto;

import com.example.healthgenie.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.sql.Blob;



@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TrainerProfileRequestDto {

    private String name;

    private String description;

    private String prize;

    private String certification;

    private Blob pics;

    private Long matchingTimes;

    private Long avgSarScore;
}
