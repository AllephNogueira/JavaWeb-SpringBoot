package com.allephnogueira.mymusic.model;


import jakarta.persistence.*;


@Entity  // Informando que a classe vai ser um banco de dados
//@Table(name = "musicas")   // Agora personalizando o nome da tabela
public class Musica {

    // _____________ CRIANDO MEU ID E DIZENDO QUE VAI SER ALTO INCREMETAVEL _____________________

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ___________________________________________________________________________________________

    @Column(name = "nomeDaMusica") // Dizendo que agora minha coluna vai ser nome da musica
    private String nome;

    // _____________ Vamos ordernar por genero  _____________ //

//    @Enumerated(EnumType.STRING) // vai ser ordenado por STRING
    private String genero;

    // _______________________________________________________//
    private String cantor;

    public Musica(String nome, String genero, String cantor) {
        this.nome = nome;
        this.genero = genero;
        this.cantor = cantor;
    }

    public Musica() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCantor() {
        return cantor;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }


    @Override
    public String toString() {
        return  "id=" + id +
                ", nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", cantor='" + cantor + '\'';
    }
}

