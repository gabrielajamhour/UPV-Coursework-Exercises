package ejemplos.tema3;

//para poder usar Map y ListaConPI
import librerias.estructurasDeDatos.modelos.*; 

//para poder usar TablaHash, la Implementacion de Map
import librerias.estructurasDeDatos.deDispersion.*; 

// CUESTION: ?Que sucede si, en lugar de los dos import, 
// se escribe: import java.util.*;?
import java.util.Locale; 
import java.util.Scanner; 

public class Test3Map {
    
    public static void main(String[] args) {
        
        // Por simplicidad, la frase no se lee de un fichero,  
        // sino que se lee de teclado como un String de Palabras 
        // separadas por blancos. Una frase (String) ejemplo seria: 
        // "vale, aunque es un poco rollo lo hago para que se vea como funciona el Map!! Se me ha olvidado escribir palabras repetidas vaya!!"

        // Lectura de la frase (String) a partir de la que se construye el Map
        Locale localEDA = new Locale("es", "US");
        Scanner teclado = new Scanner(System.in).useLocale(localEDA);
        System.out.println("Escriba palabras separadas por blancos:");
        String texto = teclado.nextLine();

        // Creacion del Map vacio ... 
        // ?Que Clave y Valor tiene cada Entrada de este Map? 
        // ?De que tipos son? 
        Map<String, Integer> m = new TablaHash<String, Integer>(texto.length());

        // Construcciï¿½n del Map, via insercion/actualizacion de sus Entradas, 
        // a partir de la frase leida: 
        // uso del mï¿½todo split de String con separador " " (uno o mas)
        String[] palabrasDelTexto = texto.split(" +");
                
        for (int i = 0; i < palabrasDelTexto.length; i++) {
            String palabra = palabrasDelTexto[i].toLowerCase();
            Integer frec = m.recuperar(palabra);
            
            if (frec == null) {m.insertar(palabra, 1);}
            else {m.insertar(palabra, frec+1);}
        }
            
        ListaConPI<String> claves = m.claves();    
            
        for (claves.inicio(); !claves.esFin(); claves.siguiente()) {  
            String clave = claves.recuperar();
            Integer frecuencia = m.recuperar(clave);
            
            if (frecuencia > 1) {
                System.out.println("Palabra repetida del texto: " + clave + " - Número de veces que se repite: " + frecuencia);
            }
        }
    }
}
