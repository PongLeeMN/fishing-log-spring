package com.example.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String species;
    @Positive
    private double weight;
    @NotBlank
    private String lake;

    public Fish() {
    }

    public Fish(String species, double weight, String lake) {
        this.species = species;
        this.weight = weight;
        this.lake = lake;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getLake() {
        return lake;
    }

    public void setLake(String lake) {
        this.lake = lake;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return species + " - " + weight + " lbs - " + lake;
    }
}