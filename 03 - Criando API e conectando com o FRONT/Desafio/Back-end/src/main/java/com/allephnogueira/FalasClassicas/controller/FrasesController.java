package com.allephnogueira.FalasClassicas.controller;


import com.allephnogueira.FalasClassicas.DTO.FrasesDTO;
import com.allephnogueira.FalasClassicas.service.ServiceFrases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrasesController {

    @Autowired
    private ServiceFrases servico;

    @GetMapping("/series/frases")
    public FrasesDTO obterFrase(){
        return servico.obterFrase();

    }

}
