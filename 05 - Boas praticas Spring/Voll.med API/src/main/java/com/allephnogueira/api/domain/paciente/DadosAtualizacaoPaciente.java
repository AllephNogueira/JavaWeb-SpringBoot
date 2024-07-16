package com.allephnogueira.api.domain.paciente;

import com.allephnogueira.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String telefone,
        DadosEndereco endereco
) {
}
