package pweb.demo.model;

import java.util.List;

public class Pergunta {
    private long id;
    private long corridaId;
    private String enunciado;
    private Integer respostaCorreta;
    private List<Alternativa> alternativas;

    public Pergunta(long id, long corridaId, String enunciado, Integer respostaCorreta,
            List<Alternativa> alternativas) {
        this.id = id;
        this.corridaId = corridaId;
        this.enunciado = enunciado;
        this.respostaCorreta = respostaCorreta;
        this.alternativas = alternativas;
    }
}
