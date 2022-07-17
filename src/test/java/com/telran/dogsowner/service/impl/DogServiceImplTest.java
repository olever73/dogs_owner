package com.telran.dogsowner.service.impl;

import com.telran.dogsowner.dto.DogRequestDTO;
import com.telran.dogsowner.dto.DogResponseDTO;
import com.telran.dogsowner.entity.Dog;
import com.telran.dogsowner.entity.Owner;
import com.telran.dogsowner.repository.DogRepository;
import com.telran.dogsowner.repository.OwnerRepository;
import liquibase.pro.packaged.L;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DogServiceImplTest {
    @Mock
    private DogRepository dogRepository;
    @Mock
    private OwnerRepository ownerRepository;
    @InjectMocks
    private DogServiceImpl dogServiceImpl ;
    @Test
    @DisplayName("should save() dog,when there owner is null  ")
    public void shouldSaveDogWhenOwnerIsNullTest() {

        DogRequestDTO request = DogRequestDTO.builder()
                .nickName("Jonn")
                .breed("white")
                .dateOfBirth(LocalDate.ofEpochDay(12-01-2022))
                .build();


        Dog dog = Dog.builder()
                .nickName(request.getNickName())
                .breed(request.getBreed())
                .dateOfBirth(request.getDateOfBirth())
                .owner(null)
                .registrationDate(null).build();

        Mockito
                .when(dogRepository.save(
                        ArgumentMatchers.any()))
                .thenReturn(dog);

        dogServiceImpl.createDog(request);

    }
    @Test
    @DisplayName("should throw 404-NOT_FOUND, when no such dog  ")
    public void shouldThrow404WhenNoSuchDog() {
        Long dogId = 1L;
        HttpStatus expectedErrorStatus = HttpStatus.NOT_FOUND;
        // String expectedErrorMessage = String.format("Dog with such id [%s] does not exist", dogId);
        Mockito
                .when(dogRepository.findById(dogId))
                .thenReturn(Optional.empty());
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class,
                () ->  dogServiceImpl.getDogById(dogId)
        );
        Assertions.assertEquals(expectedErrorStatus, exception.getStatus());
        // Assertions.assertEquals(expectedErrorMessage, exception.getReason());

    }

    @Test
    @DisplayName("should get by id when dog is available  ")
    public void shouldGetByIdWhenDogIsAvailable() {
        Long dogId = 1L;
        Long ownerId = 1L;
        DogRequestDTO request = DogRequestDTO.builder()
                .nickName("Jonn")
                .breed("white")
                .dateOfBirth(LocalDate.ofEpochDay(12-01-2022))
                .build();

        Owner owner = Owner.builder()
                .firstName("Fill")
                .id(ownerId)
                .lastName("Filimonov")
                .dateOfBirth(LocalDate.ofEpochDay(12-01-2002))
                .build();
        Dog dog = Dog.builder()
                .nickName(request.getNickName())
                .breed(request.getBreed())
                .dateOfBirth(request.getDateOfBirth())
                .owner(owner)
                .registrationDate(null).build();


        Mockito
                .when(dogRepository.findById(dogId))
                .thenReturn(Optional.of(dog));


        DogResponseDTO response = dogServiceImpl.getDogById(dogId);

         Assertions.assertEquals(dog.getId(), response.getId());
        Assertions.assertEquals(dog.getNickName(), response.getNickName());
        Assertions.assertEquals(dog.getBreed(), response.getBreed());
        Assertions.assertEquals(dog.getDateOfBirth(), response.getDateOfBirth());
        Assertions.assertEquals(dog.getRegistrationDate(), response.getRegistrationDate());
        Assertions.assertNotNull(response.getOwnerId());










    }






}