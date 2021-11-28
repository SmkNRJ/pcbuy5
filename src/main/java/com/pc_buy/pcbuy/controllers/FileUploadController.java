package com.pc_buy.pcbuy.controllers;

import com.pc_buy.pcbuy.models.Catalog;
import com.pc_buy.pcbuy.models.repo.CatalogRepository;
import com.pc_buy.pcbuy.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class FileUploadController {

@Autowired
    FileUploadService fileUploadService;
    @Autowired
    private CatalogRepository catalogRepository;

    @PostMapping ("/shop/catalog/add")
    public void uploadFile(@RequestParam String title,@RequestParam String description,@RequestParam Integer price, @RequestParam MultipartFile file, HttpServletResponse response) throws IOException {

        String result = fileUploadService.uploadFile(file);
        String l = file.getOriginalFilename();
        Catalog catalog = new Catalog(title, description, price, "images/" + result);
        catalogRepository.save(catalog);
        response.sendRedirect("/shop/catalog");
    }

}
