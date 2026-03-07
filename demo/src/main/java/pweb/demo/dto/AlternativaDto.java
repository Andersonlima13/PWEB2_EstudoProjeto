package pweb.demo.dto;

public class AlternativaDto {
    private long id;
    private long corridaId;
    private long perguntaId;
    private String descricao;
    private Boolean isCorreta;

    public AlternativaDto() {
    }

    public AlternativaDto(long corridaId, long perguntaId, String descricao, Boolean isCorreta) {
        this.corridaId = corridaId;
        this.perguntaId = perguntaId;
        this.descricao = descricao;
        this.isCorreta = isCorreta;
    }

    public AlternativaDto(long id, long corridaId, long perguntaId, String descricao, Boolean isCorreta) {
        this.id = id;
        this.corridaId = corridaId;
        this.perguntaId = perguntaId;
        this.descricao = descricao;
        this.isCorreta = isCorreta;
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

    public long getPerguntaId() {
        return perguntaId;
    }

    public void setPerguntaId(long perguntaId) {
        this.perguntaId = perguntaId;
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
