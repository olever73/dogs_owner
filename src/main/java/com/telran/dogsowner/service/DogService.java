package com.telran.dogsowner.service;

import com.telran.dogsowner.dto.DogDTO;
import com.telran.dogsowner.dto.OwnerDTO;

public interface DogService {
//
    void createDog(DogDTO dogDTO);
    void edit( DogDTO dogDTO);

    DogDTO getDogById(Long id);
}
