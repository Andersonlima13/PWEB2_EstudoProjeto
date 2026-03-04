package pweb.demo.service;

import org.springframework.stereotype.Service;
import pweb.demo.model.Corrida;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@Service
public class CorridaService {
    private Map<Long, Corrida> corridasMap = new HashMap<>();
    private long proximoId = 1;

    // isso aqui eu vou alterar o id deve ser gerado automaticamente talvez via uuid
    // Criar nova corrida
    public Corrida criar(Corrida corrida) {
        validarCorrida(corrida);
        corrida.setId(proximoId++);
        corridasMap.put(corrida.getId(), corrida);
        return corrida;
    }

    // Obter corrida por ID
    public Corrida obter(long id) {
        Corrida corrida = corridasMap.get(id);
        if (corrida == null) {
            throw new IllegalArgumentException("Corrida com ID " + id + " não encontrada");
        }
        return corrida;
    }

    // Listar todas as corridas
    public List<Corrida> listarTodas() {
        return new ArrayList<>(corridasMap.values());
    }

    // Alterar corrida existente
    public Corrida alterar(long id, Corrida corridaAtualizada) {
        Corrida corridaExistente = obter(id);

        corridaExistente.setTempo(corridaAtualizada.getTempo());
        corridaExistente.setTitulo(corridaAtualizada.getTitulo());
        corridaExistente.setDescricao(corridaAtualizada.getDescricao());
        corridaExistente.setPerguntas(corridaAtualizada.getPerguntas());
        corridaExistente.setIsativa(corridaAtualizada.isIsativa());

        return corridaExistente;
    }

    // Apagar corrida
    public void apagar(long id) {
        if (!corridasMap.containsKey(id)) {
            throw new IllegalArgumentException("Corrida com ID " + id + " não encontrada");
        }
        corridasMap.remove(id);
    }

    // jogar para um validador só
    // Validar corrida
    private void validarCorrida(Corrida corrida) {
        if (corrida.getTempo() == null) {
            throw new IllegalArgumentException("Tempo é obrigatório");
        }
        if (corrida.getTitulo() == null || corrida.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
        if (corrida.getDescricao() == null || corrida.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição é obrigatória");
        }
    }
}
