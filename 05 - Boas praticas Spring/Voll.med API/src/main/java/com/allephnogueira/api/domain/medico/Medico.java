package com.allephnogueira.api.domain.medico;

import com.allephnogueira.api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table (name = "medicos") // Alterando o nome da tabela
@Entity (name = "Medico") // Alterando nome do banco
@Getter // Aqui vai gerar os metodos getter
@NoArgsConstructor // Aqui vamos gerar o construtor sem argumentos
@AllArgsConstructor // Vai ter um construtor que recebe todos os campos
@EqualsAndHashCode(of = "id") // Pesquisar mais


public class Medico {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;


    @Enumerated (EnumType.STRING)
    private Especialidade especialidade;
    @Embedded // Aqui estamos dizendo para ele ficar em uma classe separada, mas ele vai ficar no mesmo banco de dados, fazendo parte da tabela medicos.
    // Isso é somente para por o codigo mais organizado.
    // Para isso funcionar também devemos por essa anotação na classe que vamos usar no caso ENDERECO
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosCadastroMedicos dados) {
        this.ativo = true; // Como eu estou criando um novo medico ele sempre vem como ativo.
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());

    }

    public void atualizarInformacoes(DadosAtualizacaoMedicos dados) {
        // Aqui vamos pegar o DTO que esta chegando com as informações e vamos atualizar os dados
        // Vamos fazer uma condição para verificar se os dados que estao vindo são dados e nao nulos.
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    // Esse metodo vai desativar o medico
    public void excluir() {
        this.ativo = false;

    }
}
