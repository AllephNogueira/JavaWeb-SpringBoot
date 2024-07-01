package com.allephnogueira.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties (ignoreUnknown = true)
// Estamos criando o modelo porque agora vamos ter duas chaves, vindo como MODELO E ANO.
public record Modelos(List<Dados> modelos) {
}
