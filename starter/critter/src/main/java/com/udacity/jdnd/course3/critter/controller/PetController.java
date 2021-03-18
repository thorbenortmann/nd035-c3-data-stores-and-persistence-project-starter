package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.controller.dto.PetDTO;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        val petToSave = toPet(petDTO);
        val savedPet = petService.savePet(petToSave);
        return toPetDTO(savedPet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        val pet = petService.findPetById(petId).orElseThrow();
        return toPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        val pets = petService.findPetsByOwner(ownerId);
        return pets.stream().map(this::toPetDTO).collect(Collectors.toList());
    }

    private PetDTO toPetDTO(Pet pet) {
        val petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setType(pet.getType());
        petDTO.setName(pet.getName());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNotes());

        val owner = pet.getOwner() != null ? pet.getOwner().getId() : null;
        petDTO.setOwnerId(owner);

        return petDTO;
    }

    private Pet toPet(PetDTO petDTO) {
        val pet = new Pet();
        pet.setId(petDTO.getId());
        pet.setType(petDTO.getType());
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());

        val ownerOptional = userService.findCustomerById(petDTO.getOwnerId());
        if (ownerOptional.isPresent()) {
            val owner = ownerOptional.get();
            if (owner.getPets() == null) {
                owner.setPets(new ArrayList<>());
            }
            owner.getPets().add(pet);
            pet.setOwner(owner);
        }
        return pet;
    }
}
