package com.pc_buy.pcbuy.controllers;

import com.pc_buy.pcbuy.models.Users;
import com.pc_buy.pcbuy.models.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller

public class AccountController {

    @GetMapping("/account/login")
    public String login(Users users){
        return "login";
    }

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping ("/account/registration")
    public void registration(@RequestParam String login, @RequestParam String password, HttpServletResponse response) throws IOException {
        Users user = new Users (login, password);
        usersRepository.save(user);
        response.sendRedirect("/shop/catalog");

    }

    @GetMapping("/account/registration")
    public String registrationPage(Model model){
        return "registration";
    }

}
