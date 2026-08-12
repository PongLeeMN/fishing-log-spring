package com.example.demo;

public class Fish {
    private final String species;
    private final double weight;
    private final String lake;


    public Fish(String species, double weight, String lake) {
        this.species = species;
        this.weight = weight;
        this.lake = lake;
    }


    // boiler plate
    public String getSpecies() {
        return species;
    }

    public double getWeight() {
        return weight;
    }

    public String getLake() {
        return lake;
    }


    @Override
    public String toString() {
        return species + " - " + weight + " lbs - " + lake;
    }
}
