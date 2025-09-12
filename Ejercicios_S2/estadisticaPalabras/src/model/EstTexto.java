package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Estadísticas de Palabras
//Objetivo: Clase EstadisticaTexto con un arreglo de Strings (palabras) y métodos para:
//•    Contar cuántas palabras hay.
//•    Mostrar palabras únicas.
//•    Palabra más larga y más corta.


public class EstTexto {
    private String[] palabras;

    public EstTexto(String[] palabras) {
        this.palabras = palabras;
    }

    public int contarPalabras() {
        return palabras.length;
    }

    public Set<String> palabrasUnicas() {
        return new HashSet<>(Arrays.asList(palabras));
    }

    public String palabraMasLarga() {
        String masLarga = palabras[0];
        for (String palabra : palabras) {
            if (palabra.length() > masLarga.length()) {
                masLarga = palabra;
            }
        }
        return masLarga;
    }

    public String palabraMasCorta() {
        String masCorta = palabras[0];
        for (String palabra : palabras) {
            if (palabra.length() < masCorta.length()) {
                masCorta = palabra;
            }
        }
        return masCorta;
    }
}