
package com.mycompany.evaluacionneology.repositorio;

import com.mycompany.evaluacionneology.Estancia;
import com.mycompany.evaluacionneology.Vehiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ViridianaLV
 */
@Repository
public interface EstanciaRepositorio extends JpaRepository<Estancia, String> {
    
    
    Estancia findTopByVehiculoAndSalidaIsNullOrderByEntradaDesc(Vehiculo vehiculo);

   
    List<Estancia> findByVehiculo(Vehiculo vehiculo);
}
