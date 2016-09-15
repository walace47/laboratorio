package juego;


import java.util.Observable;

public class Celula extends Observable{

    private boolean estado;
    private boolean siguienteEstado;

    public Celula(boolean estado) {
        this.estado = estado;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean getSiguienteEstado() {
        return siguienteEstado;
    }

    public void setSiguienteEstado(boolean siguienteEstado) {
        this.siguienteEstado = siguienteEstado;
    }
    
    public void cambiarEstado() {
        this.estado = !this.estado;
        notificar();
    }
    
    public void actualizar() {
        this.estado = this.siguienteEstado;
        notificar();
    }
    
    public void notificar() {
        this.setChanged();
        this.notifyObservers(estado);
    }

    public String toString() {
        String respuesta = "\033[32mO\033[30m";
        if (!estado) {
            respuesta = "\033[31mX\033[30m";
        }
        return respuesta;
    }
    
    
}