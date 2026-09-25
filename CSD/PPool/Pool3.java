// CSD feb 2015 Juansa Sendra

public class Pool3 extends Pool{ //max capacity
    int instructorsSwimming = 0;
    int kidsSwimming = 0;
    int kiAux; // Máximo número de niños por instructor
    int capAux; // Aforo máximo de la piscina
    
    public void init(int ki, int cap) {
        kiAux = ki;
        capAux = cap;
    }
    public synchronized void kidSwims() throws InterruptedException {
        while(kidsSwimming + 1 + instructorsSwimming > capAux || kidsSwimming + 1 > instructorsSwimming * kiAux || instructorsSwimming == 0) {
            log.waitingToSwim();
            wait();
        }
        kidsSwimming++;
        log.swimming();
    }
    public synchronized void kidRests() {
        kidsSwimming--;
        log.resting();
        notifyAll();
    }
    public synchronized void instructorSwims() throws InterruptedException {
        while(kidsSwimming + instructorsSwimming + 1 > capAux) {
            log.waitingToSwim();
            wait();
        }
        instructorsSwimming++;
        log.swimming();
        notifyAll();
    }
    public synchronized void instructorRests() throws InterruptedException {
        while(kidsSwimming > (instructorsSwimming - 1) * kiAux) {
            log.waitingToRest();
            wait();
        }
        instructorsSwimming--;
        log.resting();
        notifyAll();
    }
}
