package com.mycompany.evaluacionneology;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 *
 * @author ViridianaLV
 */
@Entity
@DiscriminatorValue("RESIDENTE")
public class VehiculoResidente extends Vehiculo {

    private int minutosAcumulados;

    public VehiculoResidente() {

    }

    public VehiculoResidente(String placa) {
        super(placa);
        this.minutosAcumulados = 0;
    }

    public int getMinutosAcumulados() {
        return minutosAcumulados;
    }

    public void setMinutosAcumulados(int minutosAcumulados) {
        this.minutosAcumulados = minutosAcumulados;
    }

    public void sumarMinutos(int minutos) {
        this.minutosAcumulados += minutos;
    }

    @Override
    public TipoVehiculo getTipo() {
        return TipoVehiculo.RESIDENTE;
    }

}
