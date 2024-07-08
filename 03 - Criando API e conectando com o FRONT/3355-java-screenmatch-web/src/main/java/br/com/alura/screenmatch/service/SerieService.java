package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// É uma classe que o spring vai gerenciar
// É uma classe que pertence a logica do serviço
// O Controller vai acessar ela para retirar as informações e mandar para o front
// A classe Service geralmente ela que faz a transação dos dados
// Ela que vai acessar ao banco e devolver os dados para o controller
@Service
public class SerieService {

    @Autowired
    private SerieRepository repositorio;

    public List<SerieDTO> obterTodasAsSeries() {
            return converteDados(repositorio.findAll());

    }

    public List<SerieDTO> obterTop5Series() {
        return converteDados(repositorio.findTop5ByOrderByAvaliacaoDesc());

    }

    public List<SerieDTO> obterLancamento() {
        return converteDados(repositorio.encontrarEpisodiosMaisRecentes());
    }

    private List<SerieDTO> converteDados(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getSinopse(), s.getPoster()))
                .collect(Collectors.toList());
    }


    public SerieDTO obterPorID(Long id) {
        Optional<Serie> serie = repositorio.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getSinopse(), s.getPoster());
        }
        return null;
    }


    public List<EpisodioDTO> obterTodasAsTemporadas(long id) {
        Optional<Serie> serie = repositorio.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                    .collect(Collectors.toList());
        }
        return null;
    }


    public List<EpisodioDTO> obterTemporadaPorNumeros(Long id, Long numero) {
        return repositorio.obterTemporadaPorNumeros(id, numero)
                .stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                .collect(Collectors.toList());
    }


    public List<SerieDTO> consultarPorCategoria(String nomeGenero) {

        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        List<Serie> serie = repositorio.findByGenero(categoria);
        return converteDados(serie);
    }
}
