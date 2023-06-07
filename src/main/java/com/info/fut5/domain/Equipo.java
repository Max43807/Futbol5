package com.info.fut5.domain;

import java.util.ArrayList;
import java.util.List;

public class Equipo {

    private String nombre;
    private String fechaCreacion;
    private List<Jugador> jugadores;
    private Entrenador entrenador;

    // Constructor, getters y setters
    public Equipo(String nombre, String fechaCreacion, List<Jugador> jugadores, Entrenador entrenador) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.jugadores = jugadores;
        this.entrenador = entrenador;
    }

    public Equipo() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void agregarJugador(Jugador jugador) {
        if (jugadores == null) {
            jugadores = new ArrayList<>();
        }
        jugadores.add(jugador);
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    
}
