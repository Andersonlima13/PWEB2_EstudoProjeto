package pweb.demo.service;

import org.springframework.stereotype.Service;
import pweb.demo.dto.ParticipanteDto;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipanteService {
    private Map<Long, ParticipanteDto> participantesMap = new HashMap<>();
    private long proximoId = 1;

    // Criar novo participante
    public ParticipanteDto criar(ParticipanteDto participanteDto) {
        validarParticipanteDto(participanteDto);
        participanteDto.setId(proximoId++);
        participantesMap.put(participanteDto.getId(), participanteDto);
        return participanteDto;
    }

    // Obter participante por ID
    public ParticipanteDto obter(long id) {
        validarParticipanteExiste(id);
        return participantesMap.get(id);
    }

    // Listar todos os participantes
    public List<ParticipanteDto> listarTodos() {
        return new ArrayList<>(participantesMap.values());
    }

    // Alterar participante
    public ParticipanteDto alterar(ParticipanteDto participanteDto) {
        long id = participanteDto.getId();
        validarParticipanteExiste(id);

        participantesMap.put(id, participanteDto);
        return participanteDto;
    }

    // Apagar participante
    public void apagar(long id) {
        validarParticipanteExiste(id);
        participantesMap.remove(id);
    }

    // Validar participante DTO
    private void validarParticipanteDto(ParticipanteDto participanteDto) {
        if (participanteDto.getNome() == null || participanteDto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (participanteDto.getSenha() == null || participanteDto.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }
    }

    // Validar se participante existe
    private void validarParticipanteExiste(long id) {
        if (!participantesMap.containsKey(id)) {
            throw new IllegalArgumentException("Participante com ID " + id + " não encontrado");
        }
    }
}
