
package com.mycompany.evaluacionneology;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 *
 * @author ViridianaLV
 */
@Entity
@DiscriminatorValue("OFICIAL")
public class VehiculoOficial extends Vehiculo {

    public VehiculoOficial() {
    }

    public VehiculoOficial(String placa) {
        super(placa);
    }

    @Override
    public TipoVehiculo getTipo() {
        return TipoVehiculo.OFICIAL;
    }
    
}
