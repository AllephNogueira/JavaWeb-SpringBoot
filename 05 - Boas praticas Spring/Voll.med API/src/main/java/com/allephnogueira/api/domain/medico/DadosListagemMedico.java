package com.allephnogueira.api.domain.medico;

public record DadosListagemMedico (
        Long id,
        String nome,
        String email,
        String telefone,
        Especialidade especialidade,
        Boolean ativo) {


    // Aqui vamos declarar as entidades que esse DTO vai trabalhar
    // Vamos colocar somente os campos que queremos devolver.

    // Vamos criar um construtor para poder trazer os dados
    // Aqui estamos trazendo os dados do Medico
    // Jogando os dados do medico para dentro
    public DadosListagemMedico(Medico medico){
       this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getEspecialidade(), medico.getAtivo());
    }

}
