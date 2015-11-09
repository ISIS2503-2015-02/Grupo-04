package logic;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.management.monitor.MonitorSettingException;

import persistencia.TranviaSerializable;


public class TranviaLogic{

	/**
	 * Constantes
	 */
	//EMERGENCIA
    /**
     * Constante que representa el estado ocupado
     */
	public static final int OCUPADO=0;

    /**
     * Constante que representa el estado disponible
     */
	public static final int DISPONIBLE=1;

    /**
     * Constante que representa el estado problema
     */
	public static final int PROBLEMA=2;

    /**
     * Constante que representa la linea 
     */
	public static final int LINEA_A=0;
    
    /**
     * Constante que representa la linea 
     */
	public static final int LINEA_B=1;
    
    /**
     * Constante que representa la linea 
     */
	public static final int LINEA_C=2;

    /**
     * Constante que representa la ruta del archivo
     */
    public static final String RUTA_ARCHIVO="./data/data";
    
    /**
     * Cosntante que representa la url del servidor central de TBC.
     */
    public static final String URL="http://172.24.100.35:9000/tranvia";

    /**
	 * Atributos de datos.
	 */
	TranviaSerializable data;
	
	/**
	 * Constructor de la logica de tranvia.
	 * Busca un archivo de datos del tranvia, si no lo encuentra crea un archivo y pide informacion al servidor central
	 */
	public TranviaLogic(){
		try{
			//Busca informacion en un archivo de datos y la carga en la aplicacion
			ObjectInputStream ins = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO));
			data = (TranviaSerializable)ins.readObject();
			ins.close();
		}catch(FileNotFoundException e){
			//Si no encuentra el archivo crea uno nuevo y lo llena con datos pedidos al servidor central.
			Logger.info(e);
			data = new TranviaSerializable();
			File file = new File(RUTA_ARCHIVO);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				Logger.info(e1);
			}
			//Pide los datos al servidor central
			getTranvia();
		}catch(Exception e){
			Logger.info(e);
		}
	}
	
	/**
	 * Pide los datos del tranvia al servidor central de TBC
	 */
	public void getTranvia(){
		try{
			//URL del servidor central
			URL url = new URL(URL);
			//Configura la conexion al servidor
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//Realiza el request
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode()!=200){
				//En caso de error arroja una excepcion
				throw new TranviaException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			//Recoge los datos de respeusta del servidor y los guarda en el archivo de datos.
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output=buff.readLine();
			String[] at1 = output.split(",");
			for (String string : at1) {
				String[] at2 = string.split(":");
				if("[{\"id\"".equals(at2[0]))
					data.setId(Long.parseLong(at2[1]));
				if("\"longitud\"".equals(at2[0]))
					data.setLongitud(Double.parseDouble(at2[1]));
				if("\"latitud\"".equals(at2[0]))
					data.setLatitud(Double.parseDouble(at2[1]));
				if("\"estado\"".equals(at2[0]))
					data.setEstado(Integer.parseInt(at2[1]));
				if("\"Kilometraje\"".equals(at2[0]))
					data.setKilometraje(Integer.parseInt(at2[1]));
				if("\"linea\"".equals(at2[0]))
					data.setKilometraje(Integer.parseInt(at2[1].replace("}","")));
			}
			conn.disconnect();
		}catch(Exception e){
			//En caso de error escribe la excepcion en el logger de la aplicacion.
			Logger.info(e);
		}
	}

	/**
	 * Cambia la posicion del tranvia.
	 * @param lonDouble Longitud actual del tranvia.
	 * @param lat Double Latitud actual del Tranvia
	 */
	public void cambiarPosicion(Double lon, Double lat){
		data.setLongitud(lon);
		data.setLatitud(lat);
	}
	
	/**
	 * Envia la posicion al servidor central de TBC
	 * @throws TranviaException
	 */
	public void enviarPosicion()throws TranviaException{
		try{
			//Prepara la conexion y arma el URL
			URL url = new URL(URL+data.getId());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Accept", "application/json");
			String input = "{\"longitud\":"+data.getLongitud()+",\"latitud\":"+data.getLatitud()+"}";
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			//En caso de error arroja una excepcion
			if(conn.getResponseCode()!=200)
				throw new TranviaException("Error enviando posicion: "+conn.getResponseCode()+conn.getContentType()+conn.getResponseMessage());
			conn.disconnect();
			System.out.println("Working");
		}catch(Exception e){
			//Arroja un excepcion en caso de error de conexion con el servidor.
			throw new TranviaException(e.getMessage());
		}
	}
	
	/**
	 * Reporta una emergencia en el tranvia al servidor central.
	 */
	public String reportarEmergencia(String des, int tipoAcc, String mag)throws TranviaException{
		try{
			//Arma la conexion y el request.
			URL url = new URL(URL+data.getId()+"/reportarAccidente");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = "{\"descripcion\":\""+des+"\",\"tipoAccidente\":"+tipoAcc+",\"magnitud\":\""+mag+"\"}";
			System.out.println(input.getBytes());

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			//En caso de error arroja una excepcion.
			if(conn.getResponseCode()!=200)
				throw new TranviaException("Error enviando posicion: "+conn.getResponseCode()+" "+conn.getContentType()+" "+conn.getResponseMessage());
			
			conn.disconnect();
			return "";
		}catch(Exception e){
			//En caso de un error de conexion arroja una excepcion.
			throw new TranviaException(e.getMessage());
		}
	}
	
	/**
	 * Manejador de persistencia de los datos del tranvia localmente.
	 * Escribe los datos del tranvia en un archivo local para rapido acceso.
	 */
	public void persist(){
		try{
		FileOutputStream out = new FileOutputStream(RUTA_ARCHIVO);
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(data);
		os.flush(); 
		os.close();
		}catch(Exception e){
			Logger.info(e);
		}
	}
}
