package aplicaciones.hospital;

/**
 * Write a description of class ServidorColaFIFOPlus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ServidorColaPrioridadPlus extends ServidorColaPrioridad implements ServidorQuirofanoPlus
{
    private int talla;
    
    /**
     * Constructor for objects of class ServidorColaPrioridadPlus
     */
    public ServidorColaPrioridadPlus()
    {
        talla = 0;
    }
    
    public int numPacientes() {
        return talla;
    }
    
    public Paciente transferirPaciente() {
        Paciente p = cP.eliminarMin();
        talla--;
        return p;
    }
    
    public void distribuirPacientes(ServidorQuirofanoPlus s) {
        Paciente[] pacientes = new Paciente[talla];
        
        for(int i = 0; talla != 0; i++) {
            pacientes[i] = transferirPaciente();
        }
        
        for(int i = 0; i < pacientes.length; i++){
            if(i % 2 == 0) {this.insertarEnEspera(pacientes[i]);}
            else {s.insertarEnEspera(pacientes[i]);}
        }
    }
    
    public void insertarEnEspera(Paciente p){
        super.insertarEnEspera(p);
        talla++;
    }
    
    public Paciente operarPaciente(int h){
        talla--;
        return super.operarPaciente(h);
    }
}