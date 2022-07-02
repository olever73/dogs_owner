package com.telran.dogsowner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
public class OwnerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
