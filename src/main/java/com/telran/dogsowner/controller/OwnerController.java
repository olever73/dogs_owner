package com.telran.dogsowner.controller;

import com.telran.dogsowner.dto.OwnerRequestDTO;
import com.telran.dogsowner.dto.OwnerResponseDTO;
import com.telran.dogsowner.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/owners")
    public void createOwner(@RequestBody OwnerRequestDTO request) {
        ownerService.createOwner(request);
    }

    @GetMapping("/owners")
    public List<OwnerResponseDTO> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/owners/{id}")
    public OwnerResponseDTO getOwnerById(@PathVariable("id") long id) {
        return ownerService.getOwnerById(id);
    }

    @PutMapping("/owners/{id}/dogs/{dog_id}")
    public void dogToggle(@PathVariable("id") long owner_id, @PathVariable("dog_id") long dog_id) {
        ownerService.dogToggle(owner_id, dog_id);
    }
}
