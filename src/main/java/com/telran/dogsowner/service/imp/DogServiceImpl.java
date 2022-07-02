package com.telran.dogsowner.service.imp;

import com.telran.dogsowner.dto.DogDTO;
import com.telran.dogsowner.entity.Dog;
import com.telran.dogsowner.repository.DogRepository;
import com.telran.dogsowner.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DogServiceImpl implements DogService {
    @Autowired
    DogRepository dogRepository;

    @Override
    public void createDog(DogDTO dogDTO) {
        Dog dog = Dog.builder()
                .id(dogDTO.getId())
                .nickname(dogDTO.getNickname())
                .breed(dogDTO.getBreed())
                //  .owner(dogDTO.getOwner())
                .registrationDate(dogDTO.getRegistrationDate())
                .build();

        dogRepository.save(dog);
    }

    @Override
    public void edit(DogDTO dogDTO) {
        Dog dog = dogRepository.findById(dogDTO.id).orElseThrow();

        dog.setBreed(dogDTO.breed);
        dog.setNickname(dogDTO.nickname);
        dog.setOwner(dogDTO.owner);
        dog.setRegistrationDate(dogDTO.registrationDate);
        dogRepository.save(dog);
    }

    @Override
    public DogDTO getDogById(Long id) {
        Dog dog = dogRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return DogDTO.builder()
                .id(dog.getId())
                .breed(dog.getBreed())
                .nickname(dog.getNickname())
                .owner(dog.getOwner())
                .dateOfBirth(dog.getDateOfBirth())
                .build();


    }


}



