package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FishingService {
    private final ArrayList<Fish> fishList = new ArrayList<>();

    public FishingService() {
        fishList.add(new Fish("Northern Pike", 3.2, "DeMontreville"));
        fishList.add(new Fish("Bass", 4.7, "Oneka Lake"));
        fishList.add(new Fish("Bluegill", 0.6, "DeMontreville"));
    }

    public List<Fish> getAllFish() {
        return fishList;
    }

    public int fishCount() {
        return fishList.size();
    }
}
