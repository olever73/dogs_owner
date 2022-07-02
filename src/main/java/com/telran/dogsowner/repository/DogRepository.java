package com.telran.dogsowner.repository;

import com.telran.dogsowner.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog,Long> {
}
