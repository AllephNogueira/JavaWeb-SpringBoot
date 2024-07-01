package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.service.traducao.ConsultaMyMemory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

// Entity dizendo que essa classe vai ser uma tabela do banco

@Entity

// Agora vamos personalizar o nome da classe - tabela
@Table(name = "series")


public class Serie {

    // Criando meu ID = é obrigatorio em bancos de dados Relacional
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Identity ele vai gerar um ID incremental.
    private Long id; // Long é um inteiro que pode ser bem grande.

    // aqui estamos personalizando o nome da coluna, no caso ela estava como Titulo, mas agora vai esta como nomeDaSerie
    // Dizendo que o nome da serie nao pode ser repetido
    @Column(name = "nomeDaSerie", unique = true)
    private String titulo;
    private Integer totalTemporadas;
    private Double avaliacao;
    @Enumerated(EnumType.STRING) // Dizendo que o genero é uma categoria e ele vai ordernar por STRING / poderia ser por numero também
    private Categoria genero;
    private String atores;
    private String sinopse;
    private String poster;

    //@Transient // Aqui estou dizendo para ignorar essa informação

    // Um para muitos
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // Por onde esse mapeamento ta na outra classe.
    // Aqui vamos adicionar o cascade para = sempre que tudo for atualizado, seja um episodio ele vai persistir os dados e fazer a atualização no SQL
    private List<Episodio> episodios = new ArrayList<>();


    public Serie(DadosSerie dadosSerie){
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();

        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0.0);

        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        this.atores = dadosSerie.atores();
        this.sinopse = ConsultaMyMemory.obterTraducao(dadosSerie.sinopse()).trim(); // Traduzindo a sinopse antes de exibir os dados.
        this.poster = dadosSerie.poster();
    }

    public Serie() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return  "genero=" + genero +
                ", titulo='" + titulo + '\'' +
                ", totalTemporadas=" + totalTemporadas +
                ", avaliacao=" + avaliacao +
                ", atores='" + atores + '\'' +
                ", sinopse='" + sinopse + '\'' +
                ", poster='" + poster + '\''+
                ", episodios='" + episodios + '\'';
    }
}
