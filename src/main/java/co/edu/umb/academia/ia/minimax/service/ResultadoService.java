package co.edu.umb.academia.ia.minimax.service;

import co.edu.umb.academia.ia.minimax.entity.Resultado;
import co.edu.umb.academia.ia.minimax.exceptions.ResultadoException;
import java.util.List;

public interface ResultadoService {

    public List<Resultado> listadoResultados() throws ResultadoException;

    public Resultado buscarPorDescripcion(String descripcion) throws ResultadoException;
}
