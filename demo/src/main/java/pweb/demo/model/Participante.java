package pweb.demo.model;

public class Participante {
    private long id;
    private String nome;
    private String senha;

    public Participante(long id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }
}
