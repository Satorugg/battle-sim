package com.battlesim.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.battlesim.api.assembler.PlayerModelAssembler;
import com.battlesim.api.entity.Player;
import com.battlesim.api.exception.PlayerNotFoundException;
import com.battlesim.api.repository.PlayerRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

@RestController
public class PlayerController {
    
    private final PlayerRepository repository;
    private final PlayerModelAssembler assembler;

    PlayerController(PlayerRepository repository, PlayerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/players")
    public CollectionModel<EntityModel<Player>> all() {
        List<EntityModel<Player>> players = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(players, linkTo(methodOn(PlayerController.class).all()).withSelfRel());
    }

    @PostMapping("/players")
    public ResponseEntity<?> newPlayer(@RequestBody Player newPlayer) {
        EntityModel<Player> entityModel = assembler.toModel(repository.save(newPlayer));
        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }
    
    @GetMapping("/players/{id}")
    public EntityModel<Player> one(@PathVariable Long id) {
        Player player = repository.findById(id)
            .orElseThrow(() -> new PlayerNotFoundException(id));
        
        return assembler.toModel(player);
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
