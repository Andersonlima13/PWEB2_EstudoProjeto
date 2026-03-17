package pweb.demo.service;

import org.springframework.stereotype.Service;

import pweb.demo.dto.ParticipanteDto;
import pweb.demo.model.Participante;

@Service
public class LoginService {

    // metodo de autenticacao , porem ta criando participante/retornando e
    // verificando
    public Participante autenticar(ParticipanteDto dto) {
        if (dto.getNome() == null || dto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do participante é obrigatório");
        }

        // Converte DTO → Model
        Participante participante = new Participante(0, dto.getNome().trim(), dto.getSenha());
        return participante;
    }

    public Participante logout() {

        return null;
    }

    public Participante registrar() {

        return null;
    }

}
