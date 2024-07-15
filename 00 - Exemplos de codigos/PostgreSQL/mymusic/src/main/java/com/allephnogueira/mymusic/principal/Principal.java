package com.allephnogueira.mymusic.principal;

import com.allephnogueira.mymusic.model.Genero;
import com.allephnogueira.mymusic.model.Musica;
import com.allephnogueira.mymusic.repository.MusicaRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private String cantor;
    private String nomeMusica;
    private String genero;
    private Scanner scanner = new Scanner(System.in);

    MusicaRepository repository;
    public Principal(MusicaRepository repository) {
        this.repository = repository;
    }

    public void Pergunta() {
        System.out.println("""
                MyMusic - Onde sua musica favorita nunca é esquecida.
                1 - Adicionar musica 
                2 - Minha lista de musicas
                3 - Procurar musica pelo nome
                4 - Remover musica pelo cantor
                """);
        var opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                adicionarMusicaAoBanco();
                break;
            case 2:
                listaDeMusicas();
                break;
            case 3:
                listaDeMusicasPorCantor();
                break;
            case 4:
                removerMusica();
                break;
            default:
                System.out.println("Opcao invalida.");
                break;
        }
    }



    private void adicionarMusicaAoBanco() {
        System.out.println("Quem canta ela?");
        cantor = scanner.nextLine();
        System.out.println("Qual nome dela?");
        nomeMusica = scanner.nextLine();
        System.out.println("Qual genero da musica?");
        genero = scanner.nextLine();


        Musica musica = new Musica(nomeMusica, genero, cantor );
        repository.save(musica);
        System.out.println("Musica salva com sucesso!");
        }

    private void listaDeMusicas() {
        List<Musica> ListaMusicas = repository.findAll();

        ListaMusicas.stream()
                .sorted(Comparator.comparing(Musica::getId))
                .forEach(System.out::println);
        System.out.println("\n");
    }

//    private void procurarUmaMusica() {
//        System.out.println("Qual nome da musica que voce quer localizar:");
//        nomeMusica = scanner.nextLine();
//
//        Optional<Musica> musicaBuscada = repository.findByNomeContainingIgnoreCase(nomeMusica);
//        if (musicaBuscada.isPresent()) {
//            System.out.println("Encontramos : " + musicaBuscada.get());
//        } else {
//            System.out.println("Musica nao encontrada!");
//        }
//    }

    private void listaDeMusicasPorCantor(){
        System.out.println("Digite o nome do cantor");
        cantor = scanner.nextLine();

        List<Musica> listaDeMusicaDoCantor = repository.findByNomeContainingIgnoreCase(cantor);
        if (!listaDeMusicaDoCantor.isEmpty()) {
            listaDeMusicaDoCantor.forEach(System.out::println);
        }


    }

    private void removerMusica(){
        System.out.println("Digite o nome do cantor");
        cantor = scanner.nextLine();

        int opcao = 1;
        while (opcao != 0) {
            List<Musica> listaDeMusicaDoCantor = repository.findByNomeContainingIgnoreCase(cantor);
            //IsEmpaty = vazio
            //IsPresent = tem algo dentro
            if(!listaDeMusicaDoCantor.isEmpty()) {
                System.out.println("Encontramos essas musicas:");
                listaDeMusicaDoCantor.forEach(System.out::println);
                System.out.println("Digite pelo ID a musica que você quer remover.");
                long id = scanner.nextLong();
                scanner.nextLine();

                repository.deleteById(id);
                System.out.println("Musica removida com sucesso!");

            }else {
                System.out.println("Não encontramos esse cantor em sua lista!");
                System.out.println("MyMusic\n");
                listaDeMusicas();
            }

            System.out.println("""
                        - 0 Sair
                        - 1 Remover outra musica
                        - 2 Encontrar outro cantor""");

            opcao = scanner.nextInt();
            scanner.nextLine();
            if (opcao == 2) {
                removerMusica();
            }
            System.out.println(opcao == 0? "Saindo..." : "");
        }
    }
}
