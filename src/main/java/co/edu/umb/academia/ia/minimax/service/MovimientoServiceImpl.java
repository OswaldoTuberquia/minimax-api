package co.edu.umb.academia.ia.minimax.service;

import co.edu.umb.academia.ia.minimax.dtos.OutMovimientoDTO;
import co.edu.umb.academia.ia.minimax.entity.Movimiento;
import co.edu.umb.academia.ia.minimax.exceptions.MovimientoException;
import co.edu.umb.academia.ia.minimax.repo.MovimientoJpaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoJpaRepository repo;

    @Transactional(readOnly = true)
    @Override
    public List<OutMovimientoDTO> listarMovimientosPartida(long idPartida) throws MovimientoException {
        List<OutMovimientoDTO> listaFinal = new ArrayList<>();
        List<Movimiento> lista = repo.findAllMovimientoByIdPartida(idPartida);
        if (!lista.isEmpty()) {
            lista.forEach((item) -> {
                listaFinal.add(mapEntityToDTO(item));
            });
        }
        return listaFinal;
    }

    @Transactional
    @Override
    public void guardarMovimiento(Movimiento movimiento) throws MovimientoException {
        repo.saveAndFlush(movimiento);
    }

    @Override
    public OutMovimientoDTO mapEntityToDTO(Movimiento movimiento) {
        return new OutMovimientoDTO(
                movimiento.getId(),
                movimiento.getJugador().trim(),
                movimiento.getPosicion(),
                movimiento.getOrden()
        );
    }

}
