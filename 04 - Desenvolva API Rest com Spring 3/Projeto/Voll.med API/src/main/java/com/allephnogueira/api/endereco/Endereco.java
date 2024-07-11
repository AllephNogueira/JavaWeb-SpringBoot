package com.allephnogueira.api.endereco;


import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable // Agora precisamos declarar aqui também para ele reconhecer.

@Getter // Aqui vai gerar os metodos getter
@NoArgsConstructor // Aqui vamos gerar o construtor sem argumentos
@AllArgsConstructor // Vai ter um construtor que recebe todos os campos


public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.cidade = dados.cidade();
        this.uf = dados.uf();

    }
}
