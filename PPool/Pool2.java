// CSD feb 2015 Juansa Sendra

public class Pool2 extends Pool{ //max kids/instructor
    int instructorsSwimming = 0;
    int kidsSwimming = 0;
    int kiAux; // Máximo número de niños por instructor
    
    public void init(int ki, int cap) {
        kiAux = ki;
    }
    public synchronized void kidSwims() throws InterruptedException {
        while(kidsSwimming + 1 > instructorsSwimming * kiAux || instructorsSwimming == 0) {
            log.waitingToSwim();
            wait();
        }
        kidsSwimming++;
        log.swimming();
    }
    public synchronized void kidRests() {
        kidsSwimming--;
        notifyAll();
        log.resting();
    }
    public synchronized void instructorSwims() {
        instructorsSwimming++;
        notifyAll();
        log.swimming();
    }
    public synchronized void instructorRests() throws InterruptedException {
        while(kidsSwimming > (instructorsSwimming - 1) * kiAux) {
            log.waitingToRest();
            wait();
        }
        instructorsSwimming--;
        log.resting();
    }
}
