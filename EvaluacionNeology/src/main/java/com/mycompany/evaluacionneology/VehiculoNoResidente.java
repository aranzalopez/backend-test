package com.mycompany.evaluacionneology;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 *
 * @author ViridianaLV
 */
@Entity
@DiscriminatorValue("NO_RESIDENTE")
public class VehiculoNoResidente extends Vehiculo {

    public VehiculoNoResidente() {
    }

    public VehiculoNoResidente(String placa) {
        super(placa);
    }

    @Override
    public TipoVehiculo getTipo() {
        return TipoVehiculo.NO_RESIDENTE;
    }

}
