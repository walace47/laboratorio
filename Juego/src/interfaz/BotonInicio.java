package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JButton;

import juego.MonitorPausa;

public class BotonInicio extends JButton{
	private MonitorPausa monitor;
	private AtomicBoolean pausa;
	public BotonInicio(AtomicBoolean pausa,MonitorPausa monitor){
		this.monitor=monitor;
		this.pausa=pausa;
		 this.addActionListener(new ActionListener() {            
	            public void actionPerformed(ActionEvent e) {
	                iniciar();
	            }
	        });
	}
	
	public void iniciar(){
		this.pausa.set(false);
		monitor.inicio();
	}
}
