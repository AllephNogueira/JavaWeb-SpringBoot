package com.allephnogueira.mymusic;

import com.allephnogueira.mymusic.model.Genero;
import com.allephnogueira.mymusic.model.Musica;
import com.allephnogueira.mymusic.principal.Principal;
import com.allephnogueira.mymusic.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MymusicApplication implements CommandLineRunner {

	@Autowired
	private MusicaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MymusicApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.Pergunta();




	}
}
