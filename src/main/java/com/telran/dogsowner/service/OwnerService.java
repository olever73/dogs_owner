package com.telran.dogsowner.service;


import com.telran.dogsowner.dto.OwnerRequestDTO;
import com.telran.dogsowner.dto.OwnerResponseDTO;

import java.util.List;

public interface OwnerService {


    void createOwner(OwnerRequestDTO request);

    OwnerResponseDTO getOwnerById(long id);

    List<OwnerResponseDTO> getAllOwners();

    void dogToggle(long id, long dog_id);



}
