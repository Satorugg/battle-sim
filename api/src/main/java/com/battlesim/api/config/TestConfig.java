package com.battlesim.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.battlesim.api.entity.Player;
import com.battlesim.api.repository.PlayerRepository;

import org.springframework.boot.CommandLineRunner;

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
