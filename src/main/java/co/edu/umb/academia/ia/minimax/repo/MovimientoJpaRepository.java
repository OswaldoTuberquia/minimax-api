package co.edu.umb.academia.ia.minimax.repo;

import co.edu.umb.academia.ia.minimax.entity.Movimiento;
import co.edu.umb.academia.ia.minimax.exceptions.MovimientoException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoJpaRepository extends JpaRepository<Movimiento, Long> {

    @Query(value = "Select * from movimiento "
            + " where id_partida = :idPartida order By id desc", nativeQuery = true)
    public List<Movimiento> findAllMovimientoByIdPartida(
            @Param("idPartida") long idPartida) throws MovimientoException;
}
