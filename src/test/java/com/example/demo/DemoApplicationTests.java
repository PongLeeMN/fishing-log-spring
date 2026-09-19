package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FishingServiceTest {

    @Mock
    private FishingRepository fishingRepository;

    @InjectMocks
    private FishingService fishingService;



    @Test
    void updateFishById_whenFishDoesNotExist_returnsNull() {
        //ARRANGE
        long missingId = 99L;
        Fish replacementFish = new Fish ("Walleye", 5.5, "Lake Superior");

        when (fishingRepository.findById(missingId)).
                thenReturn(Optional.empty());

        //ACT
        Fish result = fishingService.updateFishById(missingId, replacementFish);

        //ASSERT
        assertNull(result);

        verify(fishingRepository).findById(missingId);
        verify(fishingRepository, never()).save(any(Fish.class));
    }

    @Test
    void updateFishById_whenFishExists_updatesAndReturnsFish() {
        //ARRANGE
        long id = 1L;
        Fish existingFish = new Fish("Bluegill", 1.1, "Lake Elmo");
        Fish replacementFish = new Fish("Bluegill", 0.87, "Lake Jane");

        when (fishingRepository.findById(id)).thenReturn(Optional.of(existingFish));
        when (fishingRepository.save(replacementFish)).thenReturn(replacementFish);

        //ACT
        Fish result = fishingService.updateFishById(id, replacementFish);

        //ASSERT
        assertEquals(replacementFish, result);
        assertEquals(id, replacementFish.getId());

        verify(fishingRepository).findById(id);
        verify(fishingRepository).save(replacementFish);
    }
}