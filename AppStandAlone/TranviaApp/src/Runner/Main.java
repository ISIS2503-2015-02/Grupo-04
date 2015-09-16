package Runner;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logic.TranviaLogic;

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
		if(e.getActionCommand().equals("PANIC")){
			try {
				logic.reportarEmergencia("Breve descripcion de la emergencia", 0, "Baja");
				System.out.println("PANIC");
			} catch (Exception e1) {
				e1.printStackTrace();
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
		Main main = new Main();
	}


}
final class EnviarPosicion extends Thread{
	private static TranviaLogic logic;
	public EnviarPosicion(TranviaLogic logic){
		this.logic  = logic;
	}
	
	public void run(){
		Date d = new Date();
		while(true){
			Date c = new Date();
			if((c.getTime()-d.getTime())>5000){
				d=c;
				try {
					logic.enviarPosicion();
					System.out.println("Envio Posicion");
				} catch (Exception e) {
				}
			}
		}
	}
}
