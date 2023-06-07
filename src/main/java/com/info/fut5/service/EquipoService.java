/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.info.fut5.service;

import com.info.fut5.domain.Entrenador;
import com.info.fut5.domain.Equipo;
import com.info.fut5.domain.Jugador;
import java.util.List;

/**
 *
 * @author maxis
 */
public interface EquipoService {
    
    void crearEquipo(String nombre, String fechaCreacion);
    void agregarJugadorAEquipo(Jugador jugador, String nombreEquipo);
    void asignarEntrenadorAEquipo(Entrenador entrenador, String nombreEquipo);
    Equipo buscarEquipoPorNombre(String nombreEquipo);
    Jugador buscarJugadorPorNombre(String nombreJugador);
    void eliminarEquipoPorNombre(String nombreEquipo);
    void importarJugadoresDesdeArchivo(String nombreArchivo);
    void exportarJugadoresAArchivo(String nombreArchivo);
    List<Jugador> obtenerTodosLosJugadores();
    
}
