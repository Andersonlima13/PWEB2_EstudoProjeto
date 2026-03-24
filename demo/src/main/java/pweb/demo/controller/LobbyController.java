package pweb.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pweb.demo.dto.CorridaDto;
import pweb.demo.service.CorridaService;

@RestController
@RequestMapping("/api/lobby")
public class LobbyController {
    private CorridaService corridaService;

}
