package com.example.kaorureceipe.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kaorureceipe.Domain.Receipe;
import com.example.kaorureceipe.Repository.ReceipeRepository;

@Service
@Transactional
public class ReceipeService {
    @Autowired
    private ReceipeRepository repository;

    public Receipe load(Integer id) {
        Receipe receipe = repository.load(id);
        return receipe;
    }

    public Receipe findByTitle(String title) {
        Receipe receipe = repository.findByTitle(title);
        return receipe;
    }

    public List<Receipe> findAll() {
        List<Receipe> receipes = repository.findAll();
        return receipes;
    }

    public void saveReceipe(Receipe receipe) {
        repository.insert(receipe);
    }
}
