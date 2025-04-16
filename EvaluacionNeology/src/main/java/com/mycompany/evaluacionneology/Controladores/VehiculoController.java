package com.mycompany.evaluacionneology.Controladores;

import com.mycompany.evaluacionneology.service.VehiculoService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ViridianaLV
 */
@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/entrada/{placa}")
    public void entrada(@PathVariable String placa) {
        vehiculoService.registrarEntrada(placa);
    }

    @PostMapping("/salida/{placa}")
    public BigDecimal salida(@PathVariable String placa) {
        return vehiculoService.registrarSalida(placa);
    }

    @PostMapping("/oficial/{placa}")
    public void altaOficial(@PathVariable String placa) {
        vehiculoService.altaOficial(placa);
    }

    @PostMapping("/residente/{placa}")
    public void altaResidente(@PathVariable String placa) {
        vehiculoService.altaResidente(placa);
    }

    @PostMapping("/comenzarMes")
    public void comenzarMes() {
        vehiculoService.comenzarMes();
    }

    @GetMapping("/informe")
    public List<String> informe() {
        return vehiculoService.generarInformePagos();
    }
}
