package com.allephnogueira.TabelaFipe.principal;

import com.allephnogueira.TabelaFipe.model.Dados;
import com.allephnogueira.TabelaFipe.model.Modelos;
import com.allephnogueira.TabelaFipe.service.ConsumoApi;
import com.allephnogueira.TabelaFipe.service.ConverterDados;

import java.util.Comparator;
import java.util.Scanner;

public class Principal {

    Scanner leitura = new Scanner(System.in);
    ConverterDados conversor = new ConverterDados();
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";




    public void exibeMenu(){
        System.out.println("""
                Qual tipo de veiculo você quer buscar?
                1 - CARRO / 2 - MOTO / 3 - CAMINHOES""");
        int opcaoDeBusca = leitura.nextInt();
        leitura.nextLine();

        String opcaoEscolhida = "";
        switch (opcaoDeBusca){
            case 1:
                opcaoEscolhida = "carros";
                break;
            case 2:
                opcaoEscolhida = "motos";
                 break;
            case 3:
                opcaoEscolhida = "caminhoes";
                 break;
            default:
                System.out.println("Você digitou a opção errada!");
                break;
        }

        var json = consumoApi.obterDados(URL_BASE + opcaoEscolhida + "/marcas/"); // Estou pegando os dados do servidor e passando para dentro da variavel


        // Transformando os objetos em forma de lista.
        var marcas = conversor.obterLista(json, Dados.class); // Estou pegandos os dados e transformando em objetos em forma de lista.
        marcas.stream()
                        .sorted(Comparator.comparing(Dados::nome))
                                .forEach(d -> System.out.println("Codigo: " + d.codigo() + " - Marca: " + d.nome()));


// _____________________________________________________________________________________________________________________________
        // Agora vamos para a parte dos modelos.


        System.out.println("""
                Agora você deve escolher o carro pelo seu codigo!
                *** DIGITE O CODIGO DA MARCA ***""");
        int codigoVeiculo = leitura.nextInt();
        leitura.nextLine();



        json = consumoApi.obterDados(URL_BASE + opcaoEscolhida + "/marcas/" + codigoVeiculo + "/modelos/");
        // Porque estamos usando o obterDados e nao o ObterLista?
        // Porque o nosso modelo (Record) ja esta em forma de lista.
        var modeloLista = conversor.obterDados(json, Modelos.class);

        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::nome))
                .forEach(m -> System.out.println("Codigo: " + m.codigo() + " - Modelo: " + m.nome()));


        // Agora pedindo para ver mais informação sobre o carro
        // Aqui posso criar uma nova lista exibindo o preco também
        System.out.println("Digite o codigo do carro que você quer localizar:");
        var carroBuscado = leitura.nextInt();
        leitura.nextLine();
        json = consumoApi.obterDados(URL_BASE + opcaoEscolhida + "/marcas/" + codigoVeiculo + "/modelos/" + carroBuscado + "/anos/");
        var carroEncontrado = conversor.obterLista(json, Dados.class);
        carroEncontrado.stream()
                .sorted(Comparator.comparing(Dados::nome))
                .forEach(d -> System.out.println("Ano: " + d.codigo() + " - Nome: " + d.nome()));

        // Agora é a hora de perguntar qual o modelo correto

        System.out.println("Digite o codigo do modelo");
        var codigoAno = leitura.nextLine();
        json = consumoApi.obterDados(URL_BASE + opcaoEscolhida + "/marcas/" + codigoVeiculo + "/modelos/" + carroBuscado + "/anos/" + codigoAno);
        var precoCarro = conversor.obterDados(json, Dados.class);
        System.out.println("Marca - " + precoCarro.nome() + " - Modelo: " + precoCarro.modelo() + " - Ano " + precoCarro.anoModelo() + " - Combustivel " + precoCarro.combustivel() + " - Codigo Fipe:" + precoCarro.codigoFipe() + " - Valor: " + precoCarro.valor() + " - Referencia: " + precoCarro.mesReferencia());


        // Agora eu quero exibir todos os carros





    }
}
