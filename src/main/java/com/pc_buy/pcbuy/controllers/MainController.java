package com.pc_buy.pcbuy.controllers;

import com.pc_buy.pcbuy.models.Catalog;
import com.pc_buy.pcbuy.models.repo.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private CatalogRepository catalogRepository;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Catalog> catalogs = catalogRepository.findAll();
        model.addAttribute("catalog", catalogs);
        model.addAttribute("title", "Главная страница");
        return "shop-main";
    }

    @GetMapping("/main/about")
    public String about(Model model) {
        model.addAttribute("title", "О нас");
        return "about";
    }


}