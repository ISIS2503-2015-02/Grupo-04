package Logic;
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

import Persistencia.TranviaSerializable;


public class TranviaLogic{

	/**
	 * Constantes
	 */
	//EMERGENCIA
    /**
     * Constante que representa el estado ocupado
     */
    public final static int OCUPADO=0;

    /**
     * Constante que representa el estado disponible
     */
    public final static int DISPONIBLE=1;

    /**
     * Constante que representa el estado problema
     */
    public final static int PROBLEMA=2;

    /**
     * Constante que representa la linea 
     */
    public final static int LINEA_A=0;
    
    /**
     * Constante que representa la linea 
     */
    public final static int LINEA_B=1;
    
    /**
     * Constante que representa la linea 
     */
    public final static int LINEA_C=2;

    /**
	 * Atributos de datos.
	 */
	TranviaSerializable data;
	
	//CONSTRUCTOR DE LA LOGICA
	public TranviaLogic(){
		try{ObjectInputStream ins = new ObjectInputStream(new FileInputStream("./data/data")); 
			data = (TranviaSerializable)ins.readObject();
			ins.close();
		}catch(FileNotFoundException e){
			data = new TranviaSerializable();
			File file = new File("./data/data");
			try {
				file.createNewFile();
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}
			getTranvia();
			System.out.println(e.getMessage());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	//PIDE LA INFORMACION DE UN TRANVIA A LA CENTRAL PARA SIMULARLO
	public void getTranvia(){
		try{
			URL url = new URL("http://172.24.100.35:9000/estacionvcub");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode()!=200){
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output=buff.readLine();
			String at1[] = output.split(",");
			for (String string : at1) {
				String at2[] = string.split(":");
				if(at2[0].equals("[{\"id\""))
					data.setId(Long.parseLong(at2[1]));
				if(at2[0].equals("\"longitud\""))
					data.setLongitud(Long.parseLong(at2[1]));
				if(at2[0].equals("\"latitud\""))
					data.setLatitud(Long.parseLong(at2[1]));
				if(at2[0].equals("\"estado\""))
					data.setEstado(Integer.parseInt(at2[1]));
				if(at2[0].equals("\"Kilometraje\""))
					data.setKilometraje(Integer.parseInt(at2[1]));
				if(at2[0].equals("\"linea\""))
					data.setKilometraje(Integer.parseInt(at2[1].replace("}","")));
			}
			System.out.println(data.getId());
			System.out.println(data.getLongitud());
			System.out.println(data.getLatitud());
			System.out.println(data.getEstado());
			System.out.println(data.getKilometraje());
			System.out.println(data.getLinea());
			conn.disconnect();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//POSICION
	public void cambiarPosicion(Long lon, Long lat){
		data.setLongitud(lon);
		data.setLatitud(lat);
	}
	
	public void enviarPosicion(Long lon, Long lat)throws Exception{
		try{
			URL url = new URL("http://172.24.100.35:9000/tranvia/update/"+data.getId());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Accept", "application/json");
			String input = "{\"longitud\":"+lon+",\"latitud\":"+lat+"}";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			if(conn.getResponseCode()!=200)
				System.out.println("Error enviando posicion: "+conn.getResponseCode());
			conn.disconnect();
		}catch(Exception e){
			throw e;
		}
	}
	
	//EMERGENCIA
	public String reportarEmergencia(String des, int tipoAcc, String mag)throws Exception{
		try{
			URL url = new URL("http://172.24.100.35:9000/tranvia/"+data.getId()+"/reportarAccidente");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Accept", "application/json");

			String input = "{\"descripcion\":\""+des+"\",\"tipoAccidente\":\""+tipoAcc+"\",\"magnitud\":\""+mag+"\"}";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output=buff.readLine();
			conn.disconnect();
			return output;
		}catch(Exception e){
			throw e;
		}
	}
	
	//PERSISTENCIA
	public void persist(){
		try{
		FileOutputStream out = new FileOutputStream("./data/data");
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(data);
		os.flush(); 
		os.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
