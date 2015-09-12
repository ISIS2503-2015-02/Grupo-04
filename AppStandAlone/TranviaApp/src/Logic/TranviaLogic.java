package Logic;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import DTO.TranviaDTO;

public class TranviaLogic{

	public int sendPos(TranviaDTO dto) {
		int rta=0;
		try{
			URL url = new URL("http://localhost:8080/tranvia/update/"+dto.getId());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Accept", "application/json");
			
			String input="{\"estado\":"+dto.getEstado()+""
					+ ",\"kilometraje\":\""+dto.getKmAct()+""
					+ ",\"lat\":\""+dto.getLat()+""
					+ ",\"lon\":\""+dto.getLon()+""
					+ ",\"linea\":\""+dto.getLinea()+"\"}";
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			
			if(conn.getResponseCode()!=200){
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			System.out.println("Output from server .... \n");
			while((output = buff.readLine())!=null){
				System.out.println(output);
			}
			rta = conn.getResponseCode();
			conn.disconnect();
			return rta;
		}catch(Exception e){
			e.printStackTrace();
		}
		return rta;
	}

	public int enviarEstado(TranviaDTO dto) {
		//TODO revisar como se envia la informacion y que se envia.
		int rta=0;
		try{
			URL url = new URL("http://localhost:8080/tranvia/reportarAccidente/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			
			String input="{\"descripcion\":"+dto.getReporte().getDescripcion()+",\"tipoReporte\":"+dto.getReporte().getTipoReporte()+",\"id\":\""+dto.getId()+"\"}";
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			
			if(conn.getResponseCode()!=200){
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			System.out.println("Output from server .... \n");
			while((output = buff.readLine())!=null){
				System.out.println(output);
			}
			rta = conn.getResponseCode();
			conn.disconnect();
			return rta;
		}catch(Exception e){
			e.printStackTrace();
		}
		return rta;
	}

	public int kmUltRevision(TranviaDTO dto) {
		int rta=0;
		try{
			URL url = new URL("http://localhost:8080/tranvia/update/{"+dto.getId()+"}");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Accept", "application/json");
			
			String input="{\"estado\":"+dto.getEstado()+""
					+ ",\"kilometraje\":\""+dto.getKmAct()+""
					+ ",\"lat\":\""+dto.getLat()+""
					+ ",\"lon\":\""+dto.getLon()+""
					+ ",\"linea\":\""+dto.getLinea()+"\"}";
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			
			if(conn.getResponseCode()!=200){
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			System.out.println("Output from server .... \n");
			while((output = buff.readLine())!=null){
				System.out.println(output);
			}
			rta = conn.getResponseCode();
			conn.disconnect();
			return rta;
		}catch(Exception e){
			e.printStackTrace();
		}
		return rta;
	}
}
