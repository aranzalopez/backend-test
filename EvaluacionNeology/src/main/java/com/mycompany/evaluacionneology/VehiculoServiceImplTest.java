
package com.mycompany.evaluacionneology;

import com.mycompany.evaluacionneology.repositorio.EstanciaRepositorio;
import com.mycompany.evaluacionneology.repositorio.VehiculoRepositorio;
import com.mycompany.evaluacionneology.service.VehiculoService;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
/**
 *
 * @author ViridianaLV
 */
@SpringBootTest
public class VehiculoServiceImplTest {
     @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;

    @Autowired
    private EstanciaRepositorio estanciaRepositorio;

    @Test
    public void testResidenteEntradaSalida() throws InterruptedException {
        String placa = "ABC123";
        vehiculoService.altaResidente(placa);
        vehiculoService.registrarEntrada(placa);
        Thread.sleep(1000); 
        BigDecimal pago = vehiculoService.registrarSalida(placa);
        assertEquals(BigDecimal.ZERO, pago); 
    }
}
