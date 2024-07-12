package com.allephnogueira.api.medico;

import com.allephnogueira.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedicos(

        // Os dados que podemos atualizar

        @NotNull // Nao pode ficar em branco
        Long id,
        String telefone,
        DadosEndereco endereco

) {
}
