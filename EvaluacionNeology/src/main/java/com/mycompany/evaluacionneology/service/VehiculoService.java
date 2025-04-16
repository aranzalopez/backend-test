package com.mycompany.evaluacionneology.service;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ViridianaLV
 */
public interface VehiculoService {

    public void registrarEntrada(String placa);

    public BigDecimal registrarSalida(String placa);

    public void altaOficial(String placa);

    public void altaResidente(String placa);

    public void comenzarMes();

    List<String> generarInformePagos();
}
