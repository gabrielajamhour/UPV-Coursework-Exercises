package aplicaciones.censo;

import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPIOrdenada;

/**
 * ListaElectores: representa una lista de habitantes, 
 *                 registrados en el censo, y por ello, electores
 * 
 * @author  Profesores EDA 
 * @version Septiembre 2023
 */

public class ListaElectores {
   
    private ListaConPI<Habitante> censo;
    private int talla;
    
    /**
     * Métodos consultores de atributos
     */
    public ListaConPI<Habitante> getCenso() { return censo; }
    public int getTalla() { return talla; }
    
    /**
     * Devuelve el String que representa una ListaElectores 
     * 
     * @return el String con la ListaElectores en el formato texto dado. 
     */
    public String toString() {
        String res = "";
        if (talla == 0) return res;
        censo.inicio();
        for (int pos = 0; pos <= censo.talla() - 2; pos++) {
            res += censo.recuperar() + ", \n";
            censo.siguiente();
        }
        res += censo.recuperar();
        return res;
    }
   
    /**
     * Crea una ListaElectores...
     * 
     * @param orden Un boolean que indica si el censo,  
     *              debe estar ordenada ascendentemente (true) o no (false). 
     *              
     * @param n     Un int que indica la talla, número de elementos, de la lista              
     */
    public ListaElectores(boolean orden, int n) {
        talla = n;
        
        if(orden){censo = new LEGListaConPIOrdenada<Habitante>();}
        else {censo = new LEGListaConPI<Habitante>();}
        
        while(censo.talla() < n){
            Habitante h = new Habitante();
            if(indice(h) == -1){censo.insertar(h);}
        }
    }
    
    /**
     * Devuelve el índice o posicion del Habitante h en una ListaElectores, 
     * o -1 si h no forma parte de la lista. 
     * 
     * @param h un Habitante
     * @return  el índice de h en un censo, un valor int
     *          0 o positivo si h esta en el censo      
     *          o -1 en caso contrario
     */
    protected int indice(Habitante h) {
        censo.inicio();
        int i = 0;
        
        while(!censo.esFin()){
            if(censo.recuperar().equals(h)) {return i;}
            censo.siguiente(); i++;
        }
        
        return -1;
    }
    
    public ListaElectores getCensoCP(int cp1, int cp2){
        ListaElectores res = new ListaElectores(true, 0);
        censo.inicio();
        
        while(!censo.esFin()){
            int cp = censo.recuperar().getCp();
            if(cp2 >= cp && cp >= cp1){
                res.getCenso().insertar(censo.recuperar());
            }
            censo.siguiente();
        }
        
        res.talla = res.getCenso().talla();
        return res;
    }
    
    public ListaElectores buscador(String prefijo){
        ListaElectores res = new ListaElectores(true, 0);
        censo.inicio();
        
        while(!censo.esFin()){
            Habitante h = censo.recuperar();
            String apellido1 = h.getApellido1();
            String apellido2 = h.getApellido2();
            
            if(apellido1.startsWith(prefijo)){
                res.getCenso().insertar(h);
            } else if(apellido2.startsWith(prefijo)) {
                res.getCenso().insertar(h);
            }
            
            censo.siguiente();
        }
        
        res.talla = res.getCenso().talla();
        return res;
    }
}
