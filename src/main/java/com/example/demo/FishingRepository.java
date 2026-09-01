package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FishingRepository {
    private long nextId = 1;
    private final ArrayList<Fish> fishList = new ArrayList<>();

    public FishingRepository() {
        save(new Fish("Northern Pike", 3.2, "DeMontreville"));
        save(new Fish("Bass", 4.7, "Oneka Lake"));
        save(new Fish("Bluegill", 0.6, "DeMontreville"));
    }

    public List<Fish> findAll(){
        return fishList;
    }

    public Fish save(Fish fish) {
        fish.setId(nextId);
        nextId++;
        fishList.add(fish);
        return fish;
    }

    public Fish deleteFishById(long id) {
        for (int i = fishList.size() - 1; i >= 0; i--) {
            if (fishList.get(i).getId() == id) {
                return fishList.remove(i);
            }
        }
        return null;
    }

    public Fish updateFishById(long id, Fish fish) {
        for (int i = fishList.size() - 1; i >= 0; i--) {
            if (fishList.get(i).getId() == id) {
                fish.setId(id);
                fishList.set(i, fish);
                return fish;
            }
        }
        return null;
    }
}
