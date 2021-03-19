package com.udacity.jdnd.course3.critter.service;

import com.google.common.collect.Lists;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public List<Pet> findPets(List<Long> petIds) {
        return Lists.newArrayList(petRepository.findAllById(petIds));
    }

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public Optional<Pet> findPetById(long petId) {
        return petRepository.findById(petId);
    }

    public List<Pet> findPetsByOwner(long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    public List<Pet> findAll() {
        return Lists.newArrayList(petRepository.findAll());
    }
}
