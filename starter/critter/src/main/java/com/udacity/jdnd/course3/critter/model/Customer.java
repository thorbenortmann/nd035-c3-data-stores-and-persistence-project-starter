package com.udacity.jdnd.course3.critter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Customer extends User {

    private String phoneNumber;

    @Column(columnDefinition="TEXT")
    private String notes;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;
}
