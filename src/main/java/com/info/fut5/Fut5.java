/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.info.fut5;

import com.info.fut5.domain.Entrenador;
import com.info.fut5.domain.Equipo;
import com.info.fut5.domain.Jugador;
import com.info.fut5.service.EquipoService;
import com.info.fut5.service.EquipoServiceImpl;
import com.info.fut5.util.FileUtils;
import java.util.List;

import java.util.Scanner;

/**
 *
 * @author maxis
 */
public class Fut5 {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EquipoService equipoService = new EquipoServiceImpl();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 8);
    }

    private static void mostrarMenu() {
        System.out.println("Fut5App - Menú de opciones");
        System.out.println("1. Crear equipo");
        System.out.println("2. Agregar jugador a equipo");
        System.out.println("3. Asignar entrenador a equipo");
        System.out.println("4. Buscar jugador por nombre");
        System.out.println("5. Buscar equipo por nombre (solo nombre y capitán)");
        System.out.println("6. Buscar equipo por nombre (nombre, entrenador y jugadores)");
        System.out.println("7. Eliminar equipo por nombre");
        System.out.println("8. Importar jugadores desde un archivo");
        System.out.println("9. Exportar jugadores a un archivo");
        System.out.println("10. Salir");
        System.out.print("Ingrese una opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                crearEquipo();
                break;
            case 2:
                agregarJugadorAEquipo();
                break;
            case 3:
                asignarEntrenadorAEquipo();
                break;
            case 4:
                buscarJugadorPorNombre();
                break;
            case 5:
                buscarEquipoPorNombre_Simple();
                break;
            case 6:
                buscarEquipoPorNombre_Completo();
                break;
            case 7:
                eliminarEquipoPorNombre();
                break;
            case 8:
                importarJugadoresDesdeArchivo();
                break;
            case 9:
                exportarJugadoresHaciaArchivo();
                break;
            case 10:
                System.out.println("¡Hasta luego!");
                break;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                break;
        }
        System.out.println();
    }

    private static void crearEquipo() {
        System.out.print("Ingrese el nombre del equipo: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la fecha de creación del equipo: ");
        String fechaCreacion = scanner.nextLine();
        equipoService.crearEquipo(nombre, fechaCreacion);
        System.out.println("El equipo se ha creado correctamente.");
    }

    private static void agregarJugadorAEquipo() {
        System.out.print("Ingrese el nombre del jugador: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del jugador: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese la altura del jugador: ");
        double altura = Double.parseDouble(scanner.nextLine());
        System.out.print("Ingrese la posición del jugador: ");
        String posicion = scanner.nextLine();
        System.out.print("Ingrese la cantidad de goles del jugador: ");
        int goles = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la cantidad de partidos del jugador: ");
        int partidos = Integer.parseInt(scanner.nextLine());
        System.out.print("¿Es capitán? (Sí/No): ");
        boolean esCapitan = scanner.nextLine().equalsIgnoreCase("Sí");
        System.out.print("Ingrese el número de camiseta del jugador: ");
        int numeroCamiseta = Integer.parseInt(scanner.nextLine());

        Jugador jugador = new Jugador();
        jugador.setNombre(nombre);
        jugador.setApellido(apellido);
        jugador.setAltura(altura);
        jugador.setPosicion(posicion);
        jugador.setGoles(goles);
        jugador.setPartidos(partidos);
        jugador.setEsCapitan(esCapitan);
        jugador.setNumeroCamiseta(numeroCamiseta);

        System.out.print("Ingrese el nombre del equipo al que pertenece el jugador: ");
        String nombreEquipo = scanner.nextLine();
        equipoService.agregarJugadorAEquipo(jugador, nombreEquipo);
        System.out.println("El jugador se ha agregado al equipo correctamente.");
    }

    private static void asignarEntrenadorAEquipo() {
        System.out.print("Ingrese el nombre del entrenador: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del entrenador: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese la edad del entrenador: ");
        int edad = Integer.parseInt(scanner.nextLine());

        Entrenador entrenador = new Entrenador();
        entrenador.setNombre(nombre);
        entrenador.setApellido(apellido);
        entrenador.setEdad(edad);

        System.out.print("Ingrese el nombre del equipo al que pertenece el entrenador: ");
        String nombreEquipo = scanner.nextLine();
        equipoService.asignarEntrenadorAEquipo(entrenador, nombreEquipo);
        System.out.println("El entrenador se ha asignado al equipo correctamente.");
    }

    private static void buscarJugadorPorNombre() {
        System.out.print("Ingrese el nombre del jugador: ");
        String nombreJugador = scanner.nextLine();
        Jugador jugador = equipoService.buscarJugadorPorNombre(nombreJugador);
        if (jugador != null) {
            System.out.println("Nombre: " + jugador.getNombre());
            System.out.println("Apellido: " + jugador.getApellido());
            System.out.println("Posición: " + jugador.getPosicion());
            System.out.println("¿Es capitán? " + (jugador.isEsCapitan() ? "Sí" : "No"));
            System.out.println("Equipo: " + jugador.getEquipo().getNombre());
        } else {
            System.out.println("No se encontró un jugador con ese nombre.");
        }
    }

    private static void buscarEquipoPorNombre_Simple() {
    System.out.print("Ingrese el nombre del equipo: ");
    String nombreEquipo = scanner.nextLine();
    Equipo equipo = equipoService.buscarEquipoPorNombre(nombreEquipo);
    if (equipo != null) {
        System.out.println("Nombre: " + equipo.getNombre());
        System.out.print("Capitán: ");
        boolean encontrado = false;
        for (Jugador jugador : equipo.getJugadores()) {
            if (jugador.isEsCapitan()) {
                System.out.println(jugador.getNombre() + " " + jugador.getApellido());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se ha asignado un capitán al equipo.");
        }
    } else {
        System.out.println("No se encontró un equipo con ese nombre.");
    }
}

    private static void buscarEquipoPorNombre_Completo() {
        System.out.print("Ingrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();
        Equipo equipo = equipoService.buscarEquipoPorNombre(nombreEquipo);
        if (equipo != null) {
            System.out.println("Nombre: " + equipo.getNombre());
            System.out.println("Entrenador: " + equipo.getEntrenador().getNombreCompleto());
            System.out.println("Jugadores:");
            for (Jugador jugador : equipo.getJugadores()) {
                System.out.println("- " + jugador.getNombreCompleto());
            }
        } else {
            System.out.println("No se encontró un equipo con ese nombre.");
        }
    }

    private static void eliminarEquipoPorNombre() {
        System.out.print("Ingrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();
        equipoService.eliminarEquipoPorNombre(nombreEquipo);
        System.out.println("El equipo se ha eliminado correctamente.");
    }

    private static void importarJugadoresDesdeArchivo() {
        System.out.print("Ingrese la ruta del archivo CSV o TXT: ");
        String rutaArchivo = scanner.nextLine();
        List<Jugador> jugadores = FileUtils.importarJugadoresDesdeArchivo(rutaArchivo);
        if (!jugadores.isEmpty()) {
            System.out.println("Jugadores importados exitosamente:");
            for (Jugador jugador : jugadores) {
                System.out.println(jugador);
            }
        } else {
            System.out.println("No se encontraron jugadores en el archivo.");
        }
    }

    private static void exportarJugadoresHaciaArchivo() {
        System.out.print("Ingrese la ruta del archivo de destino (CSV o TXT): ");
        String rutaArchivo = scanner.nextLine();
        boolean exito = FileUtils.exportarJugadoresHaciaArchivo(equipoService.obtenerTodosLosJugadores(), rutaArchivo);
        if (exito) {
            System.out.println("Jugadores exportados exitosamente.");
        } else {
            System.out.println("Ocurrió un error al exportar los jugadores.");
        }
    }
}
