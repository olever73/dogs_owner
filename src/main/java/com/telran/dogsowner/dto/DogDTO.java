package com.telran.dogsowner.dto;


import com.telran.dogsowner.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
public class DogDTO {
    public Long id;
    public String breed;
    public String nickname;
    public Owner owner;
    public LocalDate registrationDate;


    private LocalDate dateOfBirth;


 ;}
