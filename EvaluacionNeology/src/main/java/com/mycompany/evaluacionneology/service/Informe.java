
package com.mycompany.evaluacionneology.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author ViridianaLV
 */
@Component
public class Informe {
    public void exportar(String filename, List<String> lineas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
        }
    }
}
