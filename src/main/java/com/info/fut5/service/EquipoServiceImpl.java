/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info.fut5.service;

import com.info.fut5.domain.Entrenador;
import com.info.fut5.domain.Equipo;
import com.info.fut5.domain.Jugador;
import com.info.fut5.util.FileUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maxis
 */
public class EquipoServiceImpl implements EquipoService {
    private List<Equipo> equipos;

    public EquipoServiceImpl() {
        equipos = new ArrayList<>();
    }

    @Override
    public void crearEquipo(String nombre, String fechaCreacion) {
        Equipo equipo = new Equipo();
        equipo.setNombre(nombre);
        equipo.setFechaCreacion(fechaCreacion);
        equipos.add(equipo);
    }

    @Override
    public void agregarJugadorAEquipo(Jugador jugador, String nombreEquipo) {
        Equipo equipo = buscarEquipoPorNombre(nombreEquipo);
        if (equipo != null) {
            jugador.setEquipo(equipo);
            equipo.agregarJugador(jugador);
        }
    }

    @Override
    public void asignarEntrenadorAEquipo(Entrenador entrenador, String nombreEquipo) {
        Equipo equipo = buscarEquipoPorNombre(nombreEquipo);
        if (equipo != null) {
            equipo.setEntrenador(entrenador);
        }
    }

    @Override
    public Equipo buscarEquipoPorNombre(String nombreEquipo) {
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                return equipo;
            }
        }
        return null;
    }

    @Override
    public Jugador buscarJugadorPorNombre(String nombreJugador) {
        for (Equipo equipo : equipos) {
            List<Jugador> jugadores = equipo.getJugadores();
            if (jugadores != null) {
                for (Jugador jugador : jugadores) {
                    if (jugador.getNombre().equalsIgnoreCase(nombreJugador)) {
                        return jugador;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void eliminarEquipoPorNombre(String nombreEquipo) {
        Equipo equipo = buscarEquipoPorNombre(nombreEquipo);
        if (equipo != null) {
            equipos.remove(equipo);
        }
    }

    @Override
    public void importarJugadoresDesdeArchivo(String nombreArchivo) {
        List<Jugador> jugadores = FileUtils.importarJugadoresDesdeArchivo(nombreArchivo);
        if (jugadores != null) {
            for (Jugador jugador : jugadores) {
                String nombreEquipo = jugador.getEquipo().getNombre();
                Equipo equipo = buscarEquipoPorNombre(nombreEquipo);
                if (equipo != null) {
                    equipo.agregarJugador(jugador);
                }
            }
        }
    }

    @Override
    public void exportarJugadoresAArchivo(String nombreArchivo) {
        List<Jugador> jugadores = new ArrayList<>();
        for (Equipo equipo : equipos) {
            List<Jugador> jugadoresEquipo = equipo.getJugadores();
            if (jugadoresEquipo != null) {
                jugadores.addAll(jugadoresEquipo);
            }
        }
        FileUtils.exportarJugadoresAArchivo(jugadores, nombreArchivo);
    }

    @Override
    public List<Jugador> obtenerTodosLosJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        for (Equipo equipo : equipos) {
            jugadores.addAll(equipo.getJugadores());
        }
        return jugadores;
    }
}
