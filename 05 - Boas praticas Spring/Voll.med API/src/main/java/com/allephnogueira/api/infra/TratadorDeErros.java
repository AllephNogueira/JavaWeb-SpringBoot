package com.allephnogueira.api.infra;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Classe responsavel por isolar o tratamento de erros.
// Anotação para indicar que essa classe vai ser uma classe para tratar erros na API
@RestControllerAdvice
public class TratadorDeErros {

    // Anotação para indicar quando que esse metodo deve ser chamado.
    @ExceptionHandler(EntityNotFoundException.class) // Aqui passamos o tipo da exception.
    // Agora o spring já sabe que em QUALQUER controller se for chamada essa exception é para chamar esse metodo
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();

        //notFound é o codigo 404 então estamos pedindo para ele construir(Build) o codigo notFound
    }


    // Erro quando tem algum campo invalido.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        // Vamos jogar os erros, ou os CAMPOS INVALIDOS dentro de uma variavel
        // Para exibir para o cliente oque esta errado
        var listaDeErros = ex.getFieldErrors();

        // Body serve para a gente colocar a "listaDeErros" para exibir no navegador
        // Agora para passar o codigo mais elegante, vamos criar um record interno na propria classe
        // E vamos pegar a lista de erros e vamos passar para esse record
        // O ::new serve para chamar o construtor desse record.
        // E vamos transformar com o MAP o listaDeErros em DadosErroValidacao
        return ResponseEntity.badRequest().body(listaDeErros.stream()
                .map(DadosErroValidacao::new)
                .toList());
    }

    // Esse e nosso record interno
    // Criamos aqui porque ele so vai ser utilizado aqui.
    private record DadosErroValidacao(String campo, String mensagem) {

        // Esse e nosso construtor ele precisa receber um objeto do tipo FieldErro
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }



}
