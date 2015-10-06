package Runer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParsePosition;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logica.MovibusLogic;
import Logica.PedidoMovibusLogic;
import Persistence.MovibusSerializable;
import Persistence.PedidoMovibusSerializable;

public class Main  extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	MovibusLogic logic;
	PedidoMovibusLogic logic2;
	JPanel everything;
	JPanel buts;
	JButton but1;
	JButton but2;
	JPanel moviIn;
	JPanel tempR;
	JLabel lab;
	JLabel tem;
	JTextField input;
	JTextField input2;
	JButton check;
	JButton check2;
	JPanel termP;
	JPanel info;
	JPanel info2;
	JLabel success;
	JLabel idPed;
	JLabel usNom;
	JLabel conNom;
	JLabel idBus;
	JLabel cordDes;
	JLabel cordct;
	JLabel kilom;
	JLabel idPed2;
	JLabel usNom2;
	JLabel conNom2;
	JLabel idBus2;
	JLabel cordDes2;
	JLabel cordct2;
	JLabel kilom2;
	JButton refill;

	public Main(){
		logic = new MovibusLogic();
		logic2 = new PedidoMovibusLogic();
		this.setSize(500, 700);
		this.setResizable(false);
		this.setDefaultCloseOperation( EXIT_ON_CLOSE );
		everything = new JPanel();
		everything.setLayout(new BorderLayout());
		//BOTONES
		buts = new JPanel();
		buts.setLayout(new GridLayout());
		but1 = new JButton("Revisar pedido");
		but1.setActionCommand("revisar");
		but1.addActionListener(this);
		but2 = new JButton("Dar información movibus");
		but2.setActionCommand("darinfo");
		but2.addActionListener(this);
		buts.add(but1);
		buts.add(but2);
		//MOVIBUS INPUT
		moviIn = new JPanel();
		moviIn.setLayout(new GridLayout());
		lab = new JLabel(" Id Movibus:");
		input = new JTextField();
		check = new JButton("Verificar");
		check.addActionListener(this);
		check.setActionCommand("verificar");
		moviIn.add(lab);
		moviIn.add(input);
		moviIn.add(check);
		//INFO PEDIDO Y TERMIANCION PEDIDO
		termP = new JPanel();
		termP.setLayout(new BorderLayout());
		success = new JLabel("Operacion Exitosa");
		tempR = new JPanel();
		tempR.setLayout(new GridLayout());
		tem = new JLabel(" Id Movibus:");
		input2 = new JTextField();
		check2 = new JButton("Terminar pedido");
		check2.addActionListener(this);
		check2.setActionCommand("terminar");
		idPed= new JLabel(" Id Pedido:");
		usNom= new JLabel(" Nombre usuario:");
		conNom= new JLabel(" Nombre Conductor:");
		idBus= new JLabel(" Id Movibus:");
		cordDes= new JLabel(" Destino:");
		cordct= new JLabel(" Posicion inicio:");
		kilom= new JLabel(" Kilometraje inicial:");
		info= new JPanel();
		info.add(idPed);
		info.add(usNom);
		info.add(conNom);
		info.add(idBus);
		info.add(cordDes);
		info.add(cordct);
		info.add(kilom);
		tempR.add(tem);
		tempR.add(input2);
		tempR.add(check2);
		termP.add(info, BorderLayout.EAST);
		termP.add(tempR, BorderLayout.SOUTH);
		termP.setVisible(false);
		//EVERYTHING
		everything.add(moviIn, BorderLayout.NORTH);
		everything.add(buts, BorderLayout.SOUTH);
		everything.add(termP, BorderLayout.CENTER);
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

	public static boolean isNumeric(String str)
	{
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals("revisar")){
			if(logic.getId()!=null){
				try {
					logic2.getPedidoMovibus(logic.getId());
					if(logic2.getId()!=null){
						PedidoMovibusSerializable ped = logic2.getPedido();
						idPed2= new JLabel(""+ped.getId());
						usNom2= new JLabel(ped.getUsuario().getNombre());
						conNom2= new JLabel(ped.getConductor().getNombre());
						idBus2= new JLabel(""+ped.getMovibus().getId());
						cordDes2= new JLabel(ped.getDireccionDestinoLA() + ","+ped.getDireccionDestinoLO());
						cordct2= new JLabel(ped.getMovibus().getPosicionLat()+ ","+ped.getMovibus().getPosicionLong());
						kilom2= new JLabel(""+ped.getMovibus().getKilometraje());
						info2= new JPanel();
						info2.add(idPed2);
						info2.add(usNom2);
						info2.add(conNom2);
						info2.add(idBus2);
						info2.add(cordDes2);
						info2.add(cordct2);
						info2.add(kilom2);
						termP.add(info2, BorderLayout.EAST);
						termP.setVisible(true);
					}
					else
					{
						idPed2= new JLabel("El movibus no tiene un pedido activo en el momento");
						info2= new JPanel();
						info2.add(idPed2);
						termP.add(info2, BorderLayout.EAST);
						termP.setVisible(true);
					}
				} catch (Exception e) {
					success.setText("Operacion Fallida");
					termP.add(success, BorderLayout.CENTER);
					//					System.out.println("Error con el numero de identificacion");
				}
				termP.setVisible(true);
			}else{
				JOptionPane.showMessageDialog( this, "No se ha iniciado sesion con un movibus.", "Error de inicio de sesion.", JOptionPane.ERROR_MESSAGE );
			}
		}
		if(event.getActionCommand().equals("darinfo"))
		{
			if(logic.getId()!=null){
				try {
					MovibusSerializable movibusss = logic.getMovi();
					idPed2= new JLabel("No se ha revisado por pedido aún");
					usNom2= new JLabel("No se ha revisado por pedido aún");
					conNom2= new JLabel("No se ha revisado por pedido aún");
					idBus2= new JLabel(""+movibusss.getId());
					cordDes2= new JLabel("No se ha revisado por pedido aún");
					cordct2= new JLabel(movibusss.getPosicionLat()+ ","+movibusss.getPosicionLong());
					kilom2= new JLabel(""+movibusss.getKilometraje());
					info2= new JPanel();
					info2.add(idPed2);
					info2.add(usNom2);
					info2.add(conNom2);
					info2.add(idBus2);
					info2.add(cordDes2);
					info2.add(cordct2);
					info2.add(kilom2);
					termP.add(info2, BorderLayout.EAST);
					termP.setVisible(true);
				}
				catch (Exception e) {
					success.setText("Operacion Fallida");
					termP.add(success, BorderLayout.EAST);
					//					System.out.println("Error con el numero de identificacion");
				}
				this.termP.setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog( this, "No se ha iniciado sesion con un movibus.", "Error de inicio de sesion.", JOptionPane.ERROR_MESSAGE );
			}
		}

		if(event.getActionCommand().equals("terminar")){
			if(logic2.getId()!=null&&!input.getText().equals("")&&isNumeric(input2.getText())){
				try{
					String tempoReal = input2.getText();
					int tiempo = Integer.parseInt(tempoReal);
					logic2.reportarTerminacion(tiempo);
					success.setText("Operacion Exitosa");
					termP.add(success, BorderLayout.EAST);
					JOptionPane.showMessageDialog( this, "No se ha iniciado sesion con un movibus.", "Error de inicio de sesion.", JOptionPane.ERROR_MESSAGE );
					//				numVc.setText("Numero de Vcubs rentadas : "+rta[0]);
				}catch(Exception e){
					success.setText("Operacion Fallida");
					termP.add(success, BorderLayout.EAST);
					//					numVc.setText("Error con el numero de identificacion.");
				}
				termP.setVisible(true);
			}else{
				JOptionPane.showMessageDialog( this, "Numero de identificacion invalido.", "Error de identificacion", JOptionPane.ERROR_MESSAGE );
			}
		}
		if(event.getActionCommand().equals("verificar")){
			if(!input.getText().equals("")&&isNumeric(input.getText())){
				try{
					String idMovibusT = input.getText();
					int idMovibusC = Integer.parseInt(idMovibusT);
					long idM = new Long(idMovibusC);
					logic.getMovibus(idM);
					success.setText("Movibus: " + input.getText());
					termP.add(success, BorderLayout.EAST);
					//					numVc.setText("Numero de Vcubs rentadas : "+rta[1]);
				}catch(Exception e){
					success.setText("Operacion Fallida");
					termP.add(success, BorderLayout.EAST);
					//					numVc.setText("Error con el numero de identificacion.");
				}
				termP.setVisible(true);
			}else{
				JOptionPane.showMessageDialog( this, "Numero de identificacion invalido.", "Error de identificacion", JOptionPane.ERROR_MESSAGE );
			}
		}
		this.repaint();

	}

}

