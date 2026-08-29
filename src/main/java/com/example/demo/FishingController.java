package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class FishingController {
    private final FishingService fishingService;

    public FishingController(FishingService fishingService) {
        this.fishingService = fishingService;
    }

    @GetMapping("/fish")
    public List<Fish> getAllFish() {
        return fishingService.getAllFish();
    }

    @GetMapping("/fish/count")
    public int getFishCount() {
        return fishingService.getFishCount();
    }

    @GetMapping("/fish/biggest")
    public List<Fish> getBiggestFish(@RequestParam int k) {
        return fishingService.getBiggestFish(k);
    }

    @GetMapping("/fish/search")
    public List<Fish> getSpeciesAtLake(@RequestParam String species, @RequestParam String lake) {
        return fishingService.getSpeciesAtLake(species, lake);
    }

    @GetMapping("/fish/lake")
    public List<Fish> getFishAtLake(@RequestParam String lake) {
        return fishingService.getFishAtLake(lake);
    }

    @GetMapping("/fish/species")
        public List<Fish> getSpecies(@RequestParam String species) {
            return fishingService.getSpecies(species);
        }

    @PostMapping("/fish")
    public Fish addFish(@RequestBody Fish fish) {
        return fishingService.addFish(fish);
    }

}
