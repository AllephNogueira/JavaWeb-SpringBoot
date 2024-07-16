package com.allephnogueira.api.controller;


import com.allephnogueira.api.domain.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController // Estou dizendo que essa classe é um controller.
@RequestMapping("medicos") // Sempre quando chegar uma requisição para medicos o spring vai entender que é para chamar essa classe
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

//    @PostMapping // Estou dizendo que o tipo de dado que vai ter que chegar tem que ser um POST
//    @Transactional // Esse metodo é porque vamos ter uma transação em escrita entao precisamos ter.
//    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicos dados) { // RequestBody quer dizer: Spring pegue os dados do corpo da requisição!
//        // Valid é para pedir para o Spring se conectar com as validações que fizemos.
//        repository.save(new Medico(dados));
//
//        return ResponseEntity.noContent().build();
//    }

    // Metodo cadastrar com boas praticas.
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicos dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repository.save(medico);

        // URI é nossa URL
        // Para nao precisar ficar trocando essa URL o spring tem uma classe que faz isso para a gente. ( UriComponentsBuilder )
        // path é para passar o complemento do url, porque o uriBuild ja criou o ficando 127.0.0.1
        // path = /medicos/{id} = o id é dinamico.
        // Vamos pegar o id do medico que foi criado.
        // Para a gente poder retornar todas as informações

        // So que para pegar o id no medico que foi criado, precisamos pegar esse dado
        // Para isso usamos o construir(build) uma URL com os dados e passamos o id
        // Ai la em cima se você reparar criamos uma variavel medico que recebe um objeto medico com um construtor passando os dtos DadosCadastroMedicos
        // toUri = é para ele criar o objeto URL

        var uri = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();

        // Agora sim no body precisamos passar os dados que foram criados
        // vamos usar o DTO para exibir esses dados.
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    // Repare que vamos utilizar por verbo, se for Post ele vai chamar o cadastrar
    // Se for get ele vai chamar a lista
    // Tudo vai depender do verbo requisição

    @GetMapping()
    // Tipo de dados que vamos retornar vai ser List<>
    public ResponseEntity<Page<DadosListagemMedico>> listaDeMedicos(@PageableDefault(size = 20, sort = {"nome"}) Pageable paginacao) {
        //return repository.findAll(paginacao).map(DadosListagemMedico::new);
        // Observa que não precisa mais do stream(coleção de dados) o proprio page ja faz isso, e também apos o map ele também converte.
        // Lembrar de continuar fazendo o map, porque o repositorio vai devolver os dados de MEDICO e para segurança vamos querer transferir isso para um DTO e devolver o DTO.

        // Criando o metodo que vai retornar os medicos ativos
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);

    }

    // Metodo para poder atualizar dados
    @PutMapping()
    @Transactional // Como estamos fazendo escrita, então também estamos fazendo uma transação.
    public ResponseEntity atualizarDados(@RequestBody @Valid DadosAtualizacaoMedicos dados) {
        // Primeiro vamos precisar trazer esses dados do banco de dados.
        // Depois que tiver os dados podemos sobreescrever os dados que queremos.
        //Vamos pegar o medico com o id que esta chegando dentro do DTO
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);


        // Aqui queremos passar os dados que foram atualizados
        // Mas nao podemos passar a entidade por segurança e sim um DTO
        // Aqui mandamos ele criar um novo DTO
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

    }

    // Desativar medico
    @DeleteMapping("/{id}") // parametro dinâmico
    @Transactional
    public ResponseEntity excluirMedico(@PathVariable Long id) {
        // Primeiro vamos carregar o medico
        var medico = repository.getReferenceById(id); // Nessa linha eu recupero o medico do banco de dados.
        // Quando eu chamar o medico eu vou setalo para falso.
        medico.excluir();

        // ResponseEntity é uma classe do Spring que podemos detalhar o retorno.
        // noContent ele cria um objeto e vamos chamar o biuld para construir
        return ResponseEntity.noContent().build();

        // Agora vai retornar o 204 no Content quer dizer que foi prcessado com sucesso e nao tem conteudo na resposta.
    }


    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

    }



}
