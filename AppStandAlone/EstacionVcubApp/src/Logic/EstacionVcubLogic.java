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
import java.net.HttpURLConnection;
import java.net.URL;

public class EstacionVcubLogic{
	
	EstacionVcubSerializable data;
	
	public EstacionVcubLogic(){
		try{
			ObjectInputStream ins = new ObjectInputStream(new FileInputStream("./data/data")); 
			data = (EstacionVcubSerializable)ins.readObject();
			ins.close();
		}catch(FileNotFoundException e){
			data = new EstacionVcubSerializable();
			File file = new File("./data/data");
			try {
				file.createNewFile();
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}
			getEstacion();
			System.out.println(e.getMessage());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public String getNombreEstacion(){
		return data.getNombre();
	}
	
	public void getEstacion(){
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
				if(at2[0].equals("\"nombre\""))
					data.setNombre(at2[1].replaceAll("\"", ""));
				if(at2[0].equals("\"capacidad\""))
					data.setCapacidad(Integer.parseInt(at2[1]));
				if(at2[0].equals("\"vcubs\""))
					data.setVcubs(Integer.parseInt(at2[1]));
			}
			System.out.println(data.getNombre());
			System.out.println(data.getId());
			System.out.println(data.getCapacidad());
			System.out.println(data.getVcubs());
			conn.disconnect();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int llenarEstacion() {
		int rta=0;
		try{
			URL url = new URL("http://172.24.100.35:9000/estacionvcub/"+data.getId());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
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
				if(at2[0].equals("\"nombre\""))
					data.setNombre(at2[1].replaceAll("\"", ""));
				if(at2[0].equals("\"capacidad\""))
					data.setCapacidad(Integer.parseInt(at2[1]));
				if(at2[0].equals("\"vcubs\""))
					data.setVcubs(Integer.parseInt(at2[1]));
			}
			conn.disconnect();
			return rta;
		}catch(Exception e){
			e.printStackTrace();
		}
		return rta;
	}

	public String retiroVcub(Long usuarioCC) {
		String rta="";
		try{
			URL url = new URL("http://172.24.100.35:9000/estacionvcub/"+data.getId()+"/usuario/"+usuarioCC+"");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode()!=200){
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			System.out.println("Output from server .... \n");
			while((output = buff.readLine())!=null){
				String[] at1 = output.split(",");
				for (String string : at1) {
					String[] at2=string.split(":");
					if(at2[0].equals("\"vcubsEnUso\""))
						rta = at2[1]+":";
				}
				System.out.println(output);
			}
			rta = rta+conn.getResponseCode()+"";
			conn.disconnect();
			return rta;
		}catch(Exception e){
			e.printStackTrace();
		}
		return rta;
	}

	public String devolucionVcub(Long usuarioCC) throws Exception{
		String rta="";
		try{
			URL url = new URL("http://172.24.100.35:9000/estacionvcub/"+data.getId()+"/usuario/"+usuarioCC+"/devolucionVcub");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode()!=200){
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			System.out.println("Output from server .... \n");
			while((output = buff.readLine())!=null){
				String[] at1 = output.split(",");
				for (String string : at1) {
					String[] at2=string.split(":");
						if(at2[0].equals("\"vcubsEnUso\""))
							rta = at2[1]+":";
						if(at2[0].equals("\"error\""))
							throw new Exception(at2[1]);
				}
				System.out.println(output);
			}
			rta = rta+conn.getResponseCode();
			conn.disconnect();
			return rta;
		}catch(Exception e){
			e.printStackTrace();
		}
		return rta;
	}

	public String verificarUsuario(Long usuarioCC){
		String rta="";
		try{
			URL url = new URL("http://172.24.100.35:9000/usuario/"+usuarioCC);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode()!=200){
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			System.out.println("Output from server .... \n");
			while((output = buff.readLine())!=null){
				String[] at1 = output.split(",");
				for (String string : at1) {
					String[] at2=string.split(":");
						if(at2[0].equals("\"vcubsEnUso\""))
							rta = at2[1]+":";
						if(at2[0].equals("\"error\""))
							throw new Exception(at2[1]);
						if(at2[0].equals("\"nombre\""))
							rta = rta + at2[1];
				}
				System.out.println(output);
			}
			rta = rta+conn.getResponseCode();
			conn.disconnect();
			return rta;
		}catch(Exception e){
			e.printStackTrace();
		}
		return rta;
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
}