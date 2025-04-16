package com.mycompany.evaluacionneology;


import com.mycompany.evaluacionneology.Vehiculo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;



/**
 *
 * @author ViridianaLV
 */

@Entity
public class Estancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vehiculo vehiculo;

    private LocalDateTime entrada;
    private LocalDateTime salida;

    public Estancia() {
    }

    public Estancia( Vehiculo vehiculo, LocalDateTime entrada) {
       
        this.vehiculo = vehiculo;
        this.entrada = entrada;
     
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSalida() {
        return salida;
    }

    public void setSalida(LocalDateTime salida) {
        this.salida = salida;
    }
     public int getDuracionEnMinutos() {
        if (entrada != null && salida != null) {
            return (int) java.time.Duration.between(entrada, salida).toMinutes();
        }
        return 0;
    }
    
    
}
