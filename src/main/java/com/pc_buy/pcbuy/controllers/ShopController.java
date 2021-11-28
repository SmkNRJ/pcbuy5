package com.pc_buy.pcbuy.controllers;

import com.pc_buy.pcbuy.models.Catalog;
import com.pc_buy.pcbuy.models.repo.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ShopController {

    @Autowired
    private CatalogRepository catalogRepository;


    @GetMapping("/shop/catalog")
    public String catalog(Model model){
        Iterable<Catalog> catalogs = catalogRepository.findAll();
        model.addAttribute("catalog", catalogs);
        return "catalog";
    }

    @GetMapping("/shop/catalog/add")
    public String catalogAdd(Model model){
        return "catalog-add";
    }

/*    @PostMapping("/shop/catalog/add")
    public String shopProductAdd(@RequestParam String title,@RequestParam String description,@RequestParam Integer price,@RequestParam String picture, Model model){
        Catalog catalog = new Catalog(title, description, price, picture);
        catalogRepository.save(catalog);
        return "redirect:/shop/catalog";
    }*/

    @GetMapping("/shop/catalog/{id}")
    public String catalogDetails(@PathVariable(value = "id") long id, Model model) {
        if(!catalogRepository.existsById(id)){
            return "redirect:/shop/catalog";
        }

        Optional<Catalog> catalog = catalogRepository.findById(id);
        ArrayList<Catalog> res = new ArrayList<>();
        catalog.ifPresent(res::add);
        model.addAttribute("catalog", res);
        return "catalog-details";
    }

    @GetMapping("/shop/catalog/{id}/edit")
    public String catalogDetailsEdit(@PathVariable(value = "id") long id, Model model) {
        if(!catalogRepository.existsById(id)){
            return "redirect:/shop/catalog";
        }

        Optional<Catalog> catalog = catalogRepository.findById(id);
        ArrayList<Catalog> res = new ArrayList<>();
        catalog.ifPresent(res::add);
        model.addAttribute("catalog", res);
        return "catalog-details-edit";
    }

    @PostMapping ("/shop/catalog/{id}/edit")
    public String shopProductUpdate(@PathVariable(value = "id") long id, @RequestParam String title,@RequestParam String description,@RequestParam Integer price, @RequestParam String picture, Model model){
        Catalog catalog = catalogRepository.findById(id).orElseThrow(IllegalStateException :: new);
        catalog.setTitle(title);
        catalog.setDescription(description);
        catalog.setPrice(price);
        catalog.setPicture(picture);
        catalogRepository.save(catalog);
        return "redirect:/shop/catalog";
    }

    @PostMapping("/shop/catalog/{id}/remove")
    public String shopProductDelete(@PathVariable(value = "id") long id, Model model){
        Catalog catalog = catalogRepository.findById(id).orElseThrow(IllegalStateException :: new);
        catalogRepository.delete(catalog);
        return "redirect:/shop/catalog";
    }

    @GetMapping("/shop/order")
    public String order(Model model) {
        return "order";
    }
}
