package com.example.kaorureceipe.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kaorureceipe.Domain.Receipe;
import com.example.kaorureceipe.Form.ReceipeResisterForm;
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

    public void saveReceipe(ReceipeResisterForm form) {
        Receipe receipe = new Receipe();
        receipe.setTitle(form.getTitle());
        receipe.setIntroduction(form.getIntroduction());
        receipe.setServing(form.getServing());
        receipe.setIngredients(form.getIngredients());
        receipe.setDetail(form.getDetail());
        receipe.setPoint(form.getPoint());
        receipe.setImagePath(form.getImagePath());

        // try {
        //     String imagePath = saveImage(form.getImagePath());
        //     receipe.setImagePath(imagePath);
        // } catch (IOException e){
        //     e.getStackTrace();
        // }
        
        repository.insert(receipe);
    }

    public void deleteReceipe(Integer id){
        repository.updateDisplayFlagById(id);
    }
}
