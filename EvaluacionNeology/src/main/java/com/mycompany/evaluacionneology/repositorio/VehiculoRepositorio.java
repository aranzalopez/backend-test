
package com.mycompany.evaluacionneology.repositorio;

import com.mycompany.evaluacionneology.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ViridianaLV
 */
@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, String>{
    
}
