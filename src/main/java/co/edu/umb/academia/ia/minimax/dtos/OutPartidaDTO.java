package co.edu.umb.academia.ia.minimax.dtos;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutPartidaDTO implements Serializable {

    private long secuencia;
    private java.sql.Date fecha;
    private String jugador;
    private String estado;
}
