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
public class ResponseDTO implements Serializable {

    private boolean estado;
    private String mensaje;
}
