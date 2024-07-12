package com.allephnogueira.api.medico;

import com.allephnogueira.api.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedicos(

        @NotNull // Verifica se esta nulo
        @NotBlank // Verifica se o campo esta vazio // ESSE ELE JA VERIFICA SE É NULO E VAZIO N PRECISA USAR O NOTNULL
        String nome,

        @NotBlank
        @Email // Verifica se tem o @ o dominio..
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}") // Essa regex é uma expressão regular onde estamos dizendo que é digito + de 4 a 6 digitos
        String crm,


        @NotBlank
                @Pattern(regexp = "\\d{11,13}") // Vai ser um digito e vai ter de 11 a 13 digitos.
        String telefone,

        @NotNull // Aqui nao é uma string então é notnull
                // O proprio SPRING ja verifica se um dos dados que esta chegando e os dados que estao dentro do enum
        Especialidade especialidade,// Lembra que especialidade vai ser fixo, então vamos criar um enum.

        @NotNull
                @Valid // Preciso que ele valide também o DTO que esta dentro do DadosEndereco
        DadosEndereco endereco) {

}
