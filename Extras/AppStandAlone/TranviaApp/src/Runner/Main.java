package runner;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;

import logic.Logger;
import logic.TranviaLogic;

public class Main extends JFrame implements ActionListener{

	/**
	 * Constante de serializacion de la clase.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logica de la aplicacion
	 */
	static TranviaLogic logic;
	/**
	 * Boton de panico de la aplicacion
	 */
	JButton panic;

	/**
	 * Constructor de la interfaz.
	 */
	public Main(){
		//Boton de panico
		panic = new JButton("PANIC");
		panic.setActionCommand("PANIC");
		panic.addActionListener(this);
		this.setSize(400, 400);
		this.setLayout(new BorderLayout());
		this.add(panic, BorderLayout.CENTER);
		this.setTitle("Tranvia TBC");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Manejo de eventos de la aplicacion de tranvia.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//En caso de panico en el tranvia
		if("PANIC".equals(e.getActionCommand())){
			try {
				//Reporta la emergencia al servidor central
				logic.reportarEmergencia("Breve descripcion de la emergencia", 0, "Baja");
			} catch (Exception e1) {
				//En caso de error imprime la excepcion en el logger de la aplicacion. esperemos que nunca falle!!
				Logger.info(e1);
			}
		}
	}

	/**
	 * Metodo de finalizacion y cierre de la aplicacion.
	 */
	@Override
	public void dispose(){
		logic.persist();
		super.dispose();
	}

	/**
	 * Metodo ejecutable de la aplicacion y constructor de la interfaz
	 * @param args argumentos vacios.
	 */
	public static void main(String[] args) {
		logic = new TranviaLogic();
		EnviarPosicion enviador = new EnviarPosicion(logic);
		enviador.start();
	}


}

/**
 * Clase de simulacion de envio de posiciones del tranvia.
 * @author s.baquero10
 *
 */
final class EnviarPosicion extends Thread{
	//Atributo de la logica de la simulacion.
	private static TranviaLogic logic;
	//Constructor de la simulacion
	public EnviarPosicion(TranviaLogic logic){
		this.logic  = logic;
	}
	
	//Envio constante de posiciones nuevas al servidor.
	@Override
	public void run(){
		Date d = new Date();
		while(true){
			Date c = new Date();
			if((c.getTime()-d.getTime())>5000){
				d=c;
				try {
					logic.enviarPosicion();
				} catch (Exception e) {
					Logger.info(e);
				}
			}
		}
	}
}
