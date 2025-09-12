package Ejercicio9;

public class Tablero {
    private char[][] tablero;

    public Tablero() {
        tablero = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    public void mostrarTablero() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public boolean colocarFicha(int fila, int columna, char ficha) {
        if (fila >= 1 && fila <= 3 && columna >= 1 && columna <= 3) {
            if (tablero[fila-1][columna-1] == ' ') {
                tablero[fila-1][columna-1] = ficha;
                return true;
            } else {
                System.out.println("La casilla ya está ocupada.");
                return false;
            }
        } return false;
    }

    public char verificarGanador() {
        // Revisar filas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] != ' ' && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return tablero[i][0];
            }
        }

        // Revisar columnas
        for (int j = 0; j < 3; j++) {
            if (tablero[0][j] != ' ' && tablero[0][j] == tablero[1][j] && tablero[1][j] == tablero[2][j]) {
                return tablero[0][j];
            }
        }

        // Revisar diagonales
        if (tablero[0][0] != ' ' && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return tablero[0][0];
        }
        if (tablero[0][2] != ' ' && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            return tablero[0][2];
        }

        // Si no hay ganador
        return ' ';
    }
}
