package co.edu.umb.academia.ia.minimax.api;

import co.edu.umb.academia.ia.minimax.dtos.InputMovimientoDTO;
import co.edu.umb.academia.ia.minimax.dtos.InputPartidaDTO;
import co.edu.umb.academia.ia.minimax.dtos.OutMovimientoDTO;
import co.edu.umb.academia.ia.minimax.dtos.OutPartidaDTO;
import co.edu.umb.academia.ia.minimax.dtos.ResponseDTO;
import co.edu.umb.academia.ia.minimax.entity.Movimiento;
import co.edu.umb.academia.ia.minimax.entity.Partida;
import co.edu.umb.academia.ia.minimax.entity.Resultado;
import co.edu.umb.academia.ia.minimax.exceptions.MovimientoException;
import co.edu.umb.academia.ia.minimax.exceptions.PartidaException;
import co.edu.umb.academia.ia.minimax.exceptions.ResultadoException;
import co.edu.umb.academia.ia.minimax.service.MovimientoService;
import co.edu.umb.academia.ia.minimax.service.PartidaService;
import co.edu.umb.academia.ia.minimax.service.ResultadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = {"*"})
@Tag(name = "Juego de Tres Lineas", description = "API Rest para la gestion del juego de tres lineas con algoritmo minimax")
public class TresLineasRestController {

    Logger log = LoggerFactory.getLogger(TresLineasRestController.class);

    @Autowired
    private MovimientoService movService;
    @Autowired
    private PartidaService parService;
    @Autowired
    private ResultadoService resService;

    @GetMapping({"/partidas/{fechainicial}/{fechafinal}"})
    @Operation(summary = "Método para listar las partidas por rango de fecha")
    public ResponseEntity<List<OutPartidaDTO>> getAllPartidas(
            @PathVariable java.sql.Date fechainicial,
            @PathVariable java.sql.Date fechafinal) throws PartidaException {
        try {
            return new ResponseEntity(parService.listarPartidas(fechainicial, fechafinal), HttpStatus.OK);
        } catch (PartidaException ex) {
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            log.error(errors.toString());
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping({"/movimientos/{idpartida}"})
    @Operation(summary = "Método para listar las partidas por rango de fecha")
    public ResponseEntity<List<OutMovimientoDTO>> getAllMovimientoByIdPartida(
            @PathVariable long idpartida) throws MovimientoException {
        try {
            return new ResponseEntity(movService.listarMovimientosPartida(idpartida), HttpStatus.OK);
        } catch (MovimientoException ex) {
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            log.error(errors.toString());
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping({"/partidas"})
    @Operation(summary = "Método para listar las partidas por rango de fecha")
    public ResponseEntity<ResponseDTO> procesarPartida(
            @RequestBody InputPartidaDTO inputDTO
    ) throws ResultadoException, PartidaException, MovimientoException {
        ResponseDTO response = new ResponseDTO(false, "bad request");
        try {
            if (inputDTO != null) {
                Resultado resultado = resService.buscarPorDescripcion(inputDTO.getEstado().trim());
                if (resultado != null) {
                    if (!inputDTO.getMovimientos().isEmpty()) {
                        Partida partida = new Partida();
                        partida.setId(0L);
                        partida.setFecha(inputDTO.getFecha());
                        partida.setJugador(inputDTO.getJugador());
                        partida.setResultado(resultado);
                        parService.guardarPartida(partida);//guardo la pardia

                        partida = parService.buscarPartidaPorCriterios(inputDTO.getJugador(), inputDTO.getFecha(), resultado.getId());
                        if (partida != null) {
                            for (InputMovimientoDTO mov : inputDTO.getMovimientos()) {
                                movService.guardarMovimiento(new Movimiento(
                                        0L,
                                        partida,
                                        mov.getJugador().trim(),
                                        mov.getPosicion(),
                                        mov.getOrden()
                                ));
                            }
                            response.setEstado(true);
                            response.setMensaje("Partida guardada exitosamente");
                            return new ResponseEntity(response, HttpStatus.OK);
                        } else {
                            response.setMensaje("No se pudo guardar la partida");
                            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
                        }
                    } else {
                        response.setMensaje("No se recibieron movimientos asociados a la partida");
                        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
                    }
                } else {
                    response.setMensaje("Estado de partida no valido, solo son [GANADO, PERDIDO, EMPATE]");
                    return new ResponseEntity(response, HttpStatus.NOT_FOUND);
                }
            }
        } catch (MovimientoException | PartidaException | ResultadoException ex) {
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            log.error(errors.toString());
            response.setMensaje(errors.toString());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
