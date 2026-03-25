package pweb.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> corridaService.criar(dto));
        assertEquals("Título é obrigatório", ex.getMessage());
    }

    @Test
    void deveFalharAoCriarCorridaComTempoNegativo() {
        CorridaService corridaService = new CorridaService();
        CorridaDto dto = new CorridaDto();
        dto.setTitulo("Corrida com tempo negativo");
        dto.setTempo(-5);
        dto.setDescricao("Esse é um teste da corrida com tempo negativo");

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> corridaService.criar(dto));
        assertEquals("Tempo deve ser positivo", ex.getMessage());
    }

    @Test
    void deveFalharAoCriarCorridaSemDescricao() {
        CorridaService corridaService = new CorridaService();
        CorridaDto dto = new CorridaDto();
        dto.setTitulo("Corrida sem descrição");
        dto.setTempo(10);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> corridaService.criar(dto));
        assertEquals("Descrição é obrigatória", ex.getMessage());
    }

    @Test
    void deveFalharAoCriarCorridaSemTempo() {
        CorridaService corridaService = new CorridaService();
        CorridaDto dto = new CorridaDto();
        dto.setTitulo("Corrida sem tempo");
        dto.setDescricao("Esse é um teste da corrida sem tempo");

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> corridaService.criar(dto));
        assertEquals("Tempo é obrigatório", ex.getMessage());
    }

    @Test
    void deveFalharCriarCorridaComTempoZero() {
        CorridaService corridaService = new CorridaService();
        CorridaDto dto = new CorridaDto();
        dto.setTitulo("Corrida com tempo zero");
        dto.setTempo(0);
        dto.setDescricao("Esse é um teste da corrida com tempo zero");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> corridaService.criar(dto));

        assertEquals("Tempo deve ser positivo", exception.getMessage());
    }

    @Test
    void deveFalharAoCriarCorridaDuplicada() {
        CorridaService corridaService = new CorridaService();

        CorridaDto dto1 = new CorridaDto();
        dto1.setTitulo("Corrida Duplicada");
        dto1.setTempo(15);
        dto1.setDescricao("Primeira corrida com título duplicado");
        corridaService.criar(dto1);

        CorridaDto dto2 = new CorridaDto();
        dto2.setTitulo("Corrida Duplicada");
        dto2.setTempo(20);
        dto2.setDescricao("Segunda corrida com título duplicado");

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> corridaService.criar(dto2));
        assertEquals("Título de corrida já existe: Corrida Duplicada", ex.getMessage());
    }

}
