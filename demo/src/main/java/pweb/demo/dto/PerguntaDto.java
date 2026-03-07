package pweb.demo.dto;

import pweb.demo.model.Alternativa;
import java.util.List;

public class PerguntaDto {
    private long id;
    private long corridaId;
    private String enunciado;
    private long respostaCorreta;
    private List<Alternativa> alternativas;

    public PerguntaDto() {
    }

    public PerguntaDto(long corridaId, String enunciado, long respostaCorreta, List<Alternativa> alternativas) {
        this.corridaId = corridaId;
        this.enunciado = enunciado;
        this.respostaCorreta = respostaCorreta;
        this.alternativas = alternativas;
    }

    public PerguntaDto(long id, long corridaId, String enunciado, long respostaCorreta,
            List<Alternativa> alternativas) {
        this.id = id;
        this.corridaId = corridaId;
        this.enunciado = enunciado;
        this.respostaCorreta = respostaCorreta;
        this.alternativas = alternativas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCorridaId() {
        return corridaId;
    }

    public void setCorridaId(long corridaId) {
        this.corridaId = corridaId;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public long getRespostaCorreta() {
        return respostaCorreta;
    }

    public void setRespostaCorreta(long respostaCorreta) {
        this.respostaCorreta = respostaCorreta;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }
}
