package com.allephnogueira.TabelaFipe.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    public String obterDados(String endereco){


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = null;
        try { // Aqui vamos fazer uma exceção, se tudo der certo faz isso se não
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) { // exibe um erro "AMIGAVEL"
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Aqui é oque vai ser devolvido se tudo der certo, dentro dessa variavel
        String json = response.body();
        return json; // Quando chamar a classe ele vai retornar essa informacao

    }
}
