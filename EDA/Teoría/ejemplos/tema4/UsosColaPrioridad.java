package ejemplos.tema4;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.jerarquicos.MonticuloBinario;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;

/**
 * class UsosColaPrioridad.
 * 
 * @author FTG 
 * @version 1.0
 */

public class UsosColaPrioridad {
    
    /** Problema 1: 
     *  Disenyar un metodo estatico e iterativo cPSort 
     *  que, con la ayuda de una Cola de Prioridad, 
     *  ordene un array v de elementos Comparable. 
     */
    public static <E extends Comparable<E>> void cPSort(E[] v) {
        ColaPrioridad<E> cP = new MonticuloBinario<E>(v.length+1);
        
        for(int i = 0; i < v.length; i++) cP.insertar(v[i]);
        for(int i = 0; i < v.length; i++) v[i] = cP.eliminarMin();
        
        // Coste promedio: n * log(n) 
    }
    
    /** Problema 2:
     *  Disenyar un metodo estatico, generico e iterativo cPFusionar 
     *  que devuelva una ListaConPI con los datos de 2 Colas de Prioridad dadas, 
     *  cP1 y cP2, ordenados ascendentemente. 
     *  El metodo no puede usar ninguna EDA auxiliar para calcular su resultado 
     *  y, ademas, cP1 y cP2 deben quedar vacias al concluir su ejecucion.
     */
    public static <E extends Comparable<E>> ListaConPI<E> cPFusionar(
        ColaPrioridad<E> cP1, ColaPrioridad<E> cP2) 
    {
        ListaConPI<E> res = new LEGListaConPI<>();
        
        while(!cP1.esVacia() && !cP2.esVacia())
            if(cP1.recuperarMin().compareTo(cP2.recuperarMin()) < 0) res.insertar(cP1.eliminarMin());
            else res.insertar(cP2.eliminarMin());
        
        while(!cP1.esVacia()) res.insertar(cP1.eliminarMin());
        while(!cP2.esVacia()) res.insertar(cP2.eliminarMin());
        
        return res;
        
        // Talla en el peor de los casos: suma de la talla de las dos colas 
        // (que tenga que hacer el primer while y el segundo while enteros)
        
        // Coste promedio: n * log(n)
    }
    
    /** Problema 3:
     *  Disenyar un metodo estatico e iterativo cPEsLineal 
     *  que determine si un conjunto de valores reales se ajusta (aprox.) 
     *  a una funcion lineal creciente usando el siguiente algoritmo: 
     *  comprobar si la diferencia entre todo par de valores consecutivos, 
     *  en orden ascendente, esta acotada por un epsilon dado. 
     */
    public static boolean cPEsLineal(ColaPrioridad<Double> cP, double epsilon) {
        // COMPLETAR
        return false;
    }
    
    /** Problema 4:
     *  Disenyar un metodo estatico, generico e iterativo cPTopK 
     *  que, dado un array de datos v y un entero k, 
     *  devuelva una Cola de Prioridad con los k mejores (Top K) datos de v. 
     *  El metodo debe tener un coste O(X log k), siendo X la longitud de v.
     */
    public static <E extends Comparable<E>> ColaPrioridad<E> cPTopK(
        E[] v, int k) 
    {
        // COMPLETAR
    }
    
}