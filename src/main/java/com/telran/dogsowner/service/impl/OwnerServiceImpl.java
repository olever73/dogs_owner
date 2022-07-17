package com.telran.dogsowner.service.impl;

import com.telran.dogsowner.dto.DogResponseDTO;
import com.telran.dogsowner.dto.OwnerRequestDTO;
import com.telran.dogsowner.dto.OwnerResponseDTO;
import com.telran.dogsowner.entity.Dog;
import com.telran.dogsowner.entity.Owner;
import com.telran.dogsowner.repository.DogRepository;
import com.telran.dogsowner.repository.OwnerRepository;
import com.telran.dogsowner.service.OwnerService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private DogRepository dogRepository;

    @Override
    public void createOwner(OwnerRequestDTO request) {
        Owner owner = Owner.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .build();

        ownerRepository.save(owner);
    }

    @Override
    public OwnerResponseDTO getOwnerById(long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<Dog> list = dogRepository.findAllByOwnerId(id);

        List<DogResponseDTO> listDTO = list.stream()
                .map(dog -> DogResponseDTO.builder()
                        .id(dog.getId())
                        .nickName(dog.getNickName())
                        .breed(dog.getBreed())
                        .dateOfBirth(dog.getDateOfBirth())
                        .ownerId(dog.getOwner().getId())
                        .registrationDate(dog.getRegistrationDate()).build())
                .collect(Collectors.toList());

        return OwnerResponseDTO.builder()
                .id(owner.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .dateOfBirth(owner.getDateOfBirth())
                .dogsList(listDTO).build();
    }

    @Override
    public List<OwnerResponseDTO> getAllOwners() {
        List<Owner> list = ownerRepository.findAll();
        return list.stream()
                .map(Owner::getId)
                .map(this::getOwnerById).collect(Collectors.toList());
    }

    @Override
    public void dogToggle(long ownerId, long dogId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Dog dog = dogRepository.findById(dogId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (dog.getOwner() != null && !dog.getOwner().getId().equals(ownerId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        dog.setOwner(dog.getOwner() == null ? owner : null);
        dog.setRegistrationDate(dog.getOwner() == null ? LocalDate.now() : null);

        dogRepository.save(dog);
    }


}