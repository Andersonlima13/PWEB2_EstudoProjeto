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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean isCorreta() {
        return isCorreta;
    }

    public void setCorreta(Boolean correta) {
        isCorreta = correta;
    }
}
