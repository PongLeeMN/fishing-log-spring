package com.example.demo;

public class Fish {
    private String species;
    private double weight;
    private String lake;
    private long id;

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

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return species + " - " + weight + " lbs - " + lake;
    }
}