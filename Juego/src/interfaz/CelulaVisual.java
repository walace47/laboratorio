package interfaz;

import juego.Celula;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;

public class CelulaVisual extends JButton implements Observer {

    private Celula miObservable;

    public CelulaVisual() {
        this.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                miObservable.cambiarEstado();
            }
        });
    }

    @Override

    public void update(Observable o, Object estado) {
        if ((boolean) estado) {
            this.setBackground(Color.green);
        } else {
            this.setBackground(Color.red);
        }

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("hola");
        miObservable.cambiarEstado();
    }

    public void setObservable(Celula miObservable) {
        this.miObservable = miObservable;
    }

}
