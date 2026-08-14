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

    public int getFishCount() {
        return fishList.size();
    }

    public List<Fish> getBiggestFish(int k) {
        if (k > fishList.size()) {
            k = fishList.size();
        }
        if (k <= 0) {
            return new ArrayList<>();
        }

        List<Fish> list = new ArrayList<>(fishList);
        list.sort((a, b) -> Double.compare(b.getWeight(), a.getWeight()));

        List<Fish> biggestFish = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            biggestFish.add(list.get(i));
        }
        return biggestFish;
    }
}
