package com.telran.dogsowner.service;


import com.telran.dogsowner.dto.OwnerDTO;

import java.util.List;

public interface OwnerService {


    void createOwner(OwnerDTO ownerDTO);


    List<OwnerDTO> getAllOwners();


    OwnerDTO getOwnerById(Long id);



}
