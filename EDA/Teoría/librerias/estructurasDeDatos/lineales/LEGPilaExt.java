package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

public class LEGPilaExt<E extends Comparable<E>> extends LEGPila<E> implements PilaExt<E> 
{
    public E base() {
        NodoLEG<E> aux = tope;
        while (aux.siguiente != null) {aux = aux.siguiente;}
        return aux.dato;
    }
    
    public E minimo(){
        // Utilizando atributos
        NodoLEG<E> aux = tope;
        E min = null;
        
        while(aux != null){
            if(min == null || aux.dato.compareTo(min) < 0){
                min = aux.dato;
            }
            aux = aux.siguiente;
        }
        
        return min;
    }
    
    public E minimo2(){
        // Utilizando métodos
        if(esVacia()){return null;}
        
        LEGPila<E> aux = new LEGPila<E>();
        E min = desapilar();
        aux.apilar(min);
        
        while(!esVacia()){
            E x = desapilar();
            if (x.compareTo(min) < 0) {min = x;}
            aux.apilar(x);
        }
        
        while(!aux.esVacia()){
             apilar(aux.desapilar());
        }
        
        return min;
    }
    
    public E minimo2Recursivo(){
        // Utilizando métodos de manera recursiva
        if(esVacia()){return null;}
        E dato = desapilar();
        
        E minResto = minimo2Recursivo();
        
        apilar(dato);
        
        if(minResto == null || dato.compareTo(minResto) < 0) {return dato;}
        return minResto;
    }
}