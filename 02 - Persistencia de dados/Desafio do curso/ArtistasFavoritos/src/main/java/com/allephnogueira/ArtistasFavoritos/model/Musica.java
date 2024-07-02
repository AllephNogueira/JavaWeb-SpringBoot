package com.allephnogueira.ArtistasFavoritos.model;


import jakarta.persistence.*;

@Entity
@Table (name = "musicas")
public class Musica {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToOne
    private Artista artista;

    public Musica(String nomeMusica) {
        this.titulo = nomeMusica;
    }

    public Musica() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return   "titulo=" + titulo ; // Nome da musica
               // ", artista=" + artista.getNome(); // Lembrar que devemos por isso se não ele vai pegar todos os dados do artista
        // e queremos somente o nome.
    }
}