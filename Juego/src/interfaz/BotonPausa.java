package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class BotonPausa extends JButton{
	private AtomicBoolean pausa;
	public BotonPausa(AtomicBoolean pausa){
		this.pausa=pausa;
		 this.addActionListener(new ActionListener() {            
	            public void actionPerformed(ActionEvent e) {
	                pausar();
	            }
	        });
	}
	
	public void pausar(){
		this.pausa.set(true);
	}
}
