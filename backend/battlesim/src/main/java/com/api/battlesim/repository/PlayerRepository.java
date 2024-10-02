package com.api.battlesim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.battlesim.entity.Player;

/**
 * PlayerRepository
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {

    
}