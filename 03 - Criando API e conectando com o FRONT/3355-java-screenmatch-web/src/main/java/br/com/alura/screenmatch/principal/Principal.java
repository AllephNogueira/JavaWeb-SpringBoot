package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal  {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    // Criando minha lista de series
    private List<DadosSerie> dadosSeries = new ArrayList<>();

    private SerieRepository repository;

    private List<Serie> series = new ArrayList<>();

    private String serieBuscada;

    private Optional<Serie> serieBusca;

    // ________________________________________________________________________________

    public Principal(SerieRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu() {
        var menu = """
                1 - Buscar séries
                2 - Buscar episódios
                3 - Lista de series buscadas
                4 - Encontrar serie pelo nome
                5 - Encontrar serie pelo Ator
                6 - Buscando as TOP 5 Series
                7 - Melhores series com ate X temporadas
                8 - Buscar series por categoria
                9 - Encontrar episodio pelo nome
                10 - Buscar o top 5 episodios de uma serie
                11 - Buscar episodios por data
                
                0 - Sair                                 
                """;


        var opcao = 1;
        while (opcao != 0) {
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();


            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    buscarListaDeSeries();
                    break;
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarSeriesPorAtor();
                    break;
                case 6:
                    buscarTop5Series();
                    break;
                case 7:
                    melhoresSeriesComAteXTemporadas();
                    break;
                case 8:
                    buscarSeriesPorCategoria();
                    break;
                case 9:
                    buscarEpisodioPorTrecho();
                    break;
                case 10:
                    topMelhoresEpisodios();
                    break;
                case 11:
                    buscarEpisodioDepoisDeUmaData();
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }




    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
        // Antes de salvar eu quero ve se existe.
        if (repository.existsByTitulo(serie.getTitulo())) {
            System.out.println("Série já existe no banco de dados.");
            System.out.println("Você pode buscar pelos episodios - OPCAO 2");
        } else {
            repository.save(serie);
        }
        System.out.println(dados);

    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        serieBuscada = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + serieBuscada.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie(){

        try {
            buscarListaDeSeries();
            System.out.println("Escolha uma série pelo nome: ");
            var nomeSerie = leitura.nextLine();


            Optional<Serie> serie = series.stream()
                    .filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase()))
                    .findFirst();

            if (serie.isPresent()) {
                var serieEncontrada = serie.get();

                List<DadosTemporada> temporadas = new ArrayList<>();

                Integer totalTemporadas = serieEncontrada.getTotalTemporadas();
                if (totalTemporadas == null) {
                    System.out.println("Titulo não é uma serie ou não tem temporadas");
                } else {
                    for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                        var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                        DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                        temporadas.add(dadosTemporada);
                    }
                }
                temporadas.forEach(System.out::println);

                List<Episodio> episodios = temporadas.stream()
                        .flatMap(d -> d.episodios().stream()
                                .map(e -> new Episodio(d.numero(), e)))
                        .collect(Collectors.toList());
                serieEncontrada.setEpisodios(episodios);
                repository.save(serieEncontrada); // Salva a nova lista no banco.

            }else {
                System.out.println("Série não encontrada.");
            }
        }catch (Exception e){
            System.out.println("""
                    Tem certeza que isso é uma serie?
                    Só podemos localizar uma serie
                    """ + e.getMessage() + "\n");
        }

    }

    private void buscarListaDeSeries() {
        series = repository.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getTitulo))
                .forEach(System.out::println);
    }

    private void buscarSeriePorTitulo() {

        System.out.println("Digite o nome da serie para ver mais detalhes.");
        var nomeSerie = leitura.nextLine();

        // Trabalhando com uma lista dos dados que volta do banco de dados
        serieBusca = repository.findByTituloContainingIgnoreCase(nomeSerie);
        if (serieBusca.isPresent()) {
            System.out.println("Dados da serie: " + serieBusca.get());
        }else {
            System.out.println("Serie não encontrada");

            System.out.println("Você deseja adicionar essa serie? S/N");
            String opcao = leitura.nextLine();
            if (opcao.toLowerCase().contains("s")){
                buscarSerieWeb();
                System.out.println("Serie cadastrada com sucesso!");
            }else {
                System.out.println("Ok, saindo do programa...");
            }

        }
    }
    private void buscarSeriesPorAtor() {
        System.out.println("Qual nome do ator?");
        var nomeAtor = leitura.nextLine();

        System.out.println("Avaliação a partir de que valor?");
        var avaliacao = leitura.nextDouble();
        leitura.nextLine();

        List<Serie> seriesEncontradas = repository.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, avaliacao);
        System.out.println("Serie que " + nomeAtor + " trabalhou: ");
        seriesEncontradas.forEach(s -> System.out.println(s.getTitulo() + " avaliação: " + s.getAvaliacao()));
    }

    private void buscarTop5Series() {
        List<Serie> serieTop = repository.findTop5ByOrderByAvaliacaoDesc();
        serieTop.forEach(s -> System.out.println(s.getTitulo() + " avaliação: " + s.getAvaliacao()));
    }

    private void melhoresSeriesComAteXTemporadas() {
        System.out.println("Filtrar series com ate quantas temporadas?");
        var ateXTemporadas = leitura.nextInt();
        leitura.nextLine();


        // CONVERTENDO DADOS
        // O usuario deve digitar . ao invez de ,
        // Mas vamos acerta isso para nao ter problema oque o usuario digitar vai da certo
        System.out.println("Digite a nota mínima que você quer encontrar:");
        String notaMinima = leitura.nextLine();

        notaMinima = notaMinima.replace("," , ".");
        double notaMinimaDouble = Double.parseDouble(notaMinima);


        List<Serie> seriesCom3Temporadas = repository.seriePorTemporadaEAvaliacao(ateXTemporadas, notaMinimaDouble);
        seriesCom3Temporadas.forEach(d -> System.out.println("Titulo: " + d.getTitulo() + " - AVALIAÇÃO " + d.getAvaliacao() + " - SINOPSE: " + d.getSinopse()));
    }


    private void buscarSeriesPorCategoria() {
        System.out.println("Qual categoria você quer buscar?");
        var nomeGenero = leitura.nextLine();

        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        List<Serie> seriesPorCategoria = repository.findByGenero(categoria);
        System.out.println("Series da categoria escolhida " + nomeGenero);
        seriesPorCategoria.forEach(System.out::println);
    }
    private void buscarEpisodioPorTrecho() {
        System.out.println("Digite o nome do trecho do episodio que você quer localizar");
        String nomeTrecho = leitura.nextLine();

        List<Episodio> episodiosEncontrados = repository.episodiosPorTrechos(nomeTrecho);
        episodiosEncontrados.forEach(e -> System.out.printf("Série: %s - Temporada: %s - Episodio: %s - %s\n",
                e.getSerie().getTitulo(), e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()));

    }

    private void topMelhoresEpisodios(){
        buscarSeriePorTitulo();
        if(serieBusca.isPresent()){
            Serie serie = serieBusca.get();
            List<Episodio> topEpisodios = repository.topEpisodiosPorSerie(serie);
            topEpisodios.forEach(e -> System.out.printf("Série: %s - Temporada: %s - Episodio: %s - %s - Avaliação: %s\n",
                    e.getSerie().getTitulo(), e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo(), e.getAvaliacao()));
        }
    }

    private void buscarEpisodioDepoisDeUmaData() {
        buscarSeriePorTitulo();
        if(serieBusca.isPresent()){
            Serie serie = serieBusca.get();
            System.out.println("Digite o ano limite de lancamento: ");
            var anoLancamento = leitura.nextInt();
            leitura.nextLine();

            List<Episodio> episodiosAno = repository.episodiosPorSerieEAno(serie, anoLancamento);
            episodiosAno.forEach(System.out::println);
        }
    }
}