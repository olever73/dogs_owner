package com.telran.dogsowner.service.imp;

import com.telran.dogsowner.dto.OwnerDTO;

import com.telran.dogsowner.entity.Owner;
import com.telran.dogsowner.repository.OwnerRepository;
import com.telran.dogsowner.service.OwnerService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    OwnerRepository ownerRepository;
    @Override
    public void createOwner(OwnerDTO ownerDTO) {
        Owner owner = Owner.builder()
                .id(ownerDTO.getId())
                .firstName(ownerDTO.getFirstName())
                .lastName(ownerDTO.getLastName())
                .dateOfBirth(ownerDTO.getDateOfBirth())
                .build();
        ownerRepository.save(owner);
    }


    @Override
    public List<OwnerDTO> getAllOwners() {

        return ownerRepository.findAll()
                .stream()
                .map(owner -> OwnerDTO.builder()
                        .id(owner.getId())
                        .firstName(owner.getFirstName())
                        .lastName(owner.getLastName())
                        .dateOfBirth(owner.getDateOfBirth())
                                .build())
                        .collect(Collectors.toList());

    }

    @Override
    public OwnerDTO getOwnerById( Long id) {
        Owner owner =ownerRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return OwnerDTO.builder()
                .id(owner.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .dateOfBirth(owner.getDateOfBirth())
                .build();
    }


}
