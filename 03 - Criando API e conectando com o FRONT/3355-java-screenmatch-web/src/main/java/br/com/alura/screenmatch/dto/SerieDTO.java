package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.Categoria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)
public record SerieDTO(Long id,
                       String titulo,
                       Integer totalTemporadas,
                       Double avaliacao,
                       Categoria genero,
                       String atores,
                       String sinopse,
                       String poster) {
}
