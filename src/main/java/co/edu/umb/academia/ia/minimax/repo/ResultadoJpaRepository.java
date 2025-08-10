package co.edu.umb.academia.ia.minimax.repo;

import co.edu.umb.academia.ia.minimax.entity.Resultado;
import co.edu.umb.academia.ia.minimax.exceptions.ResultadoException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoJpaRepository extends JpaRepository<Resultado, Long> {

    @Query(value = "Select * from resultado where descripcion = :descripcion limit 1", nativeQuery = true)
    public Resultado findFirstByDescripcion(@Param("descripcion") String descripcion) throws ResultadoException;
}
