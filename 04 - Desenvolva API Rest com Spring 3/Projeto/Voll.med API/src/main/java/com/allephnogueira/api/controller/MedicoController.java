package com.allephnogueira.api.controller;


import com.allephnogueira.api.endereco.Endereco;
import com.allephnogueira.api.medico.DadosCadastroMedicos;
import com.allephnogueira.api.medico.DadosListagemMedico;
import com.allephnogueira.api.medico.Medico;
import com.allephnogueira.api.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController // Estou dizendo que essa classe é um controller.
@RequestMapping("medicos") // Sempre quando chegar uma requisição para medicos o spring vai entender que é para chamar essa classe
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping // Estou dizendo que o tipo de dado que vai ter que chegar tem que ser um POST
    @Transactional // Esse metodo é porque vamos ter uma transação em escrita entao precisamos ter.
    public void cadastrar(@RequestBody @Valid DadosCadastroMedicos dados) { // RequestBody quer dizer: Spring pegue os dados do corpo da requisição!
        // Valid é para pedir para o Spring se conectar com as validações que fizemos.
        repository.save(new Medico(dados));
    }

    // Repare que vamos utilizar por verbo, se for Post ele vai chamar o cadastrar
    // Se for get ele vai chamar a lista
    // Tudo vai depender do verbo requisição

    @GetMapping()
    // Tipo de dados que vamos retornar vai ser List<>
    public Page<DadosListagemMedico> listaDeMedicos(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
        // Observa que não precisa mais do stream(coleção de dados) o proprio page ja faz isso, e também apos o map ele também converte.
        // Lembrar de continuar fazendo o map, porque o repositorio vai devolver os dados de MEDICO e para segurança vamos querer transferir isso para um DTO e devolver o DTO.
    }

}
