package Runner;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logic.EstacionVcubLogic;

public class Main extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	EstacionVcubLogic logic;
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
	JLabel denied;
	JLabel checkLab;
	JLabel numVc;
	JButton refill;
	JLabel refillSuccess;

	public Main(){
		logic = new EstacionVcubLogic();
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
		success = new JLabel("SUCCESS");
		denied = new JLabel("Denied");
		checkLab = new JLabel("Usuario : ");
		numVc = new JLabel();
		vcubs.add(numVc, BorderLayout.SOUTH);
		vcubs.setVisible(false);
		//EVERYTHING
		everything.add(userIn, BorderLayout.NORTH);
		everything.add(buts, BorderLayout.SOUTH);
		everything.add(vcubs, BorderLayout.CENTER);
		this.setTitle(logic.getNombreEstacion());
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
				String[] rta = logic.retiroVcub(Long.parseLong(input.getText())).split(":");
				if(Integer.parseInt(rta[1])!=200){
					vcubs.add(denied, BorderLayout.CENTER);
					numVc.setText("Numero de Vcubs rentadas : "+rta[0]);
				}else{
					vcubs.add(success, BorderLayout.CENTER);
					numVc.setText("Numero de Vcubs rentadas : "+rta[0]);
				}
				vcubs.setVisible(true);
			}else{
				JOptionPane.showMessageDialog( this, "Numero de identificacion invalido.", "Error de identificacion", JOptionPane.ERROR_MESSAGE );
			}
		}
		if(event.getActionCommand().equals("devolver")){
			if(!input.getText().equals("")){
				String[] rta;
				try{
					rta = logic.devolucionVcub(Long.parseLong(input.getText())).split(":");
					if(Integer.parseInt(rta[1])!=200){
						vcubs.add(denied, BorderLayout.CENTER);
						numVc.setText("Numero de Vcubs rentadas : "+rta[0]);
					}else{
						checkLab.setText(checkLab.getText()+rta[2]);
						vcubs.add(checkLab, BorderLayout.CENTER);
						numVc.setText("Numero de Vcubs rentadas : "+rta[0]);
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia de usuario", JOptionPane.WARNING_MESSAGE);
				}
				vcubs.setVisible(true);
			}else{
				JOptionPane.showMessageDialog( this, "Numero de identificacion invalido.", "Error de identificacion", JOptionPane.ERROR_MESSAGE );
			}
		}
		if(event.getActionCommand().equals("verificar")){
			if(!input.getText().equals("") && !input.getText().equals("admin")){
				String[] rta;
				try{
					rta = logic.verificarUsuario(Long.parseLong(input.getText())).split(":");
					if(Integer.parseInt(rta[2])!=200){
						vcubs.add(denied, BorderLayout.CENTER);
						numVc.setText("Numero de Vcubs rentadas : "+rta[0]);
					}else{
						checkLab.setText(checkLab.getText() + " " + rta[1]);
						vcubs.add(checkLab, BorderLayout.CENTER);
						numVc.setText("Numero de Vcubs rentadas : "+rta[0]);
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia de usuario", JOptionPane.WARNING_MESSAGE);
				}
				vcubs.setVisible(true);
			}else if(input.getText().equals("admin")){
				vcubs.add(refill, BorderLayout.CENTER);
			}else{
				JOptionPane.showMessageDialog( this, "Numero de identificacion invalido.", "Error de identificacion", JOptionPane.ERROR_MESSAGE );
			}
		}
		if(event.getActionCommand().equals("refill")){
			refillSuccess.setText("Estacion Llena.");
			try{
				int rta = logic.llenarEstacion();
				if(rta!=200){
					vcubs.add(denied, BorderLayout.CENTER);
					JOptionPane.showMessageDialog(this, "Error del servidor: "+rta+", intente mas tarded","Error",JOptionPane.ERROR_MESSAGE);
				}else{
					vcubs.add(refillSuccess, BorderLayout.NORTH);
					vcubs.add(success, BorderLayout.CENTER);
					try{TimeUnit.SECONDS.sleep(5);}catch(Exception e){System.out.println(e.getMessage());}
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia de usuario", JOptionPane.WARNING_MESSAGE);
			}
			vcubs.add(buts, BorderLayout.NORTH);
			vcubs.add(new JLabel(), BorderLayout.CENTER);
		}
	}
}
