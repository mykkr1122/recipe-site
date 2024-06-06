package com.example.kaorureceipe.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kaorureceipe.Repository.ReceipeRepository;

@Controller
@RequestMapping("")
public class AutocompleteController {
    @Autowired
    private ReceipeRepository repository;

    @GetMapping("/searchTitle")
    public List<String> titleList() {
        List<String> titleList = repository.getTitleList();
        return titleList;
    }
}
