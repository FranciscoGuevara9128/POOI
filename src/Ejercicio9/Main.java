package Ejercicio9;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Tablero tablero  = new Tablero();

        Scanner sc = new Scanner(System.in);

        System.out.println("Iniciando tablero...");
        System.out.println("Ingresa el nombre del Jugador 1: ");
        String nombre_1 = sc.nextLine();
        System.out.println("Ingresa tu ficha de juego (X) O (0)");
        String ficha_1 = sc.nextLine();
        Jugador jugador_1 = new Jugador(nombre_1, ficha_1);

        System.out.println("Ingresa el nombre del Jugador 2: ");
        String nombre_2 = sc.nextLine();
        String ficha_2 = "";

        if(jugador_1.getFicha().equals("X")){
            ficha_2 = "0";
        }else{
            ficha_2 = "X";
        };
        Jugador jugador_2 = new Jugador(nombre_2, ficha_2);

        System.out.println("Estado del tablero: ");
        tablero.mostrarTablero();

        boolean juegoActivo = true;
        Jugador turno = jugador_1;

        while (juegoActivo) {
            System.out.println("Turno de " + turno.getNombre());
            System.out.print("Fila (1-3): ");
            int fila = sc.nextInt();
            System.out.print("Columna (1-3): ");
            int columna = sc.nextInt();

            // Intentar colocar ficha
            if (tablero.colocarFicha(fila, columna, turno.getFicha().charAt(0))) {
                tablero.mostrarTablero();

                // Verificar ganador
                char ganador = tablero.verificarGanador();
                if (ganador != ' ') {
                    System.out.println("¡Ganó " + turno.getNombre() + " con la ficha " + ganador + "!");
                    juegoActivo = false;
                } else {
                    // Cambiar turno
                    turno = (turno == jugador_1) ? jugador_2 : jugador_1;
                }
            } else {
                System.out.println("Casilla ocupada o inválida. Intenta de nuevo.");
            }
        }


    }

}
