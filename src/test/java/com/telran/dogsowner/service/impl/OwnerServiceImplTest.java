package com.telran.dogsowner.service.impl;

import com.telran.dogsowner.dto.OwnerRequestDTO;
import com.telran.dogsowner.entity.Owner;
import com.telran.dogsowner.repository.DogRepository;
import com.telran.dogsowner.repository.OwnerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OwnerServiceImplTest {
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private DogRepository dogRepository;
    @InjectMocks
    private OwnerServiceImpl ownerServiceImpl;

    @Test
    @DisplayName("should save() owner  ")
    public void shouldSaveOwnerTest() {

        OwnerRequestDTO request = OwnerRequestDTO.builder()
                .lastName("Fillipov")
                .firstName("Kirill")
                .dateOfBirth(LocalDate.ofEpochDay(10 - 01 - 202))
                .build();


        Owner owner = Owner.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .build();

        Mockito
                .when(ownerRepository.save(
                        ArgumentMatchers.any()))
                .thenReturn(owner);

        ownerServiceImpl.createOwner(request);
        ownerRepository.save(owner);
    }

    @Test
    @DisplayName("should throw 404-NOT_FOUND, when no such owner  ")
    public void shouldThrow404WhenNoSuchOwner() {
        Long ownerId = 1L;
        HttpStatus expectedErrorStatus = HttpStatus.NOT_FOUND;

        Mockito
                .when(ownerRepository.findById(ownerId))
                .thenReturn(Optional.empty());
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class,
                () -> ownerServiceImpl.getOwnerById(ownerId)
        );
        Assertions.assertEquals(expectedErrorStatus, exception.getStatus());
        //  Assertions.assertEquals(expectedErrorMessage, exception.getReason());


    }

    @Nested
    @DisplayName("getAll method tests")
    class GetAllMethodTest {

        @Test
        @DisplayName("should return empty list if no owner")
        public void shouldReturnEmptyList() {

            var expectedSize = 0;
            Mockito
                    .when(ownerRepository.findAll())
                    .thenReturn(List.of());
            Mockito
                    .verify(dogRepository,
                            Mockito.times(0))
                    .findAll();


            var response = ownerServiceImpl.getAllOwners();
            Assertions.assertEquals(expectedSize, response.size());
        }
    }






}