package com.example.kaorureceipe.Contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.kaorureceipe.Domain.Receipe;
import com.example.kaorureceipe.Service.ReceipeService;

@Controller
@RequestMapping("")
public class ReceipeController {
    @Autowired
    private ReceipeService service;

    @RequestMapping("top")
    public String index(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size, Model model) {

        int offset = page * size;
        int totalReceipe = service.countReceipe();
        int totalPage = (int) Math.ceil((double) totalReceipe / size);
        List<Integer> pageNumbers = new ArrayList<>();
        int start = Math.max(0, page - 2);
        int end = Math.min(totalPage, page + 3);
        for (int i = start; i < end; i++) {
            pageNumbers.add(i);
        }
        List<Receipe> receipes = service.findReceipesWithPagination(offset, size);
        model.addAttribute("receipes", receipes);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("pageSize", size);
        return "/receipe/top";
    }

    @RequestMapping("detail")
    public String detail(Integer id, Model model) {
        Receipe receipe = service.load(id);
        model.addAttribute("receipe", receipe);

        return "/receipe/detail";
    }

    @RequestMapping("")
    public String deleteReceipe(Integer id) {
        service.deleteReceipe(id);

        return "redirect:/top";
    }

}
