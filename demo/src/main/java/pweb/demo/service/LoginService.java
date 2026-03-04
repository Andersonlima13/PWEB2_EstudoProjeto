package pweb.demo.service;

import org.springframework.stereotype.Service;
import pweb.demo.model.Participante;

@Service
public class LoginService {

    // metodo de autenticacao , porem ta criando participante/retornando e
    // verificando
    public Participante autenticar(String nome, String senha) {
        // Validação básica
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do participante é obrigatório");
        }

        // Criar e retornar participante
        Participante participante = new Participante();
        participante.setNome(nome.trim());
        participante.setSenha(senha);
        return participante;
    }

    public Participante logout() {
        // TODO: Implement logout logic
        return null;
    }

    public Participante registrar() {
        // TODO: Implement registration logic
        return null;
    }

}
