package runner;

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

import logic.EstacionVcubLogic;
import logic.Logger;

/**
 * Clase principal ejecutable e interfaz de la aplicacion estacion vcubs
 * @author s.baquero10
 *
 */
public class Main extends JFrame implements ActionListener{

	/**
	 * Atributo serializable de la clase.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Objeto que representa la logica y conexion con el servidor de la aplicacion
	 */
	private EstacionVcubLogic logic;
	/**
	 * Panel principal de la aplicacion
	 */
	private JPanel everything;
	/**
	 * Panel de botones de reserva y devolucion de vcubs.
	 */
	private JPanel buts;
	/**
	 * Boton de reserva de vcubs
	 */
	private JButton but1;
	/**
	 * Boton de devolucion de vcubs
	 */
	private JButton but2;
	/**
	 * Panel que contiene los elementos de registro de datos.
	 */
	private JPanel userIn;
	/**
	 * Texto de guia Usuario :
	 */
	private JLabel lab;
	/**
	 * Input de documento de identidad del usuario
	 */
	private JTextField input;
	/**
	 * Boton de verificacion de datos
	 */
	private JButton check;
	/**
	 * Panel donde se muestran los resultados de las operaciones
	 */
	private JPanel vcubs;
	/**
	 * Texto de exito en una operacion
	 */
	private JLabel success;
	/**
	 * Texto de cantidad de vcubs segun transaccion
	 */
	private JLabel numVc;
	/**
	 * Boton de confirmacion de llenado de la estacion
	 */
	private JButton refill;

	/**
	 * Constructor de la interfaz
	 */
	public Main(){
		//LOGICA
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
		success = new JLabel("Operacion Exitosa");
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

	/**
	 * Ejecucion de la aplicacion con argumentos vacios
	 */
	public static void main(String[] args){
		new Main();
	}

	@Override
	/**
	 * Metodo de persistencia de la logica.
	 */
	public void dispose(){
		logic.persist();
		super.dispose();
	}

	/**
	 * Manejo de eventos de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		//Evento de retiro de Vcub por parte de un usuario.
		if("retirar".equals(event.getActionCommand())){
			if(!"".equals(input.getText())){
				String[] rta = null;
				//Realiza la transaccion con el servidor
				try {
					//EXITO: se muestran la cantidad de vcubs del usuario y un mensaje de exito
					rta = logic.retiroVcub(Long.parseLong(input.getText())).split(":");
					success.setText("Operacion Exitosa");
					vcubs.add(success, BorderLayout.CENTER);
					numVc.setText("Numero de Vcubs rentadas : "+rta[0]);
				} catch (Exception e) {
					//FALLO: se muestra un mensaje de error en la interfaz y se registra en el log de la aplicacion
					Logger.info(e);
					success.setText("Operacion Fallida");
					vcubs.add(success, BorderLayout.CENTER);
					numVc.setText("Error con el numero de identificacion");
				}
				vcubs.setVisible(true);
			}else{
				//Se muestra un mensaje de error si no se inserto ningun numero de documento
				JOptionPane.showMessageDialog( this, "Numero de identificacion invalido.", "Error de identificacion", JOptionPane.ERROR_MESSAGE );
			}
		}
		//Evento de devolucion de vcubs
		if("devolver".equals(event.getActionCommand())){
			if(!"".equals(input.getText())){
				String[] rta=null;
				//Intenta realizar la transaccion con el servidor
				try{
					//EXITO: muestra la cantidad de vcubs del usuario y un mensaje de exito
					rta = logic.devolucionVcub(Long.parseLong(input.getText())).split(":");
					success.setText("Operacion Exitosa");
					vcubs.add(success, BorderLayout.CENTER);
					numVc.setText("Numero de Vcubs rentadas : "+rta[0]);
				}catch(Exception e){
					//En caso de fallo escribe la excepcion en el log y muestra un mensaje de error
					Logger.info(e);
					success.setText("Operacion Fallida");
					vcubs.add(success, BorderLayout.CENTER);
					numVc.setText(e.getMessage());
				}
				vcubs.setVisible(true);
			}else{
				//Muestra un mensaje de error si no se inserto un numero de identificacion valido
				JOptionPane.showMessageDialog( this, "Numero de identificacion invalido.", "Error de identificacion", JOptionPane.ERROR_MESSAGE );
			}
		}
		//Evento de verificacion de datos,
		if("verificar".equals(event.getActionCommand())){
			//VERIFICACION DE USUARIOS
			if(!"".equals(input.getText()) && !"admin".equals(input.getText())){
				String[] rta=null;
				//Intenta realizar la transaccion con el servidor
				try{
					//EXITO: muestra la informacion del usuario.
					rta = logic.verificarUsuario(Long.parseLong(input.getText())).split(":");
					success.setText("Usuario: " + rta[0].replace("\"", ""));
					vcubs.add(success, BorderLayout.CENTER);
					numVc.setText("Numero de Vcubs rentadas : "+rta[1]);
				}catch(Exception e){
					//Escribe en el log el error y muestra un mensaje.
					Logger.info(e);
					success.setText("Operacion Fallida");
					vcubs.add(success, BorderLayout.CENTER);
					numVc.setText("Error con el numero de identificacion.");
				}
				vcubs.setVisible(true);
			}
			//EN CASO DE SER EL ADMINISTRADOR DE LA ESTACION
			else if("admin".equals(input.getText())){
				//Reconstruye la interfaz con las opciones del usuario.
				vcubs.remove(success);
				vcubs.add(refill, BorderLayout.CENTER);
				numVc.setText("");
				vcubs.setVisible(true);
				refill.setVisible(true);
			}else{
				//Muestra un mensaje de error con la identificacion
				JOptionPane.showMessageDialog( this, "Numero de identificacion invalido.", "Error de identificacion", JOptionPane.ERROR_MESSAGE );
			}
		}
		//Evento de llenado de vcubs en la estacion
		if("refill".equals(event.getActionCommand())){
			try{
				//Realiza la transaccion con el servidor
				int rta = logic.llenarEstacion();
				vcubs.remove(refill);
				success.setText("Estacion llenada a capacidad maxima de : "+rta);
				vcubs.add(success, BorderLayout.CENTER);
			}catch(Exception e){
				//En caso de error imprime la excepcion en el log y muestra un mensaje de error.
				Logger.info(e);
				vcubs.remove(refill);
				success.setText("Error en la operacion.\nPor favor comuniquese con el dueño del servidor.");
				vcubs.add(success, BorderLayout.CENTER);
			}
			vcubs.setVisible(true);
		}
		//Al final de cualquier evento refresca la interfaz
		this.repaint();
	}
}
