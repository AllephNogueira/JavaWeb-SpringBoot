package com.allephnogueira.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)
public record Dados (@JsonAlias({"codigo", "Codigo"}) String codigo,
                     @JsonAlias({"nome", "Nome", "Marca"}) String nome,
                     @JsonAlias({"Modelo", "Modelo"}) String modelo,
                     @JsonAlias("AnoModelo") Integer anoModelo,
                     @JsonAlias("Combustivel") String combustivel,
                     @JsonAlias("CodigoFipe") String codigoFipe,
                     @JsonAlias("MesReferencia") String mesReferencia,
                     @JsonAlias("Valor") String valor){

}
