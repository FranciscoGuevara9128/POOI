package run;

import models.Read;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese una frase: ");
        String frase = sc.nextLine();

        Read cp = new Read(frase);

        System.out.println("La frase tiene " + cp.Read() + " palabras.");

        sc.close();
    }
}
