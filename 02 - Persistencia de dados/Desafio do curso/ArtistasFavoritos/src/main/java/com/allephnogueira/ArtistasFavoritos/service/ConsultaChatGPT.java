package com.allephnogueira.ArtistasFavoritos.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConsultaChatGPT {

    private final OpenAiService openAiService;

    // Use a anotação @Value para injetar a chave API do arquivo de propriedades
    public ConsultaChatGPT(@Value("${openai.api.key}") String apiKey) {
        this.openAiService = new OpenAiService(apiKey);
    }

    public String obterInformacao(String texto) {
        try {
            CompletionRequest requisicao = CompletionRequest.builder()
                    .model("gpt-3.5-turbo")  // Troque para um modelo disponível
                    .prompt("Me fale a biografia desse cantor: " + texto)
                    .maxTokens(1000)
                    .temperature(0.7)
                    .build();

            CompletionResult resposta = openAiService.createCompletion(requisicao);

            return resposta.getChoices().get(0).getText().trim();
        } catch (Exception e) {
            // Tratamento de exceções para capturar possíveis erros
            e.printStackTrace();
            return "Ocorreu um erro ao obter a informação do ChatGPT.";
        }
    }
}
