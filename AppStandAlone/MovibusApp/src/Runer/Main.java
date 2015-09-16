package Runer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logica.MovibusLogic;

public class Main  extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	MovibusLogic logic;
	JPanel everything;
	JPanel buts;
	JButton but1;
	JButton but2;
	JPanel userIn;
	JLabel lab;
	JTextField input;
	JButton check;
	JPanel vcubs;
	JLabel success;
	JLabel numVc;
	JButton refill;

	public Main(){
		logic = new MovibusLogic();
		this.setSize(400, 400);
		everything = new JPanel();
		everything.setLayout(new BorderLayout());
		//BOTONES
		buts = new JPanel();
		buts.setLayout(new GridLayout());
		but1 = new JButton("Reservar Vcub");
		but1.setActionCommand("retirar");
		but1.addActionListener(this);
		but2 = new JButton("Devolver Vcub");
		but2.setActionCommand("devolver");
		but2.addActionListener(this);
		buts.add(but1);
		buts.add(but2);
		//USER INPUT
		userIn = new JPanel();
		userIn.setLayout(new GridLayout());
		lab = new JLabel("Usuario : ");
		input = new JTextField();
		check = new JButton("Verificar");
		check.addActionListener(this);
		check.setActionCommand("verificar");
		userIn.add(lab);
		userIn.add(input);
		userIn.add(check);
		//ADMININPUT
		refill = new JButton("Llenar Estacion");
		refill.setActionCommand("refill");
		refill.addActionListener(this);
		//VCUBRTA
		vcubs = new JPanel();
		vcubs.setLayout(new BorderLayout());
		success = new JLabel("Operacion Exitosa");
		numVc = new JLabel();
		vcubs.add(numVc, BorderLayout.SOUTH);
		vcubs.setVisible(false);
		//EVERYTHING
		everything.add(userIn, BorderLayout.NORTH);
		everything.add(buts, BorderLayout.SOUTH);
		everything.add(vcubs, BorderLayout.CENTER);
		this.add(everything);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args){
		Main main = new Main();
	}

	@Override
	public void dispose(){
		logic.persist();
		super.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals("retirar")){
			if(!input.getText().equals("")){
				String[] rta = null;
				try {
					success.setText("Operacion Exitosa");
					vcubs.add(success, BorderLayout.CENTER);
					numVc.setText("Numero de Vcubs rentadas : "+rta[0]);
				} catch (Exception e) {
					success.setText("Operacion Fallida");
					vcubs.add(success, BorderLayout.CENTER);
					numVc.setText("Error con el numero de identificacion");
				}
				vcubs.setVisible(true);
			}else{
				JOptionPane.showMessageDialog( this, "Numero de identificacion invalido.", "Error de identificacion", JOptionPane.ERROR_MESSAGE );
			}
		}
		if(event.getActionCommand().equals("devolver")){
			if(!input.getText().equals("")){
				String[] rta=null;
				try{
					success.setText("Operacion Exitosa");
					vcubs.add(success, BorderLayout.CENTER);
					numVc.setText("Numero de Vcubs rentadas : "+rta[0]);
				}catch(Exception e){
					success.setText("Operacion Fallida");
					vcubs.add(success, BorderLayout.CENTER);
					numVc.setText("Error con el numero de identificacion.");
				}
				vcubs.setVisible(true);
			}else{
				JOptionPane.showMessageDialog( this, "Numero de identificacion invalido.", "Error de identificacion", JOptionPane.ERROR_MESSAGE );
			}
		}
		if(event.getActionCommand().equals("verificar")){
			if(!input.getText().equals("") && !input.getText().equals("admin")){
				String[] rta=null;
				try{
					success.setText("Usuario: " + rta[0].replace("\"", ""));
					vcubs.add(success, BorderLayout.CENTER);
					numVc.setText("Numero de Vcubs rentadas : "+rta[1]);
				}catch(Exception e){
					success.setText("Operacion Fallida");
					vcubs.add(success, BorderLayout.CENTER);
					numVc.setText("Error con el numero de identificacion.");
				}
				vcubs.setVisible(true);
			}else if(input.getText().equals("admin")){
				vcubs.remove(success);
				vcubs.add(refill, BorderLayout.CENTER);
				numVc.setText("");
				vcubs.setVisible(true);
				refill.setVisible(true);
			}else{
				JOptionPane.showMessageDialog( this, "Numero de identificacion invalido.", "Error de identificacion", JOptionPane.ERROR_MESSAGE );
			}
		}
		if(event.getActionCommand().equals("refill")){
			try{
				vcubs.remove(refill);
				success.setText("Estacion llenada a capacidad maxima de : ");
				vcubs.add(success, BorderLayout.CENTER);
			}catch(Exception e){
				vcubs.remove(refill);
				success.setText("Error en la operacion.\nPor favor comuniquese con el due�o del servidor.");
				vcubs.add(success, BorderLayout.CENTER);
			}
			vcubs.setVisible(true);
		}
		this.repaint();
	}
}

