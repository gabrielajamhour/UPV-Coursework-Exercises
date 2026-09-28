package ejemplos.tema2;

/**
 * Ejercicios del Tema 2
 * La estrategia Divide y Vencerás
 */

public class ejercicios
{
    public static int puntoCruce(int[] v){
         return puntoCruce(v, 0, v.length - 1);
    }
    
    private static int puntoCruce(int[] v, int i, int j) {
        // Tengo que devolver el índice del menor número negativo
        int m = (i + j)/2;
        
        if (v[m] <= 0) {
            if(v[m+1] > 0) {return m;}
            else {return puntoCruce(v, m+1, j);}
        }
        
        return puntoCruce(v, i, m-1);
    }
}