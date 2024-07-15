package com.allephnogueira.vendas;

import com.allephnogueira.vendas.model.MinhasVendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.allephnogueira.vendas.repository.MinhasVendasRepository;

@SpringBootApplication
public class VendasPostegreSqlApplication implements CommandLineRunner {
	@Autowired
	private MinhasVendasRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(VendasPostegreSqlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		MinhasVendas venda1 = new MinhasVendas("Acai 350", 2, 6.0);
		repository.save(venda1);

		MinhasVendas venda2 = new MinhasVendas("Acai 200", 1, 4);
		repository.save(venda2);
		repository.findAll().forEach(System.out::println);
	}
}
