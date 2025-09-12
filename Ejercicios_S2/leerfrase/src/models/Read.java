package models;

public class Read {
    private String frase;


    public Read(String frase) {
        this.frase = frase;
    }


    public int Read() {

        String[] palabras = frase.trim().split("\\s+");
        return palabras.length;


    }
}
