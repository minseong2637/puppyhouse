package com.cos.puppyHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.puppyHouse.model.Pet;


public interface PetRepository extends JpaRepository<Pet, Integer> {
}
