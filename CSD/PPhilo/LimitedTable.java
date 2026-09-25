// CSD Mar 2013 Juansa Sendra

public class LimitedTable extends RegularTable { //max 4 in dinning-room
    public LimitedTable(StateManager state) {super(state);}
    
    int philosophersSitted = 0;
    
    public synchronized void enter(int id) throws InterruptedException {
        while(philosophersSitted == 4){
            state.wenter(id);
            wait();
        }
        philosophersSitted++;
        state.enter(id);
    }
    public synchronized void exit(int id)  {
        state.exit(id);
        philosophersSitted--;
    }
}
