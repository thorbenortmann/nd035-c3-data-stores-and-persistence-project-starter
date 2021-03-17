package com.udacity.jdnd.course3.critter.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee extends User {

    @ElementCollection
    Set<EmployeeSkill> skills;

    @ElementCollection
    Set<DayOfWeek> daysAvailable;

}
