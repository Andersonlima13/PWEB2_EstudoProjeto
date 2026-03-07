package pweb.demo.model;

public class Alternativa {
    private long id;
    private String descricao;
    private Boolean isCorreta;

    public Alternativa(long id, String descricao, Boolean isCorreta) {
        this.id = id;
        this.descricao = descricao;
        this.isCorreta = isCorreta;
    }

}
