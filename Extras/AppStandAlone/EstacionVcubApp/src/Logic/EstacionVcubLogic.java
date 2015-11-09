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
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import persistencia.EstacionVcubSerializable;

public class EstacionVcubLogic implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constante que representa la ruta del archivo
	 */
	public static final String RUTA_ARCHIVO="./data/data";
	
	/**
	 * Constante que define la propiedad general del request HTTP.
	 */
	public static final String ACCEPT="Accept";
	
	/**
	 * Cosntante para el tipo de datos transmitido en el request HTTP.
	 */
	public static final String HEADER="application/json";
	
	/**
	 * URL del servidor al que se van a hacer las peticiones.
	 */
	public static final String URL="http://172.24.100.35:9000/estacionvcub";

	/**
	 * Archivo localde guardado de datos de la estación.
	 */
	private EstacionVcubSerializable data;

	/**
	 * Constructor de la Logica de la aplicacion.
	 * Se busca un archivo local de datos si no se encuentra se crea uno a partir de un request al servidor central de TBC.
	 */
	public EstacionVcubLogic(){
        try{
        	//Busca un archivo local de datos, y carga los datos de la estacion en una variable.
			ObjectInputStream ins = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO)); 
			data = (EstacionVcubSerializable)ins.readObject();
			ins.close();
		}catch(FileNotFoundException e){
			//Si no encuentra un archivo local lo crea y pide datos de la estacion al servidor central
			data = new EstacionVcubSerializable();
			File file = new File(RUTA_ARCHIVO);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				Logger.info(e1);
			}
			getEstacion();
			Logger.info(e);
		}catch(Exception e){
			Logger.info(e);
		}
	}

	/**
	 * Da el nombre de la estacion.
	 * @return String el nombre de la estacion.
	 */
	public String getNombreEstacion(){
		return data.getNombre();
	}
	
	/**
	 * Pide datos de una estacion al servidor central de TBC
	 */
	public void getEstacion(){
		try{
			//URL del servidor central
			URL url = new URL(URL);
			//Conexion con el servidor
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			//Le da la propiedad principal y el header a la peticion
			conn.setRequestProperty(ACCEPT, HEADER);

			if(conn.getResponseCode()!=200){
				//si falla la peticion devuelve una excepcion.
				throw new EstacionVcubException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			//Lee la respuesta del servidor extrae los datos del JSON y los asigna a la variable local.
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output=buff.readLine();
			String at1[] = output.split(",");
			for (String string : at1) {
				String at2[] = string.split(":");
				if("[{\"id\"".equals(at2[0]))
					data.setId(Long.parseLong(at2[1]));
				if("\"nombre\"".equals(at2[0]))
					data.setNombre(at2[1].replaceAll("\"", "").replaceAll("}", "").replaceAll("]", ""));
				if("\"capacidad\"".equals(at2[0]))
					data.setCapacidad(Integer.parseInt(at2[1]));
				if("\"vcubs\"".equals(at2[0]))
					data.setVcubs(Integer.parseInt(at2[1]));
			}
			conn.disconnect();
		}catch(Exception e){
			Logger.info(e);
		}
	}

	/**
	 * Informa al servidor central que la estacion ha sido llenada y devuelve la cantidad de vcubs ahora disponibles.
	 * @return int cantidad de vcubs ahora disponibles
	 * @throws EstacionVcubException Error de conexion con el servidor central
	 */
	public int llenarEstacion() throws EstacionVcubException {
		int rta=0;
		try{
			//Construye el url y pone el id de la estacion en el header
			URL url = new URL(URL+data.getId());
			//Arma el request y lo envia al servidor.
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty(ACCEPT, HEADER);

			//Recoge la respuesta del servidor y devuelve la cantidad de vcubs ahora disponible.
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output=buff.readLine();
			String at1[] = output.split(",");
			for (String string : at1) {
				String at2[] = string.split(":");
				if("\"capacidad\"".equals(at2[0]))
					data.setCapacidad(Integer.parseInt(at2[1]));
				if("\"vcubs\"".equals(at2[0]))
					data.setVcubs(Integer.parseInt(at2[1]));
			}
			rta = data.getCapacidad();
			conn.disconnect();
			return rta;
		}catch(Exception e){
			//En caso de un error de conexion envia un mensaje
			throw new EstacionVcubException(e.getMessage());
		}
	}

	/**
	 * Informa al servidor que el usuario con numero de documento de identificacion dado ha reservado una vcub de la estacion
	 * @param usuarioCC Elnumero del documento de identificacion del usuario
	 * @return Devuelve la cantidad de vcubs que tiene reservadas el usuario
	 * @throws EstacionVcubException En caso de un error de conexion devuelve una excepcion
	 */
	public String retiroVcub(Long usuarioCC) throws EstacionVcubException{
		String rta="";
		try{
			//Arma el url del request añadiendo los ids correspondientes
			URL url = new URL(URL+data.getId()+"/usuario/"+usuarioCC);
			//Realiza el request al servidor por medio de un PUT
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty(ACCEPT, HEADER);

			if(conn.getResponseCode()!=200){
				//En caso de un error de conexion arroja una excepcion
				throw new EstacionVcubException("Failed : HTTP error code : " + conn.getResponseCode()+"  "+conn.getResponseMessage());
			}
			//Recoge los datos de respuesta del servidor y actualiza la informacion local
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			while((output = buff.readLine())!=null){
				String[] at1 = output.split(",");
				for (String string : at1) {
					String[] at2=string.split(":");
					if("\"vcubsEnUso\"".equals(at2[0]))
						rta = at2[1]+":";
				}
			}
			conn.disconnect();
			return rta;
		}catch(Exception e){
			throw new EstacionVcubException(e.getMessage());
		}
	}

	/**
	 * Devuelve una vcub a la estacion dado el numero de identificacion del usuario que la reservo. 
	 * @param usuarioCC numero de identificacion del usuario que alquilo la vcub
	 * @return cantdad de vcubs que tiene el usuario reservadas
	 * @throws EstacionVcubException en caso de un error de conexion devuelve una excepcion
	 */
	public String devolucionVcub(Long usuarioCC) throws EstacionVcubException{
		String rta="";
		try{
			//Arma un request HTTP y envia los datos al servidor.
			URL url = new URL(URL+data.getId()+"/usuario/"+usuarioCC+"/devolver");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty(ACCEPT, HEADER);

			if(conn.getResponseCode()!=200){
				//En caso de un error devuelve una excepcion
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode()+"  "+conn.getResponseMessage());
			}
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			//En caso de exito recibe los datos de respuesta del servidor y actualiza los datos localmente.
			while((output = buff.readLine())!=null){
				String[] at1 = output.split(",");
				for (String string : at1) {
					String[] at2=string.split(":");
					if("\"vcubsEnUso\"".equals(at2[0]))
						rta = at2[1]+":";
					else if ("\"error".equals(at2[0]))
						throw new Exception(at2[1].replace("\"",""));
				}
			}
			conn.disconnect();
			return rta;
		}catch(Exception e){
			throw new EstacionVcubException(e.getMessage());
		}
	}

	/**
	 * Verifica la informacion de un usuario dado su numero de identificacion
	 * @param usuarioCC numero de identificacion del usuario a revisar.
	 * @return los datos del usuario, arroja una excepcion si el usuario no esta registrado en la base de datos.
	 * @throws EstacionVcubException En caso de un error de conexion devuelve una excepcion.
	 */
	public String verificarUsuario(Long usuarioCC)throws EstacionVcubException{
		String rta="";
		try{
			URL url = new URL(URL+usuarioCC+"/login");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty(ACCEPT, HEADER);
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			while((output = buff.readLine())!=null){
				String[] at1 = output.split(",");
				for (String string : at1) {
					String[] at2=string.split(":");
					if("\"vcubsEnUso\"".equals(at2[0]))
						rta = rta + at2[1];
					if("\"error\"".equals(at2[0]))
						throw new Exception(at2[1]);
					if("\"nombre\"".equals(at2[0]))
						rta = at2[1]+":";
				}
			}
			conn.disconnect();
			return rta;
		}catch(Exception e){
			throw new EstacionVcubException(e.getMessage());
		}
	}

	/**
	 * Persiste la informacion de la estacion cuando su sistema es cerrado.
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
