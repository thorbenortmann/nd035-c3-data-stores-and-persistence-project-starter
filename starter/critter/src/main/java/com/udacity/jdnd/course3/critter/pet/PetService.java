package com.udacity.jdnd.course3.critter.pet;

import com.google.common.collect.Lists;
import com.udacity.jdnd.course3.critter.PetRepository;
import com.udacity.jdnd.course3.critter.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public List<Pet> findPets(List<Long> petIds) {
        return Lists.newArrayList(petRepository.findAllById(petIds));
    }
}
