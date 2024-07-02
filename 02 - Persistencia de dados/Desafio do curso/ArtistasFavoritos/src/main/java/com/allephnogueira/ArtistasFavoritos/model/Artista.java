package com.allephnogueira.ArtistasFavoritos.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {  // Minha tabela

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // Dizendo que esse vai ser meu ID e que vai ser auto incremental.
    //    @Column (unique = true) Artista sendo definido como unico.
    private String nome;
    private String banda;
    @Enumerated(EnumType.STRING)
    private TipoArtista tipo;

    // Aqui devemos colocar exatamente  o nome que usamos la no Musica como atributo
    @OneToMany (mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas = new ArrayList<>();

    public Artista(String nome, TipoArtista tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    // Lembrar que sempre devemos criar o construtor padrao.
    public Artista() {

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

    public String getBanda() {
        return banda;
    }

    public void setBanda(String banda) {
        this.banda = banda;
    }

    public TipoArtista getTipo() {
        return tipo;
    }

    public void setTipo(TipoArtista tipo) {
        this.tipo = tipo;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public String toString() {
        return
                " nome='" + nome + '\'' +
                ", banda='" + banda + '\'' +
                ", tipo=" + tipo +
                ", musicas=" + musicas;
    }
}


