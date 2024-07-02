package com.allephnogueira.ArtistasFavoritos.principal;

import com.allephnogueira.ArtistasFavoritos.model.Artista;
import com.allephnogueira.ArtistasFavoritos.model.Musica;
import com.allephnogueira.ArtistasFavoritos.model.TipoArtista;
import com.allephnogueira.ArtistasFavoritos.repositorio.MusicaRepositorio;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private String nome;
    private final MusicaRepositorio repositorio;



    public Principal(MusicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }


    public void menu(){
        var opcao = 0;
        while (opcao != 9){

            System.out.println("""
                
                1 - Cadastrar artista
                2 - Cadastrar musica
                3 - Lista de musicas
                4 - Lista de artistas
                5 - Busca musicas de um artista
                6 - Pesquisar dados de um artista 
                
                9 - Sair
                """);

            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao){
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listaDeMusicas();
                    break;
                case 4:
                    listaDeArtistas();
                    break;
                case 5:
                    buscarMusicaPorArtista();
                    break;
                case 6:
                    pesquisarDadosSobreArtista();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção não encontrada!");

            }
        }

    }

    private void cadastrarArtista() {
        var cadastrarNovo = "S";
        while (cadastrarNovo.equalsIgnoreCase("s")) {
            System.out.println("Informe o nome do artista: ");
            nome = leitura.nextLine();
            System.out.println("Informe o tipo do artista: (SOLO, DUPLA, BANDA?)");
            var tipo = leitura.nextLine();

            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nome, tipoArtista);


            if(!tipo.equalsIgnoreCase("SOLO")){
                System.out.println("Digite o nome da banda:");
                var nomeBanda = leitura.nextLine();
                artista.setBanda(nomeBanda);
            }


            repositorio.save(artista);

            System.out.println("Cadastrar outro artista? S/N");
            cadastrarNovo = leitura.nextLine();
        }
    }

    private void cadastrarMusica() {
        // Primeiro devemos cadastra o artista, antes de cadastra a musica, mas vamos verificar se esse artista já esta cadastrado.
        System.out.println("Cadastrar musica de qual artista?");
        nome = leitura.nextLine();
        // Vamos fazer um optinal para isso
        // para verificar
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);
        if(artista.isPresent()) {
            System.out.println("Digite o nome da musica:");
            var nomeMusica = leitura.nextLine();


            //Criar uma musica e vincular a um artista
            // Criando a musica
            Musica musica = new Musica(nomeMusica);

            musica.setArtista(artista.get()); // Passando para musica quem é o artista
            // Se tiver duvida de como o banco vai entender isso é só lembrar que pedimos para informar o artista antes.
            // Ali no optinal a gente verificar se esse artista existe, e se existir passamos a musica

            artista.get().getMusicas().add(musica); // Lembrar que criamos uma lista la em Artistas?
            // estamos passando agora para ela o nome da musica para ele associar.


            repositorio.save(artista.get()); // Agora vamos adicionar a musica do artista ao banco
            // Como meu repositorio e de artista quem tem musica, devemos seta o artista.get
        }else {
            System.out.println("""
                    Artista não encontrado:
                    Você deve primeiro cadastrar o artista e depois a musica!
                    Vamos cadastrar o artista primeiro!""");
            cadastrarArtista();
        }
    }

    private void listaDeMusicas() {
        List<Artista> artistasList = repositorio.findAll();
        artistasList.forEach(System.out::println);
    }

    private void listaDeArtistas() {
        List<Artista> artistasList = repositorio.findAll();

        for (Artista artista : artistasList) {
            if(artista.getBanda() != null){
                System.out.println("Nome: " + artista.getNome() + " - banda: " + artista.getBanda());
            }else {
                System.out.println("Nome: " + artista.getNome());
            }

        }
    }

    private void buscarMusicaPorArtista(){
        // Busca no banco somente musica daquele artista
        System.out.println("Buscar musica de qual artista?");
        var nome = leitura.nextLine();
        List<Musica> musicas = repositorio.buscaMusicaPorArtista(nome);
        musicas.forEach(System.out::println);

    }

    private void pesquisarDadosSobreArtista() {

    }


}
