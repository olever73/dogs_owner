package com.telran.dogsowner.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class OwnerRequestDTO {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}