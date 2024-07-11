package com.allephnogueira.api.controller;


import com.allephnogueira.api.paciente.DadosCadastroPaciente;
import com.allephnogueira.api.paciente.Paciente;
import com.allephnogueira.api.paciente.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void cadastra (@RequestBody @Valid DadosCadastroPaciente dados) {
        // Vamos criar um novo objeto Paciente, passando os DTO *****DADOS como se fosse no argumentos.
        pacienteRepository.save(new Paciente(dados));
    }
}
