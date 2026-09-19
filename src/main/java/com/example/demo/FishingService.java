package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FishingService {

    private final FishingRepository fishingRepository;

    public FishingService(FishingRepository fishingRepository) {
        this.fishingRepository = fishingRepository;
    }

    public List<Fish> getAllFish() {
        return fishingRepository.findAll();
    }

    public long getFishCount() {
        return fishingRepository.count();
    }

    public List<Fish> getBiggestFish(int k) {
        List<Fish> fishList = fishingRepository.findAll();
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

    public List<Fish> getSpecies(String species) {
        List<Fish> fishList = fishingRepository.findAll();
        List<Fish> speciesList = new ArrayList<>();
        for (Fish f : fishList) {
            if (species.trim().equalsIgnoreCase(f.getSpecies())) {
                speciesList.add(f);
            }
        }
        return speciesList;
    }

    public List<Fish> getFishAtLake(String lake) {
        List<Fish> fishList = fishingRepository.findAll();
        List<Fish> fishAtLake = new ArrayList<>();

        for (Fish f: fishList) {
            if (f.getLake().trim().equalsIgnoreCase(lake.trim())) {
                fishAtLake.add(f);
            }
        }
        return fishAtLake;
    }

    public List<Fish> getSpeciesAtLake(String species, String lake) {
        List<Fish> speciesList = getSpecies(species);
        List<Fish> speciesAtLakeList = new ArrayList<>();

        for (Fish f: speciesList) {
            if (f.getLake().trim().equalsIgnoreCase(lake.trim())) {
                speciesAtLakeList.add(f);
            }
        }
        return speciesAtLakeList;
    }

    public Fish addFish(Fish fish) {
        return fishingRepository.save(fish);
    }

    public Fish deleteFishById(long id) {
        Optional<Fish> fish = fishingRepository.findById(id);

        if(fish.isEmpty()) {
            return null;
        }
        fishingRepository.deleteById(id);
        return fish.get();
    }

    public Fish updateFishById(long id, Fish fish) {
        Optional<Fish> existingFish = fishingRepository.findById(id);

        if (existingFish.isEmpty()) {
            return null;
        }
        fish.setId(id);
        return fishingRepository.save(fish);
    }
}
