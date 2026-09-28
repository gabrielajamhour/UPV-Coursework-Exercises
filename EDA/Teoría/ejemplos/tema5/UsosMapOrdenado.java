package ejemplos.tema5;

import librerias.estructurasDeDatos.modelos.MapOrdenado;
import librerias.estructurasDeDatos.modelos.EntradaMap;
import librerias.estructurasDeDatos.modelos.ListaConPI; 
import librerias.estructurasDeDatos.lineales.LEGListaConPI; 
import librerias.estructurasDeDatos.jerarquicos.ABBMapOrdenado;

/**
 * class UsosMapOrdenado.
 * 
 * @author FTG 
 * @version 2.0
 */

public class UsosMapOrdenado {
    
    /** Diseñar un método estático, genérico e iterativo entradas 
     *  que devuelva una ListaConPI con las Entradas de un Map m, no vacío, 
     *  ordenadas ascendentemente. 
     */
    public static <C extends Comparable<C>, V> 
    ListaConPI<EntradaMap<C, V>> entradas(MapOrdenado<C, V> m) 
    {
        ListaConPI<EntradaMap<C, V>> res = new LEGListaConPI<>();
        
        EntradaMap<C, V> e = m.recuperarEntradaMin();
        res.insertar(e);
        
        while(m.sucesorEntrada(e.getClave()) != null) {
            e = m.sucesorEntrada(e.getClave());
            res.insertar(e);
        }
        
        return res;
    }
    
    /** Diseñar un método estático, genérico e iterativo mapSort 
     *  que, con la ayuda de un MapOrdenado, 
     *  ordene los elementos (Comparable) de un array v no vacío, y sin repetidos.  
     */
    public static <C extends Comparable<C>> void mapSort(C[] v) {
        MapOrdenado<C, C> m = new ABBMapOrdenado<>();
        
        for (int i=0; i < v.length; i++) m.insertar(v[i], v[i]);
        
        v[0] = m.recuperarMin();
        
        for (int i=1; i< v.length; i++) {
            v[i] = m.sucesor(v[i-1]);
        }
    }
    
    /** Diseñar un método estático, e iterativo hayDosQueSuman 
     *  que, dados un array v, no vacío, de enteros y un entero k, 
     *  determine si existen en v dos números cuya suma sea k. 
     *  Usar un Map Ordenado como EDA auxiliar.
     */
    public static boolean hayDosQueSuman(int[] v, int k) {
        MapOrdenado<Integer, Integer> m = new ABBMapOrdenado<>();
        
        for (int i=0; i < v.length; i++) m.insertar(v[i], i);
        
        Integer min = m.recuperarMin();
        Integer max = m.recuperarMax();
        
        for (int i=0; i < v.length -1; i++) {
            int suma = min + max;
            if (suma == k) return true;
            else if (suma < k) min = m.sucesor(min);
            else max = m.predecesor(max);
        }
        
        return false;
    }
}
