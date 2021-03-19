package com.udacity.jdnd.course3.critter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
/*
It makes sense to keep Employees and Customers in different tables (InheritanceType.TABLE_PER_CLASS)
as the application never queries for all Users but instead only for either Employees or Customers.
 */
@Getter @Setter @NoArgsConstructor
public abstract class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
