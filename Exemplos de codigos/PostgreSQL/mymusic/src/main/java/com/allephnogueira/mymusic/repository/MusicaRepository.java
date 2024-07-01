package com.allephnogueira.mymusic.repository;

import com.allephnogueira.mymusic.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MusicaRepository extends JpaRepository<Musica, Long> {

    // Procure musica pelo nome que Contenha x e ignore a forma que esta escrito
    //Optional<Musica> findByNomeContainingIgnoreCase(String nome);

    List<Musica> findByNomeContainingIgnoreCase(String nome);
}
