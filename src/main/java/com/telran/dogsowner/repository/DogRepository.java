package com.telran.dogsowner.repository;

import com.telran.dogsowner.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {

    public List<Dog> findAllByOwnerId(Long id);

}