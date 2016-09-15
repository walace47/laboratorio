package juego;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Submundo extends Thread {
    // cada submundo le corresponde una fila del mundo

    private final int LONGITUD; // la longitud que tiene la fila
    private int fila;   //que fila le corresponde dentro del mundo
    private Celula[][] matriz;//el mundo
    private CyclicBarrier barrera; //barrera que cordina cada submundo y la salida por pantalla
    private AtomicBoolean pausa;
    private MonitorPausa pausar;

    public Submundo(int fila, Celula[][] matriz, CyclicBarrier barrera,AtomicBoolean pausa) {
        this.fila = fila;
        this.matriz = matriz;
        this.barrera = barrera;
        this.LONGITUD = matriz.length;
        this.pausa=pausa;
        
    }



	public void run() {
        while (true) {
    
			
            if (!pausa.get()) {
                try {
                	this.barrera.await();
                    this.consulta();
                    this.barrera.await();
                    this.actualizar();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Submundo.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (BrokenBarrierException ex) {
                    Logger.getLogger(Submundo.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                this.pausar.pausar();
            }
        }
    }

    public void setPausar(MonitorPausa pausar) {
        this.pausar = pausar;
    }
    
    

    public int cantVivas(int fila, int columna) {
        //Cuenta la cantidad de celulas vivas adyacentes ala celula
        int acumulador = 0;
        int x, y;// las posiciones dentro del mundo
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                //con x y se calculan la posicion dentro del arreglo
                x = i;
                y = j;
                if (x == -1) {
                    x = LONGITUD - 1;
                }
                if (y == -1) {
                    y = LONGITUD - 1;
                }
                x = x % LONGITUD;
                y = y % LONGITUD;
                if (matriz[x][y].getEstado() && (x != fila || y != columna)) {
                    acumulador++;
                }
            }
        }
        return acumulador;
    }

    public boolean nuevoEstado(int fila, int columna) {
        //Retorna el nuevo estado
        int acumulador;
        boolean resultado;
        acumulador = cantVivas(fila, columna);
        resultado = acumulador == 3 || (matriz[fila][columna].getEstado() && acumulador == 2); //retorna true  si esta viva y si tiene 2 o 3 vivas, o si tiene 3 vivas alrededor
        return resultado;
    }

    public void consulta() {
        for (int i = 0; i < LONGITUD; i++) {
            //Consulta la fila del hilo
            matriz[fila][i].setSiguienteEstado(nuevoEstado(fila, i));
        }
    }

    private void actualizar() {
        for (int i = 0; i < LONGITUD; i++) {
            //actualiza la fila del hilo
            matriz[fila][i].actualizar();
        }
    }

}
