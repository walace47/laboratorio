package interfaz;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import juego.MonitorPausa;

public class Tablero extends JFrame {

    private CelulaVisual[][] matriz;
    private JPanel botones;
    private JPanel celulas;
    private AtomicBoolean pausa;
    private MonitorPausa monitor;

    public Tablero(CelulaVisual[][] matriz,AtomicBoolean pausa,MonitorPausa monitor) {
    	this.monitor=monitor;
        this.matriz = matriz;
        this.pausa=pausa;
        botones = new JPanel();
        celulas = new JPanel();
        int tam = matriz.length;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             //hace que cuando se cierre la ventana finalice la ejecucion
        this.setSize(500, 500);                                     //tamaño inicial de la ventana
        celulas.setLayout(new GridLayout(tam, tam));                   //se utilizara para agregar los botones
        BotonInicio botonInicio=new BotonInicio(this.pausa,this.monitor);//Se crea el boton de inicio
    	BotonPausa botonPausa=new BotonPausa(this.pausa);
    	ImageIcon iconoIncio=new ImageIcon("Play.PNG");
    	ImageIcon iconoPausa=new ImageIcon("Pause.PNG");
    	botonInicio.setIcon(iconoIncio);
    	botonPausa.setIcon(iconoPausa);
    	botones.add(botonInicio);
    	botones.add(botonPausa);
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                celulas.add(matriz[i][j]);                             //se agrega el boton en la grilla
            }
        }
        this.getContentPane().setLayout(new BorderLayout());        //se utiliza un BorderLayout para acomodar los componentes
        this.getContentPane().add(botones, BorderLayout.NORTH);     //se posiciona la botonera arriba
        this.getContentPane().add(celulas, BorderLayout.CENTER);

    }

  

    

}