package Ejercicio10;

public class Cadena {
    String oracion;

    Cadena(String oracion) {
        this.oracion = oracion;
    }

    public String getOracion() {
        return oracion;
    }

    public void setOracion(String oracion) {
        this.oracion = oracion;
    }

    public void convertirMayuscula(){
        String mayuscula = this.oracion.toUpperCase();
        System.out.println("La cadena convertida es: " + mayuscula);
    }

    public void convertirMinuscula(){
        String minuscula = this.oracion.toLowerCase();
        System.out.println("La cadena convertida es: " + minuscula);
    }

    public String invertirCadena(){
        String invertida = "";
        for (int i = this.oracion.length() - 1; i >= 0 ; i--) {
            invertida = invertida + this.oracion.charAt(i);
        }
        System.out.println("La cadena convertida es: " + invertida);
        return invertida;
    }

    public void contarVocales(){
        String palabra = this.oracion.toLowerCase();
        int contador = 0;

        for (int i = 0; i <= palabra.length() - 1; i++) {
            char letra = palabra.charAt(i);

            if (letra == 'a' || letra == 'e' || letra == 'i' || letra =='o' || letra =='u'){
                contador ++;
            }
        }

        System.out.println("La cantidad de vocales es: " + contador);
    }

    public void contarConsonantes(){
        String palabra = this.oracion.toLowerCase();
        int contador = 0;

        for (int i = 0; i <= this.oracion.length() - 1; i++) {
            char letra = this.oracion.charAt(i);

            if (letra != 'a' && letra != 'e' && letra != 'i' && letra != 'o' && letra != 'u'){
                contador ++;
            }
        }
        System.out.println("La cantidad de consonantes es: " + contador);
    }

    public void verificarPalindromo(){

        String normal = this.oracion;
        Cadena inverso =  new Cadena(normal);
        inverso.oracion = inverso.invertirCadena();

        int largo = this.oracion.length();
        int caracteresIguales = 0;
        for (int i = 0; i <= largo-1; i++) {
            if(inverso.oracion.charAt(i) == normal.charAt(i)){
                caracteresIguales++;
            }
        }
        if(caracteresIguales == largo){
            System.out.println("La palabra " + this.oracion + " es palindromo");
        }else{
            System.out.println("La palabra " + this.oracion + " no es  palindromo");
        }
    }

}
