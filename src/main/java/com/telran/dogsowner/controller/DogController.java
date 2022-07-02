package com.telran.dogsowner.controller;

import com.telran.dogsowner.dto.DogDTO;
import com.telran.dogsowner.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DogController {
   @Autowired
   DogService dogService;

   @PostMapping("/dogs")
   public void createDog(@RequestBody DogDTO dogDTO) {
      dogService.createDog(dogDTO);
   }

   @PutMapping("/dogs")
   public void edit(@RequestBody DogDTO dogDTO) {
      dogService.edit(dogDTO);
   }

   @GetMapping("/dogs/{id}")
   public DogDTO getDogById(@PathVariable("id") Long id) {
      return dogService.getDogById(id);
   }

}
