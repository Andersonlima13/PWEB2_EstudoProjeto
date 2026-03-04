package pweb.demo.model;

import java.util.List;

public class Lobby {
    private List<Participante> participantes; // em teoria , todos os participantes comecam no lobby
    private List<Corrida> corridas;

    public List<Corrida> getCorridas() {
        return corridas;
    }

}
