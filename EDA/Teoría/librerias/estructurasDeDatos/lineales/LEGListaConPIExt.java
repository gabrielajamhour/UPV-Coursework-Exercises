package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

/**
 * 
 */
public class LEGListaConPIExt<E> extends LEGListaConPI<E> implements ListaConPIExt<E>
{
    /**
     * Constructor for objects of class LEGListaConPIExt
     */
    public LEGListaConPIExt(){
        super.pri = super.ult = super.ant = new NodoLEG<E>(null);
        super.talla = 0;
    }
    
    /**
     * Devuelve True/False si la lista incluye o no el elemento
     */
    public boolean contiene(E e){
        inicio();
        
        while(!esFin()  ) {
            if(recuperar().equals(e)){ return true;}
            siguiente();
        }
        
        return false;
    }
    
    /**
     * Elimina el primer elemento e de la lista y
     * devuelve true o false si no lo encuentra
     */
    public boolean eliminarPrimero(E e){
        inicio();
        
        while(!esFin()) {
            if (recuperar().equals(e)) {
                eliminar();
                return true;
            }
            siguiente();
        }
        
        return false;
    }
     
    /**
     * Elimina el último elemento e de la lista y devuelve
     * true o false si no lo encuentra
     */
    public boolean eliminarUltimo(E e){
        NodoLEG<E> ultimo = null;
        inicio();
        
        while(!esFin()) {
            if(recuperar().equals(e)){ ultimo = ant; }
            // ultimo apuntará al nodo anterior al último elemento e
            siguiente();
        }
        
        if(ultimo == null) {return false;}
        
        ant = ultimo; // movemos ant al nodo anterior que vamos a eliminar
        eliminar(); // elimina ant.siguiente
        return true;
    }
    
    /**
     * Elimina todos los elementos iguales a e de la lista y
     * devuelve true o false si no los encuentra
     */
    public boolean eliminarTodos(E e){
        inicio();
        boolean found = false;
        
        while(!esFin()){
            if(recuperar().equals(e)) {
                eliminar(); // ya pasa al siguiente
                found = true;
            } else { siguiente(); } // solo avanzamos si no eliminó
        }
        
        return found;
    }
    
    /**
     * Une una nueva lista a esta lista
     */
    public void concatenar(ListaConPI<E> l){
        fin();
        l.inicio();
        while(!l.esFin()){
            insertar(l.recuperar());
            l.eliminar();
        }
    }
    
    /**
     * Borrar la lista (elimina todos los elementos)
     */
    public void vaciar(){
        if(esVacia()){return;}
        
        inicio();
        while(!esFin()){eliminar();}
    }
    
    /**
     * Coloca el PDI en e. Si no se encuentra e, el PDI se colocará al
     * final de la lista
     */
    public void buscar(E e){
        inicio();
        
        while(!esFin()){
            if(recuperar().equals(e)) {return;}
            siguiente();
        }
    }
    
    /**
     * Invierte el orden de los elementos de la lista
     */
    public void invertir(){
        NodoLEG<E> prev = null;
        NodoLEG<E> curr = pri.siguiente; // primer nodo real
        NodoLEG<E> next;

        ult = curr; // el primer nodo será el último después de invertir
    
        while (curr != null) {
            next = curr.siguiente;
            curr.siguiente = prev;
            prev = curr;
            curr = next;
        }
    
        pri.siguiente = prev; // ahora pri apunta al nuevo primer nodo
    }
    
    /**
     * Devuelve una cadena con la descripción de los elementos de la
     * lista
     */
    public String toString(){
        return super.toString();
    }
}