package com.udacity.jdnd.course3.critter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class Employee extends User {

    @ElementCollection
    Set<EmployeeSkill> skills;

    @ElementCollection
    Set<DayOfWeek> daysAvailable;

}
