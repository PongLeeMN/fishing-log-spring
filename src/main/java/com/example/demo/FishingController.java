package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
