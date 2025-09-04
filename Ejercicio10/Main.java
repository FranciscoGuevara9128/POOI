package Ejercicio10;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String escrito;

        escrito = sc.nextLine();

        Cadena cadena = new Cadena(escrito);

        System.out.println("Elige tu opcion preferida: ");
        System.out.println("1. Convertir a mayúscula");
        System.out.println("2. Convertir a minúscula");
        System.out.println("3. Invertir cadena");
        System.out.println("4. Verificar si es palíndromo");
        System.out.println("5. Contar vocales");
        System.out.println("6. Contar consonantes");

        int decision = sc.nextInt();

        if (decision == 1){
            cadena.convertirMayuscula();
        }else if (decision == 2){
            cadena.convertirMinuscula();
        }else if (decision == 3){
            cadena.invertirCadena();
        }
        else if (decision == 4){
            cadena.verificarPalindromo();
        }else if (decision == 5){
            cadena.contarVocales();
        }else if (decision == 6){
            cadena.contarConsonantes();
        }else{
            System.out.println("No es parte del menú");
        }
    }
}
