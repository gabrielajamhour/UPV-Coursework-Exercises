package librerias.estructurasDeDatos.modelos;

/**
 * Modelo Extendido de una Lista Con Punto de Interés
 */
public interface ListaConPIExt<E> extends ListaConPI<E>
{
    /**
     * Devuelve True/False si la lista incluye o no el elemento
     */
    boolean contiene(E e);
    
    /**
     * Elimina el primer elemento e de la lista y
     * devuelve true o false si no lo encuentra
     */
    boolean eliminarPrimero(E e);
    
    /**
     * Elimina el último elemento e de la lista y devuelve
     * true o false si no lo encuentra
     */
    boolean eliminarUltimo(E e);
    
    /**
     * Elimina todos los elementos iguales a e de la lista y
     * devuelve true o false si no los encuentra
     */
    boolean eliminarTodos(E e);
    
    /**
     * Une una nueva lista a esta lista
     */
    void concatenar(ListaConPI<E> l);
    
    /**
     * Borrar la lista (elimina todos los elementos)
     */
    void vaciar();
    
    /**
     * Coloca el PDI en e. Si no se encuentra e, el PDI se colocará al
     * final de la lista
     */
    void buscar(E e);
    
    /**
     * Invierte el orden de los elementos de la lista
     */
    void invertir();
    
    /**
     * Devuelve una cadena con la descripción de los elementos de la
     * lista
     */
    String toString();
}