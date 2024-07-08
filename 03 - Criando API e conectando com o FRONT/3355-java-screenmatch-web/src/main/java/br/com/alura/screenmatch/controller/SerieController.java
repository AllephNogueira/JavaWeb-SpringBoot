package br.com.alura.screenmatch.controller;


import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Diz para retornar um objeto serializado em JSON ou XML como reposta.
@RequestMapping("/series")
public class SerieController {


    @Autowired
    private SerieService serieService;

    @GetMapping // Aqui ele ja vai entender que é /series
    public List<SerieDTO> obterSeries(){
        return serieService.obterTodasAsSeries();
    }

    @GetMapping("/top5") // Aqui ele ja vai entender que é /series/top5
    public List<SerieDTO> obterTop5Series(){
        return serieService.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamento(){
        return serieService.obterLancamento();
    }

    @GetMapping("/{id}") // Quando colocamos chaves e porque esse numero pode variar.
    // Repare que aqui nao fizemos o List porque queremos apenas 1 dado e nada mais.
    public SerieDTO obterPorId(@PathVariable Long id) {
        // PathVariable é para dizer que oque vai vir e uma variavel
        return serieService.obterPorID(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasAsTemporadas(@PathVariable long id){
        return serieService.obterTodasAsTemporadas(id);
    }

    // Pegando agora os epiosdios

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDTO> obterTemporadaPorNumeros(@PathVariable long id, @PathVariable Long numero){
        return serieService.obterTemporadaPorNumeros(id, numero);
    }

    @GetMapping("/categoria/{nomeGenero}")
    public List<SerieDTO> consultarPorCategoria(@PathVariable String nomeGenero){ // Vamos obter uma lista de serie pela categoria.
        return serieService.consultarPorCategoria(nomeGenero);
    }


}


