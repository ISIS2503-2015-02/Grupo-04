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

	private static final long serialVersionUID = 1L;
	static TranviaLogic logic;
	JButton panic;

	public Main(){
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if("PANIC".equals(e.getActionCommand())){
			try {
				logic.reportarEmergencia("Breve descripcion de la emergencia", 0, "Baja");
			} catch (Exception e1) {
				Logger.info(e1);
			}
		}
	}

	@Override
	public void dispose(){
		logic.persist();
		super.dispose();
	}

	public static void main(String[] args) {
		logic = new TranviaLogic();
		EnviarPosicion enviador = new EnviarPosicion(logic);
		enviador.start();
	}


}
final class EnviarPosicion extends Thread{
	private static TranviaLogic logic;
	public EnviarPosicion(TranviaLogic logic){
		this.logic  = logic;
	}
	
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
