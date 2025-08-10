package co.edu.umb.academia.ia.minimax.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "resultado")
public class Resultado implements Serializable{
     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resultado_id_seq")
    @SequenceGenerator(name = "resultado_id_seq", allocationSize = 1)
    private long id;
    private String descripcion;
}
