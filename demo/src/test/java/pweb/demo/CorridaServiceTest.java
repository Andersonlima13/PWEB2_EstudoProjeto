package pweb.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import pweb.demo.dto.CorridaDto;
import pweb.demo.service.CorridaService;

@SpringBootTest
public class CorridaServiceTest {

    @Test
    void deveCriarUmaCorrida() {
        CorridaService corridaService = new CorridaService();

        CorridaDto dto = new CorridaDto();
        dto.setTitulo("Corrida 1");
        dto.setTempo(12);
        dto.setDescricao("Esse é um teste da corrida 1");
        CorridaDto result = corridaService.criar(dto);

        assertNotNull(result.getId());
        assertEquals("Corrida 1", result.getTitulo());
        assertNotNull(result.getPerguntas());

    }

    @Test
    void deveFalharAoCriarCorridaSemTitulo() {
        CorridaService corridaService = new CorridaService();

        CorridaDto dto = new CorridaDto();
        dto.setTempo(12);
        dto.setDescricao("Esse é um teste da corrida sem título");

        try {
            corridaService.criar(dto);
        } catch (IllegalArgumentException e) {
            // Uma boa maneira de evitar erros é criar uma classe especifica para o erro de
            // titulo
            assertEquals("Título é obrigatório", e.getMessage());
        } // evitando falsos negativos por conta do erro nao ser igual
    }

    @Test
    void deveFalharAoCriarCorridaComTempoNegativo() {
        CorridaService corridaService = new CorridaService();
        CorridaDto dto = new CorridaDto();
        dto.setTitulo("Corrida com tempo negativo");
        dto.setTempo(-5);
        dto.setDescricao("Esse é um teste da corrida com tempo negativo");
        try {
            corridaService.criar(dto);
        } catch (IllegalArgumentException e) {
            assertEquals("Tempo deve ser positivo", e.getMessage());
        }
    }

    @Test
    void deveFalharAoCriarCorridaSemDescricao() {
        CorridaService corridaService = new CorridaService();
        CorridaDto dto = new CorridaDto();
        dto.setTitulo("Corrida sem descrição");
        dto.setTempo(10);
        try {
            corridaService.criar(dto);
        } catch (IllegalArgumentException e) {
            assertEquals("Descrição é obrigatória", e.getMessage());
        }
    }

    @Test
    void deveFalharAoCriarCorridaSemTempo() {
        CorridaService corridaService = new CorridaService();
        CorridaDto dto = new CorridaDto();
        dto.setTitulo("Corrida sem tempo");
        dto.setDescricao("Esse é um teste da corrida sem tempo");
        try {
            corridaService.criar(dto);
        } catch (IllegalArgumentException e) {
            assertEquals("Tempo é obrigatório", e.getMessage());
        }
    }

}
