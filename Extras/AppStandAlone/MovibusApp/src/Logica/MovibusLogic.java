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

	//-----------------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------------
	
	/**
	 * Constante que nos da la URL base para realizar pedidos de servicios al servidor concernietes a movibus
	 */
	public static final String URL="http://172.24.100.49:9000/movibus/";
	/**
	 * Constante que nos da la URL base de seguridad para pedir la llave al servidor
	 */
	public static final String URL_SEGURIDAD="http://172.24.100.49:9000/seguridad/";
	
	//-----------------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------------
	
	/**
	 * La logica del movibus que es la clase que lo describe y sus operaciones basicas
	 */
	private MovibusSerializable data;
	/**
	 * Atributo para poder saber cual es la llave que se le asigno a la aplicacion para concetarse con el server
	 */
	public PublicKey serverPublicKey;

	//-----------------------------------------------------------------------
	//Constructor
	//-----------------------------------------------------------------------
	
	/**
	 * Constructor vacio del server, el cual deja la clase con la base del movibus creada, pero sin datos
	 */
	public MovibusLogic(){
		data = new MovibusSerializable();
	}

	//-----------------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------------
	
	/**
	 * Metodo que nos da la id del movibus que esta usando la aplicacion si es que ya pidio que se iniciara el servicio.
	 * @return un Long que es el id del movibus actual si es que existe, puede dar null en caso de no estar inicializado el atributo data con datos
	 */
	public Long getId(){
		return data.getId();
	}
	
	/**
	 * Metodo que trae la informacion de un movibus de la base de datos basado en el id del mismo
	 * @param idM id del movibus del cual se quiere la informacion
	 */
	public void getMovibus(Long idM){
		try{
			//Realiza connexion con el servidor
			
			URL url = new URL(URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			//Envio del id encryptado del movibus del cual se quieren los datos
			
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			JSONObject p = new JSONObject();
			String ciphertext = StringEnvelope.wrap(idM.toString(), "key");               // Uso de la libreria StringEnvelop para el encryptado de los datos
			p.put("id", ciphertext);

			wr.write(p.toString());
			wr.flush();
			
			//Revision si la respuesta del servidor es exitosa
			
			if(conn.getResponseCode()!=200){
				throw new MovibusException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			//Recibe los datos del servidor y crea la data del movibus en la aplicaion al guardarlo en el atributo data
			
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
			conn.disconnect(); // Se desconecta del servidor
		}catch(Exception e){
			Logger.info(e);
		}
	}
	
	/**
	 * Metodo que reporta la posicion actual del movibus actual al servidor
	 * @throws MovibusException En caso de haber un problema en la conexio con el servidor
	 */
	public void reportePosicion() throws MovibusException{
		try{
			
			data = new MovibusSerializable();
			data.setId(new Long(1));
			data.setKilometraje(123);
			data.setPosicionLat(12123.0);
			data.setPosicionLong(123543.0);
			
			//Se conecta con el servidor para pedir uno de sus servicios
			
			URL url = new URL(URL+"posicion");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestMethod("POST");

			//Comienza el proceso de enciptado de informacion a enviar al servidor mediante objetos JSON y por medio de la libreria StringEnvelop
			
			JSONObject movRepPos   = new JSONObject();

			movRepPos.put("id", data.getId());
			movRepPos.put("longitud",data.getPosicionLong());
			movRepPos.put("latitud", data.getPosicionLat());
			
			String cipherText = StringEnvelope.wrap(movRepPos.toString(), "aa09cee77e1d606d5ab06500ac95729c");          //Encypta la informacion
			
			JSONObject movRepPos2   = new JSONObject();
			
			movRepPos2.put("envelop", cipherText);                   //Guarda la informacion encyptada en un JSONObject

			//Envia el JSONObject con la informacion encyptada
			
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(movRepPos.toString());
			wr.flush();
			conn.disconnect();
		}catch(Exception e){
			throw new MovibusException(e.getMessage());
		}
	}
	
	/**
	 * Metodo por el cual le pide una llave de seguridad unica al servidor la cual luego sera usada para la encyptacion de la informacion en el StringEnvelop
	 */
	public void getPLlave(){

		try{
		//Se conecta con el servidor
		
		URL url1 = new URL(URL_SEGURIDAD);
		HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
		conn1.setRequestProperty("Content-Type", "application/json");
		conn1.setRequestProperty("Accept", "application/json");
		conn1.setRequestMethod("GET");
		
		//Revision si la respuesta del serviodr es exitosa
		
		if(conn1.getResponseCode()!=200){
			throw new MovibusException("Failed : HTTP error code : " + conn1.getResponseCode());
		}
		//Envio de la informacion
		
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

	/**
	 * Metodo que nos dice si la cadena de caracteres dada por parametro es un numero 
	 * @param str la cadena de caracteres a evaluar
	 * @return true si la cadena de carateres es un numero y falso en caso contrario
	 */
	public static boolean isNumeric(String str){
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(str, pos);
	  return str.length() == pos.getIndex();
	}
	
	/**
	 * Da el movibus que se tiene serialisado actualmente 
	 * @return data movibus serializado actualmente
	 */
	public MovibusSerializable getMovi() {
		return data;
	}
}
