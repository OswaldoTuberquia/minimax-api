package co.edu.umb.academia.ia.minimax.service;

import co.edu.umb.academia.ia.minimax.dtos.OutPartidaDTO;
import co.edu.umb.academia.ia.minimax.entity.Partida;
import co.edu.umb.academia.ia.minimax.exceptions.PartidaException;
import java.util.List;

public interface PartidaService {
    
    public OutPartidaDTO mapEntityToDTO(Partida partida);

    public List<OutPartidaDTO> listarPartidas(java.sql.Date fechainicial, java.sql.Date fechafinal) throws PartidaException;

    public void guardarPartida(Partida partida) throws PartidaException;
    
    public Partida buscarPartidaPorCriterios(String jugador, java.sql.Date fecha, long resultado)throws PartidaException;
}
