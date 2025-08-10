package co.edu.umb.academia.ia.minimax.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "partida")
public class Partida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partida_id_seq")
    @SequenceGenerator(name = "partida_id_seq", allocationSize = 1)
    private long id;
    private java.sql.Date fecha;
    private String jugador;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resultado", referencedColumnName = "id")
    private Resultado resultado;
}
