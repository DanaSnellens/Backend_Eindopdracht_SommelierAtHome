package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public abstract class User {
    @Id
    @GeneratedValue
    Long id;                //Hier geen private voor?
    private String surName;
    private String lastName;
    private String email;
}
