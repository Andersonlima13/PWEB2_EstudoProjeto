package pweb.demo.dto;

public class ParticipanteDto {
    private long id;
    private String nome;
    private String senha;

    public ParticipanteDto() {
    }

    public ParticipanteDto(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public ParticipanteDto(long id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
