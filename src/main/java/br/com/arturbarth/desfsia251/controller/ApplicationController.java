package br.com.arturbarth.desfsia251.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @RequestMapping
    public String index() {
        return "Bem-vindo à API de Produtos!";
    }

}
