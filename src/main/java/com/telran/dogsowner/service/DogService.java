package com.telran.dogsowner.service;

import com.telran.dogsowner.dto.DogRequestDTO;
import com.telran.dogsowner.dto.DogResponseDTO;

import java.util.List;

public interface DogService {
    void createDog(DogRequestDTO request);

    DogResponseDTO getDogById(long id);

    List<DogResponseDTO> getAllDogs();

    List<DogResponseDTO> getAllUnregisteredDogs();
}
