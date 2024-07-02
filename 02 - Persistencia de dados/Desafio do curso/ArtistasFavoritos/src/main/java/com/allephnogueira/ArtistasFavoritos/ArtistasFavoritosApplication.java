package com.allephnogueira.ArtistasFavoritos;

import com.allephnogueira.ArtistasFavoritos.principal.Principal;
import com.allephnogueira.ArtistasFavoritos.repositorio.MusicaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArtistasFavoritosApplication implements CommandLineRunner {
	@Autowired
	private MusicaRepositorio repositorio;

	public static void main(String[] args) {
		SpringApplication.run(ArtistasFavoritosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.menu();
	}
}
