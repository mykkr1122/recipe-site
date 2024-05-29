package com.example.kaorureceipe.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kaorureceipe.Domain.Receipe;
import com.example.kaorureceipe.Service.ReceipeService;

@Controller
@RequestMapping("")
public class ReceipeController {
    @Autowired
    private ReceipeService service;

    @RequestMapping("top")
    public String index(Model model) {
        List<Receipe> receipes = service.findAll();
        model.addAttribute("receipes", receipes);
        return "/receipe/top";
    }

    @RequestMapping("detail")
    public String detail(Integer id, Model model) {
        Receipe receipe = service.load(id);
        model.addAttribute("receipe", receipe);

        return "/receipe/detail";
    }

    @RequestMapping("")
    public String deleteReceipe(Integer id){
        service.deleteReceipe(id);

        return "redirect:/top";
    }

}
