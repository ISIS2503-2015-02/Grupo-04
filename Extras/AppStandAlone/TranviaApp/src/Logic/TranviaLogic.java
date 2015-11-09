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
    
    
    public static final String URL="http://172.24.100.35:9000/tranvia";

    /**
	 * Atributos de datos.
	 */
	TranviaSerializable data;
	
	//CONSTRUCTOR DE LA LOGICA
	public TranviaLogic(){
		try{
			ObjectInputStream ins = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO));
			data = (TranviaSerializable)ins.readObject();
			ins.close();
		}catch(FileNotFoundException e){
			Logger.info(e);
			data = new TranviaSerializable();
			File file = new File(RUTA_ARCHIVO);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				Logger.info(e1);
			}
			getTranvia();
		}catch(Exception e){
			Logger.info(e);
		}
	}
	
	//PIDE LA INFORMACION DE UN TRANVIA A LA CENTRAL PARA SIMULARLO
	public void getTranvia(){
		try{
			URL url = new URL(URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode()!=200){
				throw new TranviaException("Failed : HTTP error code : " + conn.getResponseCode());
			}
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
			Logger.info(e);
		}
	}

	//POSICION
	public void cambiarPosicion(Double lon, Double lat){
		data.setLongitud(lon);
		data.setLatitud(lat);
	}
	
	public void enviarPosicion()throws TranviaException{
		try{
			URL url = new URL(URL+data.getId());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Accept", "application/json");
			String input = "{\"longitud\":"+data.getLongitud()+",\"latitud\":"+data.getLatitud()+"}";
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			if(conn.getResponseCode()!=200)
				throw new TranviaException("Error enviando posicion: "+conn.getResponseCode()+conn.getContentType()+conn.getResponseMessage());
			conn.disconnect();
			System.out.println("Working");
		}catch(Exception e){
			throw new TranviaException(e.getMessage());
		}
	}
	
	//EMERGENCIA
	public String reportarEmergencia(String des, int tipoAcc, String mag)throws TranviaException{
		try{
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
			if(conn.getResponseCode()!=200)
				throw new TranviaException("Error enviando posicion: "+conn.getResponseCode()+" "+conn.getContentType()+" "+conn.getResponseMessage());
			
			conn.disconnect();
			return "";
		}catch(Exception e){
			throw new TranviaException(e.getMessage());
		}
	}
	
	//PERSISTENCIA
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
