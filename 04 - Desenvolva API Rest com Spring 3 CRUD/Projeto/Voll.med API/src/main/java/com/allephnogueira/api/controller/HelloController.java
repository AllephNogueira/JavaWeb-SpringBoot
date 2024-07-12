package com.allephnogueira.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //INFORMANDO QUE ESSA CLASSE É UM API REST CONTROLLER
@RequestMapping("/hello") // VAMOS DIZER QUAL URL ESSE CONTROLLER VAI RESPONDER
public class HelloController {


    @GetMapping // Aqui estamos dizendo que se chegar uma requisição do tipo GET e para chamar esse metodo a baixo.
    // Lembrar que no controller ele deve chamar algum metodo
    // Tipo de retorno vai ser uma string
    public String olaMundo() {
        return "Hello World Spring"; // Devemos retornar uma string
    }

}
