package interfaz;

import juego.*;

import util.TecladoIn;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    
    public static void creacionAleatoria(CelulaVisual[][] mundoVisual,Celula[][] mundo){
        Random estado=new Random();
        int longitud=mundo.length; 
        for (int i = 0; i < longitud; i++) {
            for (int j = 0; j < longitud; j++) {
                mundoVisual[i][j] = new CelulaVisual();
                mundo[i][j] = new Celula(estado.nextBoolean());
                mundo[i][j].addObserver(mundoVisual[i][j]);                                         //se agrega a la celula su observador
                mundoVisual[i][j].update(null, mundo[i][j].getEstado());                            //se asigna el primer estado a la celula visaul
                mundoVisual[i][j].setObservable(mundo[i][j]);
            }
        }
    
}

    public static void main(String[] args) throws InterruptedException {
        int longitud;
        CelulaVisual[][] mundoVisual;
        Tablero tablero;
        MonitorPausa pausar;
        AtomicBoolean pausa=new AtomicBoolean(true);
        pausar=new MonitorPausa();
        System.out.println("Ingrese la longitud de la matriz (cuadrada): ");
        longitud = TecladoIn.readLineInt();
        CyclicBarrier barrera = new CyclicBarrier(longitud + 1);
        mundoVisual = new CelulaVisual[longitud][longitud];
        Celula[][] mundo = new Celula[longitud][longitud]; // se crea el mundo
        creacionAleatoria(mundoVisual,mundo);
        tablero = new Tablero(mundoVisual,pausa,pausar);
        tablero.setVisible(true);
//        System.out.println(toString(mundo));
        for (int i = 0; i < longitud; i++) {
           Submundo sub;
            sub= new Submundo(i, mundo, barrera,pausa);
            sub.setPausar(pausar);
            sub.start();
            
        }
        while (true) {
            try {
                barrera.await();
//                System.out.println(toString(mundo));
                barrera.await();
                Thread.sleep(1000);
            } catch (BrokenBarrierException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String toString(Celula[][] m) {
        //muestra el mundo
        String res = "|";
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                res += m[i][j].toString();
                if (j < m[0].length - 1) {
                    res += "  ";
                }
            }
            if (i < m.length - 1) {
                res += "|\n|";
            }
        }
        res += "|";
        res += "\n";
        return res;
    }

}