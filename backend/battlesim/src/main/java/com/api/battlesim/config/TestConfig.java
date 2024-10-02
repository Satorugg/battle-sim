package com.api.battlesim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

import com.api.battlesim.entity.Player;
import com.api.battlesim.repository.PlayerRepository;

@Configuration
class TestConfig {

  @Bean
  CommandLineRunner initDatabase(PlayerRepository repository) {
    return args -> {
      repository.save(new Player("Mikey"));
      repository.save(new Player("Moolie"));
    };
  }
}