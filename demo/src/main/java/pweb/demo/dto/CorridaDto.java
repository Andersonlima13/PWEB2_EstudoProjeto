package pweb.demo.dto;

import java.util.List;

public class CorridaDto {
    private long id;
    private Integer tempo;
    private List<PerguntaDto> perguntas;
    private String titulo;
    private String descricao;
    private boolean isativa;

    public CorridaDto() {
    }

    public CorridaDto(Integer tempo, List<PerguntaDto> perguntas, String titulo, String descricao, boolean isativa) {
        this.tempo = tempo;
        this.perguntas = perguntas;
        this.titulo = titulo;
        this.descricao = descricao;
        this.isativa = isativa;
    }

    public CorridaDto(long id, Integer tempo, List<PerguntaDto> perguntas, String titulo, String descricao,
            boolean isativa) {
        this.id = id;
        this.tempo = tempo;
        this.perguntas = perguntas;
        this.titulo = titulo;
        this.descricao = descricao;
        this.isativa = isativa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public List<PerguntaDto> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<PerguntaDto> perguntas) {
        this.perguntas = perguntas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isIsativa() {
        return isativa;
    }

    public void setIsativa(boolean isativa) {
        this.isativa = isativa;
    }
}
