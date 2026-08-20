package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FishingRepository {
    private final ArrayList<Fish> fishList = new ArrayList<>();

    public FishingRepository() {
        fishList.add(new Fish("Northern Pike", 3.2, "DeMontreville"));
        fishList.add(new Fish("Bass", 4.7, "Oneka Lake"));
        fishList.add(new Fish("Bluegill", 0.6, "DeMontreville"));
    }

    public List<Fish> findAll(){
        return fishList;
    }
}
