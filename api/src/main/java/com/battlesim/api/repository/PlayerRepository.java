package com.battlesim.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.battlesim.api.entity.Player;

/**
 * PlayerRepository
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {

    
}