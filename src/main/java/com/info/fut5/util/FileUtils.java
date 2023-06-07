/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info.fut5.util;

import com.info.fut5.domain.Equipo;
import com.info.fut5.domain.Jugador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maxis
 */
public class FileUtils {
    private static final String DELIMITER = ",";

    public static List<Jugador> importarJugadoresDesdeArchivo(String nombreArchivo) {
        List<Jugador> jugadores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String line;
            boolean primeraLinea = true;
            while ((line = reader.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue; // Omitir la primera línea (encabezado)
                }
                String[] datos = line.split(DELIMITER);
                if (datos.length == 10) {
                    Jugador jugador = new Jugador();
                    jugador.setId(Integer.parseInt(datos[0]));
                    jugador.setNombre(datos[1]);
                    jugador.setApellido(datos[2]);
                    jugador.setAltura(Double.parseDouble(datos[3]));
                    jugador.setPosicion(datos[4]);
                    jugador.setGoles(Integer.parseInt(datos[5]));
                    jugador.setPartidos(Integer.parseInt(datos[6]));
                    jugador.setEsCapitan(Boolean.parseBoolean(datos[7]));
                    jugador.setNumeroCamiseta(Integer.parseInt(datos[8]));
                    Equipo equipo = new Equipo();
                    equipo.setNombre(datos[9]);
                    jugador.setEquipo(equipo);
                    jugadores.add(jugador);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jugadores;
    }

    public static void exportarJugadoresAArchivo(List<Jugador> jugadores, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Jugador jugador : jugadores) {
                StringBuilder sb = new StringBuilder();
                sb.append(jugador.getId()).append(DELIMITER);
                sb.append(jugador.getNombre()).append(DELIMITER);
                sb.append(jugador.getApellido()).append(DELIMITER);
                sb.append(jugador.getAltura()).append(DELIMITER);
                sb.append(jugador.getPosicion()).append(DELIMITER);
                sb.append(jugador.getGoles()).append(DELIMITER);
                sb.append(jugador.getPartidos()).append(DELIMITER);
                sb.append(jugador.isEsCapitan()).append(DELIMITER);
                sb.append(jugador.getNumeroCamiseta()).append(DELIMITER);
                sb.append(jugador.getEquipo().getNombre());
                writer.write(sb.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean exportarJugadoresHaciaArchivo(List<Jugador> jugadores, String rutaArchivo) {
        try (FileWriter fileWriter = new FileWriter(rutaArchivo)) {
            fileWriter.write("ID,Nombre,Apellido,Altura,Posicion,Goles,Partidos,Capitan,NumeroCamiseta,Equipo\n");
            for (Jugador jugador : jugadores) {
                String esCapitan = jugador.isEsCapitan() ? "SI" : "NO";
                String linea = String.format("%s,%s,%s,%.2f,%s,%d,%d,%s,%d,%s\n",
                        jugador.getId(),
                        jugador.getNombre(),
                        jugador.getApellido(),
                        jugador.getAltura(),
                        jugador.getPosicion(),
                        jugador.getGoles(),
                        jugador.getPartidos(),
                        esCapitan,
                        jugador.getNumeroCamiseta(),
                        jugador.getEquipo().getNombre()
                );
                fileWriter.write(linea);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
