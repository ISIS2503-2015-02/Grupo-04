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
	
	public static final String ACCEPT="Accept";
	public static final String HEADER="application/json";
	public static final String URL="http://172.24.100.35:9000/estacionvcub";

	private EstacionVcubSerializable data;

	public EstacionVcubLogic(){
        try{
			ObjectInputStream ins = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO)); 
			data = (EstacionVcubSerializable)ins.readObject();
			ins.close();
		}catch(FileNotFoundException e){
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

	public String getNombreEstacion(){
		return data.getNombre();
	}

	public void getEstacion(){
		try{
			URL url = new URL(URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty(ACCEPT, HEADER);

			if(conn.getResponseCode()!=200){
				throw new EstacionVcubException("Failed : HTTP error code : " + conn.getResponseCode());
			}
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

	public int llenarEstacion() throws EstacionVcubException {
		int rta=0;
		try{
			URL url = new URL(URL+data.getId());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty(ACCEPT, HEADER);

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
			throw new EstacionVcubException(e.getMessage());
		}
	}

	public String retiroVcub(Long usuarioCC) throws EstacionVcubException{
		String rta="";
		try{
			URL url = new URL(URL+data.getId()+"/usuario/"+usuarioCC);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty(ACCEPT, HEADER);

			if(conn.getResponseCode()!=200){
				throw new EstacionVcubException("Failed : HTTP error code : " + conn.getResponseCode()+"  "+conn.getResponseMessage());
			}
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

	public String devolucionVcub(Long usuarioCC) throws EstacionVcubException{
		String rta="";
		try{
			URL url = new URL(URL+data.getId()+"/usuario/"+usuarioCC+"/devolver");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty(ACCEPT, HEADER);

			if(conn.getResponseCode()!=200){
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode()+"  "+conn.getResponseMessage());
			}
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
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
