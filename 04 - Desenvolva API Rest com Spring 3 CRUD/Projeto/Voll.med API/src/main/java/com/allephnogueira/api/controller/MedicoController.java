package com.allephnogueira.api.controller;


import com.allephnogueira.api.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public Page<DadosListagemMedico> listaDeMedicos(@PageableDefault(size = 20, sort = {"nome"}) Pageable paginacao) {
        //return repository.findAll(paginacao).map(DadosListagemMedico::new);
        // Observa que não precisa mais do stream(coleção de dados) o proprio page ja faz isso, e também apos o map ele também converte.
        // Lembrar de continuar fazendo o map, porque o repositorio vai devolver os dados de MEDICO e para segurança vamos querer transferir isso para um DTO e devolver o DTO.

        // Criando o metodo que vai retornar os medicos ativos
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

    }

    // Metodo para poder atualizar dados
    @PutMapping()
    @Transactional // Como estamos fazendo escrita, então também estamos fazendo uma transação.
    public void atualizarDados(@RequestBody @Valid DadosAtualizacaoMedicos dados) {
        // Primeiro vamos precisar trazer esses dados do banco de dados.
        // Depois que tiver os dados podemos sobreescrever os dados que queremos.
        //Vamos pegar o medico com o id que esta chegando dentro do DTO
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

    }

    // Desativar medico
    @DeleteMapping("/{id}") // parametro dinâmico
    @Transactional
    public void excluirMedico(@PathVariable Long id) {
        // Primeiro vamos carregar o medico
        var medico = repository.getReferenceById(id); // Nessa linha eu recupero o medico do banco de dados.
        // Quando eu chamar o medico eu vou setalo para falso.
        medico.excluir();
    }

}
