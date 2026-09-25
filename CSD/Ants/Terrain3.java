import java.util.concurrent.locks.*;
import java.util.concurrent.TimeUnit;

/**
 * Terrain3
 * 
 * Monitor general (java.util.concurrent) con una variable condition por celda
 * del territorio. Incluye un mecanismo para resolver el problema de los 
 * interbloqueos.
 * 
 * @author CSD Juansa Sendra
 * @version 2021
 */
public class Terrain3 implements Terrain {
    Viewer v;
    Lock lock;
    Condition[][] cond;
    
    public  Terrain3 (int t, int ants, int movs, String msg) {
        v=new Viewer(t,ants,movs,msg);
        lock = new ReentrantLock();
        cond = new Condition[t][t];
        for (int i=0; i<t; i++) {
            for (int j=0; j<t; j++) {
                cond[i][j] = lock.newCondition();
            }
        }
    }
    public void     hi      (int a) {
        lock.lock();
        try{
            v.hi(a);
        } finally {lock.unlock();}
    }
    public void     bye     (int a) {
        lock.lock();
        try{
            Pos mipos = v.getPos(a);
            cond[mipos.x][mipos.y].signal();
            
            v.bye(a);
        } finally {lock.unlock();}
    }
    public void     move    (int a) throws InterruptedException {
       lock.lock();
       try{
            v.turn(a);
            
            Pos dest = v.dest(a); 
            Pos mipos = v.getPos(a);
            
            while (v.occupied(dest)) {
                boolean signaled = cond[dest.x][dest.y].await(300, TimeUnit.MILLISECONDS);
                // plazo máximo de espera
                
                if(!signaled && v.occupied(dest)){
                    v.chgDir(a);
                    dest = v.dest(a);
                    v.retry(a); // indica visualmente que se ha cambiado la dirección
                }
            }
            
            v.go(a);
            cond[mipos.x][mipos.y].signal();
       } finally {lock.unlock();}
    }
}