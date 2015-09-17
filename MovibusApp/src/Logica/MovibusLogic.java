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
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import Persistence.MovibusSerializable;


public class MovibusLogic {

	MovibusSerializable data;
	
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
			URL url = new URL("http://172.24.100.49:9000/movibus/"+data.getId());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestMethod("POST");

			JSONObject movRepPos   = new JSONObject();

			movRepPos.put("longitud",posLon);
			movRepPos.put("latitud", posLat);

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
