package run;
import models.TextAnalyzer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read a text
        System.out.print("Ingrese el texto: ");
        String text = sc.nextLine();

        // Create object of TextAnalyzer
        TextAnalyzer analyzer = new TextAnalyzer(text);

        // Test methods
        System.out.println("Número de palabras: " + analyzer.countWords());

        System.out.print("Ingrese una palabra para saber cuantas veces aparece: ");
        String word = sc.nextLine();
        int occurrences = analyzer.countOccurrences(word);

        String timesWord = (occurrences == 1) ? "vez" : "veces";
        System.out.println(word + " aparece " + occurrences + " " + timesWord + "." );

        System.out.print("Ingrese la palabra que quiere reemplazar: ");
        String oldWord = sc.nextLine();
        System.out.print("Ingrese la nueva palabra: ");
        String newWord = sc.nextLine();
        System.out.println("Texto modificado: " + analyzer.replaceWord(oldWord, newWord));

        sc.close();
    }
}
