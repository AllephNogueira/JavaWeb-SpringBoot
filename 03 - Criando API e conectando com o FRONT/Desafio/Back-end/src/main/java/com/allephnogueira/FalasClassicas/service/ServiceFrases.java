package com.allephnogueira.FalasClassicas.service;


import com.allephnogueira.FalasClassicas.DTO.FrasesDTO;
import com.allephnogueira.FalasClassicas.model.Frases;
import com.allephnogueira.FalasClassicas.repository.FraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceFrases {
    @Autowired
    private FraseRepository repositorio;

    private List<FrasesDTO> converterDados(List<Frases> frases){
        return frases.stream()
                .map(f -> new FrasesDTO(f.getId(), f.getFrase(), f.getPersonagem(), f.getTitulo(), f.getPoster()))
                .collect(Collectors.toList());
    }


    public FrasesDTO obterFrase() {
        Optional<Frases> frases = repositorio.findRandom();
        if (frases.isPresent()) {
            Frases f = frases.get();
            return new FrasesDTO(f.getId(), f.getFrase(), f.getPersonagem(), f.getTitulo(), f.getPoster());
        }
        return null;
    }
}
