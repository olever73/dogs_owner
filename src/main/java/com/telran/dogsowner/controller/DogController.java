package com.telran.dogsowner.controller;

import com.telran.dogsowner.dto.DogRequestDTO;
import com.telran.dogsowner.dto.DogResponseDTO;
import com.telran.dogsowner.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogController {

   @Autowired
   private DogService dogService;

   @PostMapping("/dogs")
   public void createDog(@RequestBody DogRequestDTO request) {
      dogService.createDog(request);
   }

   @GetMapping("/dogs")
   public List<DogResponseDTO> getAllDogs() {
      return dogService.getAllDogs();
   }

   @GetMapping("/dogs/{id}")
   public DogResponseDTO getDogById(@PathVariable("id") long id) {
      return dogService.getDogById(id);
   }

   @GetMapping("/dogs/unregistered")
   public List<DogResponseDTO> getAllUnregisteredDogs() {
      return dogService.getAllUnregisteredDogs();
   }
}