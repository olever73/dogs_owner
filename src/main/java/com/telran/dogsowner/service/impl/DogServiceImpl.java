package com.telran.dogsowner.service.impl;

import com.telran.dogsowner.dto.DogRequestDTO;
import com.telran.dogsowner.dto.DogResponseDTO;
import com.telran.dogsowner.entity.Dog;
import com.telran.dogsowner.repository.DogRepository;
import com.telran.dogsowner.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogServiceImpl implements DogService {

    @Autowired
    private DogRepository dogRepository;

    @Override
    public void createDog(DogRequestDTO request) {
        Dog dog = Dog.builder()
                .nickName(request.getNickName())
                .breed(request.getBreed())
                .dateOfBirth(request.getDateOfBirth())
                .owner(null)
                .registrationDate(null).build();

        dogRepository.save(dog);
    }

    @Override
    public DogResponseDTO getDogById(long id) {
        Dog dog = dogRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return dogToDTO(dog);
    }

    @Override
    public List<DogResponseDTO> getAllDogs() {
        List<Dog> list = dogRepository.findAll();
        return list.stream()
                .map(Dog::getId)
                .map(this::getDogById).collect(Collectors.toList());
    }

    @Override
    public List<DogResponseDTO> getAllUnregisteredDogs() {
        return dogRepository.findAllByOwnerId(null)
                .stream()
                .map(this::dogToDTO)
                .collect(Collectors.toList());
    }





    private DogResponseDTO dogToDTO(Dog dog) {
        Long ownerId;
        if (dog.getOwner() == null)
            ownerId = null;
        else ownerId = dog.getOwner().getId();

        return DogResponseDTO.builder()
                .id(dog.getId())
                .nickName(dog.getNickName())
                .breed(dog.getBreed())
                .dateOfBirth(dog.getDateOfBirth())
                .ownerId(ownerId)
                .registrationDate(dog.getRegistrationDate())
                .build();
    }
}