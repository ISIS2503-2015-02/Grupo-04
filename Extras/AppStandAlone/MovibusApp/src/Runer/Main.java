package runer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.Logger;
import logica.MovibusLogic;
import logica.PedidoMovibusLogic;
import persistence.MovibusSerializable;
import persistence.PedidoMovibusSerializable;

public class Main  extends JFrame implements ActionListener{
	
	//---------------------------------------------------------------------------
	//   Constantes
	//---------------------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;

	//---------------------------------------------------------------------------
	//   Atributos
	//---------------------------------------------------------------------------
	
	private static MovibusLogic logic;
	private PedidoMovibusLogic logic2;
	private JPanel everything;
	private JPanel buts;
	private JButton but1;
	private JButton but2;
	private JPanel moviIn;
	private JPanel tempR;
	private JLabel lab;
	private JLabel tem;
	private JTextField input;
	private JTextField input2;
	private JButton check;
	private JButton check2;
	private JPanel termP;
	private JPanel info;
	private JPanel info2;
	private JLabel success;
	private JLabel idPed;
	private JLabel usNom;
	private JLabel conNom;
	private JLabel idBus;
	private JLabel cordDes;
	private JLabel cordct;
	private JLabel kilom;
	private JLabel idPed2;
	private JLabel usNom2;
	private JLabel conNom2;
	private JLabel idBus2;
	private JLabel cordDes2;
	private JLabel cordct2;
	private JLabel kilom2;
	private JButton refill;


	//---------------------------------------------------------------------------
	//   MAIN
	//---------------------------------------------------------------------------
	
	public Main(){
		logic = new MovibusLogic();
		logic.getPLlave();
		JOptionPane.showMessageDialog( this, logic.serverPublicKey , "Llave Publica del server", JOptionPane.INFORMATION_MESSAGE );
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
		but2 = new JButton("Dar informaci�n movibus");
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
		logic = new MovibusLogic();
		EnviPos envi = new EnviPos(logic);
		envi.start();	
	}


	//---------------------------------------------------------------------------
	//   Metodos
	//---------------------------------------------------------------------------
	
	@Override
	public void dispose(){
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
		if("revisar".equals(event.getActionCommand())){
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
					Logger.info(e);
					success.setText("Operacion Fallida");
					termP.add(success, BorderLayout.CENTER);
				}
				termP.setVisible(true);
			}else{
				JOptionPane.showMessageDialog( this, "No se ha iniciado sesion con un movibus.", "Error de inicio de sesion.", JOptionPane.ERROR_MESSAGE );
			}
		}
		if("darinfo".equals(event.getActionCommand()))
		{
			if(logic.getId()!=null){
				try {
					MovibusSerializable movibusss = logic.getMovi();
					idPed2= new JLabel("No se ha revisado por pedido a�n");
					usNom2= new JLabel("No se ha revisado por pedido a�n");
					conNom2= new JLabel("No se ha revisado por pedido a�n");
					idBus2= new JLabel(""+movibusss.getId());
					cordDes2= new JLabel("No se ha revisado por pedido a�n");
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
					Logger.info(e);
					success.setText("Operacion Fallida");
					termP.add(success, BorderLayout.EAST);
				}
				this.termP.setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog( this, "No se ha iniciado sesion con un movibus.", "Error de inicio de sesion.", JOptionPane.ERROR_MESSAGE );
			}
		}

		if("terminar".equals(event.getActionCommand())){
			if(logic2.getId()!=null&&!"".equals(input.getText())&&isNumeric(input2.getText())){
				try{
					String tempoReal = input2.getText();
					int tiempo = Integer.parseInt(tempoReal);
					logic2.reportarTerminacion(tiempo);
					success.setText("Operacion Exitosa");
					termP.add(success, BorderLayout.EAST);
					JOptionPane.showMessageDialog( this, "No se ha iniciado sesion con un movibus.", "Error de inicio de sesion.", JOptionPane.ERROR_MESSAGE );
				}catch(Exception e){
					Logger.info(e);
					success.setText("Operacion Fallida");
					termP.add(success, BorderLayout.EAST);
				}
				termP.setVisible(true);
			}else{
				JOptionPane.showMessageDialog( this, "Numero de identificacion invalido.", "Error de identificacion", JOptionPane.ERROR_MESSAGE );
			}
		}
		if("verificar".equals(event.getActionCommand())){
			if(!"".equals(input.getText())&&isNumeric(input.getText())){
				try{
					String idMovibusT = input.getText();
					long idMovibusC = Long.parseLong(idMovibusT);
					logic.getMovibus(idMovibusC);
					success.setText("Movibus: " + input.getText());
					termP.add(success, BorderLayout.EAST);
				}catch(Exception e){
					Logger.info(e);
					success.setText("Operacion Fallida");
					termP.add(success, BorderLayout.EAST);
				}
				termP.setVisible(true);
			}else{
				JOptionPane.showMessageDialog( this, "Numero de identificacion invalido.", "Error de identificacion", JOptionPane.ERROR_MESSAGE );
			}
		}
		this.repaint();

	}


final static class EnviPos extends Thread{
	private static MovibusLogic logic3;
	public EnviPos(MovibusLogic logics){
		this.logic3  = logics;
	}
	
	@Override
	public void run(){
		Date d = new Date();
		while(true){
			Date c = new Date();
			if((c.getTime()-d.getTime())>5000){
				d=c;
				try {
					logic3.reportePosicion();
					Logger.info2("Envio Posicion");
				} catch (Exception e) {
					Logger.info(e);
				}
			}
		}
	}
}
}

