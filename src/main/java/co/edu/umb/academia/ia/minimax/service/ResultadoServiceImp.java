package co.edu.umb.academia.ia.minimax.service;

import co.edu.umb.academia.ia.minimax.entity.Resultado;
import co.edu.umb.academia.ia.minimax.exceptions.ResultadoException;
import co.edu.umb.academia.ia.minimax.repo.ResultadoJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResultadoServiceImp implements ResultadoService {

    @Autowired
    private ResultadoJpaRepository repo;

    @Transactional(readOnly = true)
    @Override
    public List<Resultado> listadoResultados() throws ResultadoException {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Resultado buscarPorDescripcion(String descripcion) throws ResultadoException {
        return repo.findFirstByDescripcion(descripcion.trim().toUpperCase());
    }

}
