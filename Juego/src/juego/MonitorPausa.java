package juego;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joan
 */
public class MonitorPausa {
    public synchronized void pausar(){
        try {
            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(MonitorPausa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void inicio(){
        notifyAll();
    }
}