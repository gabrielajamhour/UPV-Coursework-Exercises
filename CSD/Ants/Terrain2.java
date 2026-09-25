import java.util.concurrent.locks.*;

/**
 * Terrain2
 * 
 * Monitor general (java.util.concurrent) con una variable condition por cada
 * celda del territorio: una hormiga se suspende en la variable condición asociada
 * a la celda ocupada a la que quiere desplazarse
 * 
 * @author CSD Juansa Sendra
 * @version 2021
 */
public class Terrain2 implements Terrain {
    Viewer v;
    Lock lock;
    Condition[][] cond;
    
    public  Terrain2 (int t, int ants, int movs, String msg) {
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
                cond[dest.x][dest.y].await();
                v.retry(a);
            }
            
            v.go(a);
            cond[mipos.x][mipos.y].signal();
       } finally {lock.unlock();}
    }
}