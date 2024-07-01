package com.example.testeGenerico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TesteGenericoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteGenericoApplication.class, args);

		// Instanciando a classe
		Soma<String> caixaDeTexto = new Soma();
		// Guardando um texto
		caixaDeTexto.setNumero1("Guardando texto na minha caixa!");
		//Exibindo um texto
		System.out.println(caixaDeTexto.getNumero1());

		// Agora vamos exemplo de um numero
		Soma<Integer> numero = new Soma();
		numero.setNumero1(10);
		System.out.println("Numero da minha classe: " + numero.getNumero1());


		// Agora vamos um exemplo double
		Soma<Double> numeroDobule = new Soma();
		numeroDobule.setNumero1(10.0);
		System.out.println("Numero da minha classe: " + numeroDobule.getNumero1());
	}

}
