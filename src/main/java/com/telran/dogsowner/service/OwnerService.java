package com.telran.dogsowner.service;


import com.telran.dogsowner.dto.OwnerDTO;

import java.util.List;

public interface OwnerService {

    // POST Mapping @RequestBody
    void createOwner(OwnerDTO ownerDTO);

    // GET Mapping /buildings
    List<OwnerDTO> getAllOwners();

    // GET Mapping /buildings/{id}
    OwnerDTO getOwnerById(Long id);



}
