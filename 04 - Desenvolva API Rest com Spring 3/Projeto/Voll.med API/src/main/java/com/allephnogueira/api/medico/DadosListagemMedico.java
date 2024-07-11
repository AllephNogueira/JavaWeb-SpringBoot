package com.allephnogueira.api.medico;

public record DadosListagemMedico (
        String nome,
        String email,
        String telefone,
        Especialidade especialidade) {


    // Aqui vamos declarar as entidades que esse DTO vai trabalhar
    // Vamos colocar somente os campos que queremos devolver.

    // Vamos criar um construtor para poder trazer os dados
    // Aqui estamos trazendo os dados do Medico
    // Jogando os dados do medico para dentro
    public DadosListagemMedico(Medico medico){
       this(medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getEspecialidade());
    }

}
