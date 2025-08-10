package co.edu.umb.academia.ia.minimax.repo;

import co.edu.umb.academia.ia.minimax.entity.Partida;
import co.edu.umb.academia.ia.minimax.exceptions.PartidaException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaJpaRepository extends JpaRepository<Partida, Long> {

    @Query(value = "select * from partida "
            + " where fecha between :fechainicial and :fechafinal "
            + " order by id desc", nativeQuery = true)
    public List<Partida> findAllPartidaByRange(@Param("fechainicial") java.sql.Date fechainicial,
            @Param("fechafinal") java.sql.Date fechafinal) throws PartidaException;

    @Query(value = "select * from partida "
            + " where jugador = :jugador "
            + " and fecha = :fecha "
            + " and id_resultado = :resultado "
            + " order by id desc limit 1", nativeQuery = true)
    public Partida findPartidaPorCriterios(
            @Param("jugador") String jugador,
            @Param("fecha") java.sql.Date fecha,
            @Param("resultado") long resultado) throws PartidaException;
}
