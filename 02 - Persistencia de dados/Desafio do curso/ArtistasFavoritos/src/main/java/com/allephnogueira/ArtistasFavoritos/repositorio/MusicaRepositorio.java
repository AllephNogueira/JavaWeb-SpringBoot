package com.allephnogueira.ArtistasFavoritos.repositorio;

import com.allephnogueira.ArtistasFavoritos.model.Artista;
import com.allephnogueira.ArtistasFavoritos.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface MusicaRepositorio extends JpaRepository<Artista, Long> {

    // primeiro parametro quem é a entidade que estamos persistindo
    // segundo parametro o tipo do ID que nesse caso definimos como LONG

    //boolean existsByNomeIgnoreCaseAndBandaIgnoreCase(String nome, String banda);

    Optional<Artista> findByNomeContainingIgnoreCase(String nome);



    @Query ("SELECT m FROM Artista a JOIN a.musicas m WHERE a.nome ILIKE %:nome%")
    List<Musica> buscaMusicaPorArtista(String nome);



}
