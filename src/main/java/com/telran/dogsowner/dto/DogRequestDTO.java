package com.telran.dogsowner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
public class DogRequestDTO {

    private String nickName;
    private String breed;
    private LocalDate dateOfBirth;
}