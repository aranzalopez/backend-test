package com.mycompany.evaluacionneology.service.serviceImpl;

import com.mycompany.evaluacionneology.Estancia;
import com.mycompany.evaluacionneology.Vehiculo;
import com.mycompany.evaluacionneology.VehiculoNoResidente;
import com.mycompany.evaluacionneology.VehiculoOficial;
import com.mycompany.evaluacionneology.VehiculoResidente;
import com.mycompany.evaluacionneology.repositorio.EstanciaRepositorio;
import com.mycompany.evaluacionneology.repositorio.VehiculoRepositorio;
import com.mycompany.evaluacionneology.service.VehiculoService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ViridianaLV
 */
@Service
public class VehiculoServiceImpl implements VehiculoService {

    private static final BigDecimal TARIFA_RESIDENTE = BigDecimal.valueOf(0.05);
    private static final BigDecimal TARIFA_NO_RESIDENTE = BigDecimal.valueOf(0.5);

    

    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;

    @Autowired
    private EstanciaRepositorio estanciaRepositorio;

    @Override
    public void registrarEntrada(String placa) {
        Vehiculo vehiculo = vehiculoRepositorio.findById(placa).orElseGet(() -> {
            VehiculoNoResidente nuevo = new VehiculoNoResidente(placa);
            return vehiculoRepositorio.save(nuevo);
        });
        Estancia estancia = new Estancia(vehiculo, LocalDateTime.now());
        estanciaRepositorio.save(estancia);
    }

    @Override
    public BigDecimal registrarSalida(String placa) {
        Optional<Vehiculo> optional = vehiculoRepositorio.findById(placa);
        if (optional.isEmpty()) {
            throw new RuntimeException("Vehículo no encontrado.");
        }

        Vehiculo vehiculo = optional.get();
        Estancia estancia = estanciaRepositorio.findTopByVehiculoAndSalidaIsNullOrderByEntradaDesc(vehiculo);

        if (estancia == null) {
            throw new RuntimeException("No hay entrada registrada.");
        }

        estancia.setSalida(LocalDateTime.now());
        estanciaRepositorio.save(estancia);

        int minutos = estancia.getDuracionEnMinutos();

        if (vehiculo instanceof VehiculoOficial) {
            return BigDecimal.ZERO;
        } else if (vehiculo instanceof VehiculoResidente) {
            ((VehiculoResidente) vehiculo).sumarMinutos(minutos);
            vehiculoRepositorio.save(vehiculo);
            return BigDecimal.ZERO;
        } else {
            return TARIFA_NO_RESIDENTE.multiply(BigDecimal.valueOf(minutos));
        }
    }

    @Override
    public void altaOficial(String placa) {
        VehiculoOficial oficial = new VehiculoOficial(placa);
        vehiculoRepositorio.save(oficial);
    }

    @Override
    public void altaResidente(String placa) {
        VehiculoResidente residente = new VehiculoResidente(placa);
        vehiculoRepositorio.save(residente);
    }

    @Override
    public void comenzarMes() {
        List<Vehiculo> vehiculos = vehiculoRepositorio.findAll();

        for (Vehiculo v : vehiculos) {
            if (v instanceof VehiculoOficial) {
                List<Estancia> estancias = estanciaRepositorio.findByVehiculo(v);
                estanciaRepositorio.deleteAll(estancias);
            } else if (v instanceof VehiculoResidente) {
                ((VehiculoResidente) v).setMinutosAcumulados(0);
                vehiculoRepositorio.save(v);
            }
        }
    }

    @Override
    public List<String> generarInformePagos() {
        List<Vehiculo> vehiculos = vehiculoRepositorio.findAll();
        List<String> informe = new ArrayList<>();

        informe.add("Núm. placa\tTiempo estacionado (min.)\tCantidad a pagar");

        for (Vehiculo v : vehiculos) {
            if (v instanceof VehiculoResidente residente) {
                int minutos = residente.getMinutosAcumulados();
                BigDecimal pago = TARIFA_RESIDENTE.multiply(BigDecimal.valueOf(minutos));
                informe.add(v.getPlaca() + "\t" + minutos + "\t" + pago);
            }
        }

        return informe;
    }
}
