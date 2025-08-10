package co.edu.umb.academia.ia.minimax.service;

import co.edu.umb.academia.ia.minimax.dtos.OutPartidaDTO;
import co.edu.umb.academia.ia.minimax.entity.Partida;
import co.edu.umb.academia.ia.minimax.exceptions.PartidaException;
import co.edu.umb.academia.ia.minimax.repo.PartidaJpaRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartidaServiceImpl implements PartidaService {

    @Autowired
    private PartidaJpaRepository repo;

    @Transactional(readOnly = true)
    @Override
    public List<OutPartidaDTO> listarPartidas(java.sql.Date fechainicial, java.sql.Date fechafinal) throws PartidaException {
        List<OutPartidaDTO> listaFinal = new ArrayList();
        List<Partida> lista = repo.findAllPartidaByRange(fechainicial, fechafinal);
        if (!lista.isEmpty()) {
            lista.forEach((item) -> {
                listaFinal.add(mapEntityToDTO(item));
            });
        }
        return listaFinal;
    }

    @Transactional
    @Override
    public void guardarPartida(Partida partida) throws PartidaException {
        repo.saveAndFlush(partida);
    }

    @Override
    public OutPartidaDTO mapEntityToDTO(Partida partida) {
        return new OutPartidaDTO(
                partida.getId(),
                partida.getFecha(),
                partida.getJugador().trim(),
                partida.getResultado().getDescripcion().trim()
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Partida buscarPartidaPorCriterios(String jugador, Date fecha, long resultado) throws PartidaException {
        return repo.findPartidaPorCriterios(jugador, fecha, resultado);
    }

}
