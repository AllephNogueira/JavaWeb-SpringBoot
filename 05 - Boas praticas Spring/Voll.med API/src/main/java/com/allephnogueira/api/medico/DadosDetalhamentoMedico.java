package com.allephnogueira.api.medico;

import com.allephnogueira.api.endereco.Endereco;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {
    // Aqui vamos receber os dados do medico

    // Agora sim vamos pegar os dados que vem la do medico e vamos colocar aqui.
    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
