package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

public class LEGListaConPIOrdenada<E extends Comparable> extends LEGListaConPI<E> implements ListaConPI<E>
{
    @Override
    public void insertar(E e) { 
        inicio();
        
        while(!esFin() && recuperar().compareTo(e) < 0) {
            siguiente();
        }
        // En este punto, PI se encuentra un elemento antes del primer
        // elemento que es >= e
        
        super.insertar(e); // Usamos el método de la clase base
    } 
}