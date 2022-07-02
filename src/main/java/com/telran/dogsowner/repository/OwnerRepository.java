package com.telran.dogsowner.repository;

import com.telran.dogsowner.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,Long> {

}
