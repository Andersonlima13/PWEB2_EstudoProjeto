package pweb.demo.model;

import java.util.List;

public class Corrida {
    private long id;
    private Integer tempo;
    private List<Pergunta> perguntas;
    private String titulo;
    private String descricao;
    private boolean isativa;

    public Corrida(long id, Integer tempo, List<Pergunta> perguntas, String titulo, String descricao, boolean isativa) {
        this.id = id;
        this.tempo = tempo;
        this.perguntas = perguntas;
        this.titulo = titulo;
        this.descricao = descricao;
        this.isativa = isativa;
    }

}
