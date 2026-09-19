package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FishingRepository extends JpaRepository<Fish, Long> {
}