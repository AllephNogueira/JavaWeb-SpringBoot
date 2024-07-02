package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    // primeiro parametro quem é a entidade que estamos persistindo
    // segundo parametro o tipo do ID que nesse caso definimos como LONG

    boolean existsByTitulo(String titulo);


    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, double avaliacao);

    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqualOrderByAvaliacaoDesc(int temporada, double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    // pegando por JPQL

    @Query("select s from Serie s WHERE s.totalTemporadas <= :temporada AND s.avaliacao >= :avaliacao")
    List<Serie> seriePorTemporadaEAvaliacao(int temporada, double avaliacao);


    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:nomeTrecho%")
    List<Episodio> episodiosPorTrechos(String nomeTrecho);



    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie order by e.avaliacao DESC LIMIT 5 ")
    List<Episodio> topEpisodiosPorSerie(Serie serie);


    @Query ("SELECT e FROM Serie s JOIN s.episodios e WHERE s= :serie AND YEAR(e.dataLancamento) > :anoLancamento ")
    List<Episodio> episodiosPorSerieEAno(Serie serie, int anoLancamento);
}


