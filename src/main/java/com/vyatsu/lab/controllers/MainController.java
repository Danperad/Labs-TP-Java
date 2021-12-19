package com.vyatsu.lab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @GetMapping
    public String index() {
        return "redirect:/products";
    }
}