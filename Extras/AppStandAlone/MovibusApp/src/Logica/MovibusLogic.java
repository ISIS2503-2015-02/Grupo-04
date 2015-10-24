package Logica;

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
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.KeyGenerator;

import org.json.JSONObject;
import org.owasp.StringEnvelope;

import Persistence.MovibusSerializable;
import Security.Security;


public class MovibusLogic {

	MovibusSerializable data;
	public PublicKey serverPublicKey;
	
	public MovibusLogic()
	{
		try{
			ObjectInputStream ins = new ObjectInputStream(new FileInputStream("./data/data")); 
			data = (MovibusSerializable)ins.readObject();
			ins.close();
		}catch(FileNotFoundException e){
			data = new MovibusSerializable();
			File file = new File("./data/data");
			try {
				file.createNewFile();
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}
			System.out.println(e.getMessage());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public Long getId(){
		return data.getId();
	}
	
	public void getMovibus(Long idM){
		try{
			URL url = new URL("http://172.24.100.49:9000/movibus/"+idM);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode()!=200){
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
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
			
			System.out.println(data.getId());
			System.out.println(data.getEstad());
			System.out.println(data.getPosicionLat());
			System.out.println(data.getPosicionLong());
			System.out.println(data.getKilometraje());
			conn.disconnect();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String reportePosicion(Double posLat, Double posLon) throws Exception{
		String rta="";
		try{
			
			URL url = new URL("http://172.24.100.49:9000/movibus/posicion");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestMethod("POST");

			JSONObject movRepPos   = new JSONObject();

			movRepPos.put("id", data.getId());
			movRepPos.put("longitud",posLon);
			movRepPos.put("latitud", posLat);
			
			StringEnvelope env = new StringEnvelope();
			String cipherText = env.wrap(movRepPos.toString(), "aa09cee77e1d606d5ab06500ac95729c");
			JSONObject movRepPos2   = new JSONObject();
			
			movRepPos2.put("envelop", movRepPos);

			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(movRepPos.toString());
			wr.flush();
			conn.disconnect();
			return rta;
		}catch(Exception e){
			throw e;
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
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void getPLlave() 
	{

		try{
		URL url1 = new URL("http://172.24.100.35:9000/seguridad");
		HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
		conn1.setRequestProperty("Content-Type", "application/json");
		conn1.setRequestProperty("Accept", "application/json");
		conn1.setRequestMethod("GET");
		

		if(conn1.getResponseCode()!=200){
			throw new RuntimeException("Failed : HTTP error code : " + conn1.getResponseCode());
		}
		BufferedReader buff = new BufferedReader(new InputStreamReader(conn1.getInputStream()));
		String output=buff.readLine();
		JSONObject j = new JSONObject(output);
		//Pedido de llave
		byte[] llave = (byte[])j.get("pk");
		
		KeyFactory kf = KeyFactory.getInstance("RSA");
		serverPublicKey = kf.generatePublic(new X509EncodedKeySpec(llave));
		
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
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
		// TODO Auto-generated method stub
		return data;
	}
}
