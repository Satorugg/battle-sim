package com.battlesim.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.battlesim.api.entity.Player;
import com.battlesim.api.exception.PlayerNotFoundException;
import com.battlesim.api.repository.PlayerRepository;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PlayerController {
    
    private final PlayerRepository repository;

    PlayerController(PlayerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/players")
    public List<Player> all() {
        return repository.findAll();
    }

    @PostMapping("/players")
    public Player newPlayer(@RequestBody Player newPlayer) {
        return repository.save(newPlayer);
    }
    
    @GetMapping("/players/{id}")
    public Player one(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new PlayerNotFoundException(id));
    }

    @PutMapping("/players/{id}")
    public Player replacePlayer(@RequestBody Player newPlayer, @PathVariable Long id) {
        return repository.findById(id)
            .map(player -> {
                player.setName(newPlayer.getName());
                return repository.save(player);
            })
            .orElseGet(() -> {
                return repository.save(newPlayer);
            });
    }

    @DeleteMapping("/players/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
