package com.allephnogueira.api.endereco;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(

							@NotNull
							String logradouro,
							@NotNull
							String bairro,
							@NotNull
							@Pattern(regexp = "\\d{8}") // Estou dizendo que vai vir digito e vai vir 8 digitos
							String cep,
							@NotNull
							String cidade,
							@NotNull
							String uf,
							@NotNull
							String numero,
							String complemento) {
}
