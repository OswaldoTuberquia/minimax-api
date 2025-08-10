package co.edu.umb.academia.ia.minimax.service;

import co.edu.umb.academia.ia.minimax.dtos.OutMovimientoDTO;
import co.edu.umb.academia.ia.minimax.entity.Movimiento;
import co.edu.umb.academia.ia.minimax.exceptions.MovimientoException;
import java.util.List;

public interface MovimientoService {

    public OutMovimientoDTO mapEntityToDTO(Movimiento movimiento);

    public List<OutMovimientoDTO> listarMovimientosPartida(long idPartida) throws MovimientoException;

    public void guardarMovimiento(Movimiento movimiento) throws MovimientoException;
}
