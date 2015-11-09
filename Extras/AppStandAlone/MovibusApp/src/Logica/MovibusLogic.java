package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.NumberFormat;
import java.text.ParsePosition;

import logica.Logger;

import org.json.JSONObject;
import org.owasp.StringEnvelope;

import persistence.MovibusSerializable;


public class MovibusLogic {

	public static final String URL="http://172.24.100.49:9000/movibus/";
	public static final String URL_SEGURIDAD="http://172.24.100.49:9000/seguridad/";
	private MovibusSerializable data;
	private PublicKey serverPublicKey;
	
	
	public MovibusLogic()
	{
		try{
			ObjectInputStream ins = new ObjectInputStream(new FileInputStream("./data/data")); 
			data = (MovibusSerializable)ins.readObject();
			ins.close();
		}catch(FileNotFoundException e){
			Logger.info(e);
			data = new MovibusSerializable();
			File file = new File("./data/data");
			try {
				file.createNewFile();
			} catch (IOException e1) {
				Logger.info(e1);
			}
		}catch(Exception e){
			Logger.info(e);
		}
	}

	public Long getId(){
		return data.getId();
	}
	
	public void getMovibus(Long idM){
		try{
			URL url = new URL(URL+idM);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode()!=200){
				throw new MovibusException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output=buff.readLine();
			JSONObject j = new JSONObject(output);
			Long id = j.getLong("id");
				
			int estad = j.getInt("tiempoEstimado");
			int kilom = j.getInt("tiempoReal");
			Double latpos = j.getDouble("direccionDestinoLA");
			Double lonpos = j.getDouble("direccionDestinoLO");
			
			data.setId(id);
			data.setEstad(estad);
			data.setKilometraje(kilom);
			data.setPosicionLat(latpos);
			data.setPosicionLong(lonpos);
			conn.disconnect();
		}catch(Exception e){
			Logger.info(e);
		}
	}
	
	public String reportePosicion() throws MovibusException{
		String rta="";
		try{
			data = new MovibusSerializable();
			data.setId(new Long(1));
			data.setKilometraje(123);
			data.setPosicionLat(12123.0);
			data.setPosicionLong(123543.0);
			URL url = new URL(URL+"posicion");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestMethod("POST");

			JSONObject movRepPos   = new JSONObject();

			movRepPos.put("id", data.getId());
			movRepPos.put("longitud",data.getPosicionLong());
			movRepPos.put("latitud", data.getPosicionLat());
			
			String cipherText = StringEnvelope.wrap(movRepPos.toString(), "aa09cee77e1d606d5ab06500ac95729c");
			
			JSONObject movRepPos2   = new JSONObject();
			
			movRepPos2.put("envelop", cipherText);

			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(movRepPos.toString());
			wr.flush();
			conn.disconnect();
			return rta;
		}catch(Exception e){
			throw new MovibusException(e.getMessage());
		}
	}
	
	public void persist(){
		try{
		FileOutputStream out = new FileOutputStream("./data/data");
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(data);
		os.flush(); 
		os.close();
		}catch(Exception e){
			Logger.info(e);
		}
	}
	
	public void getPLlave() 
	{

		try{
		URL url1 = new URL(URL_SEGURIDAD);
		HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
		conn1.setRequestProperty("Content-Type", "application/json");
		conn1.setRequestProperty("Accept", "application/json");
		conn1.setRequestMethod("GET");
		

		if(conn1.getResponseCode()!=200){
			throw new MovibusException("Failed : HTTP error code : " + conn1.getResponseCode());
		}
		BufferedReader buff = new BufferedReader(new InputStreamReader(conn1.getInputStream()));
		String output=buff.readLine();
		JSONObject j = new JSONObject(output);
		//Pedido de llave
		byte[] llave = (byte[])j.get("pk");
		
		KeyFactory kf = KeyFactory.getInstance("RSA");
		serverPublicKey = kf.generatePublic(new X509EncodedKeySpec(llave));
		
		}catch(Exception e){
			Logger.info(e);
		}
		
	}

	public static boolean isNumeric(String str)
	{
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(str, pos);
	  return str.length() == pos.getIndex();
	}
	
	public MovibusSerializable getMovi() {
		return data;
	}
}
