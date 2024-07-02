package br.com.alura.screenmatch.model;

public enum Categoria {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    AVENTURA ("Adventure", "Aventura"),
    FANTASIA ("Fantasy", "Fantasia"),
    BIOGRAFIA ("Biography", "Biografia"),
    GENERICA("Generic", "Generico");

    private String categoriaOmdb;
    private String categoriaEmPortugues;

    Categoria(String categoriaOmdb, String categoriaEmPortugues) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEmPortugues = categoriaEmPortugues;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        System.err.println("Categoria desconhecida: " + text);
        return GENERICA;
        //throw new IllegalArgumentException("Nenhuma categoria encontrada!");
    }

    public static Categoria fromPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaEmPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        System.err.println("Categoria desconhecida: " + text);
        return GENERICA;
        //throw new IllegalArgumentException("Nenhuma categoria encontrada!");
    }

}