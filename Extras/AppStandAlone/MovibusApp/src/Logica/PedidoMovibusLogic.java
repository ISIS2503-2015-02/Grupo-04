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
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.*;
import org.owasp.StringEnvelope;

import persistence.ConductorSerializable;
import persistence.MovibusSerializable;
import persistence.PedidoMovibusSerializable;
import persistence.UsuarioSerializable;

public class PedidoMovibusLogic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	PedidoMovibusSerializable data;
	
	public PedidoMovibusLogic()
	{
		try{
			ObjectInputStream ins = new ObjectInputStream(new FileInputStream("./data/data")); 
			data = (PedidoMovibusSerializable)ins.readObject();
			ins.close();
		}catch(FileNotFoundException e){
			data = new PedidoMovibusSerializable();
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
	
	public PedidoMovibusSerializable getPedido()
	{
		return data;
	}
	
	public void getPedidoMovibus(Long long1){
		try{
			URL url = new URL("http://172.24.100.49:9000/pedidoMovibus/"+long1+"/byMovibus");
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
			//Pedido
			Long idPed = j.getLong("id");
			
			String fech1 = j.getString("fechaPedido");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date fechP = sdf.parse(fech1);

			String fech2 = j.getString("fechaEjecucion");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date fechE = sdf2.parse(fech2);
				
			int tempr = j.getInt("tiempoEstimado");
			int tempe = j.getInt("tiempoReal");
			Double latDi = j.getDouble("direccionDestinoLA");
			Double lonDi = j.getDouble("direccionDestinoLO");
			
			data.setId(idPed);
			data.setFechaEjecucion(fechE);
			data.setFechaPedido(fechP);
			data.setTiempoEstimado(tempe);
			data.setTiempoReal(tempr);
			data.setDireccionDestinoLA(latDi);
			data.setDireccionDestinoLO(lonDi);
			
				//Longitudes y latitudes de la ruta
			JSONArray lats= j.getJSONArray("rutaLatitudes");
			JSONArray longs= j.getJSONArray("rutaLongitudes");
			Double[] latits=new Double[lats.length()];
			Double[] longes=new Double[longs.length()];
			for(int i=0;i<lats.length();i++)
			{
				latits[i]=lats.getDouble(i);
				longes[i]=longs.getDouble(i);
			}
			
			data.setLatitud(latits);
			data.setLongitud(longes);
			
			//Usuario
			UsuarioSerializable usu = new UsuarioSerializable();
			JSONObject us= j.getJSONObject("usuario");
			Long id = us.getLong("id");
			String nomb = us.getString("nombre");
			int ced = us.getInt("cedula");
			int cel = us.getInt("celular");
			usu.setNombre(nomb);
			usu.setCedula(ced);
			usu.setCelular(cel);
			usu.setId(id);
			
			data.setUsuario(usu);
			
			
			//Conductor
			JSONObject cond= j.getJSONObject("conductor");
			ConductorSerializable condu= new ConductorSerializable();
			Long idCon= cond.getLong("id");
			String nombCon= cond.getString("nombre");
			int cedcon= cond.getInt("cedula");
			int celcon= cond.getInt("celular");
			condu.setId(idCon);
			condu.setNombre(nombCon);
			condu.setCedula(cedcon);
			condu.setCelular(celcon);
			
			data.setConductor(condu);
			
			//Movibus
			JSONObject movibus= j.getJSONObject("movibus");
			MovibusSerializable movi= new MovibusSerializable();
			Long idMov= movibus.getLong("id");
			int est= movibus.getInt("estado");
			Double posLat= movibus.getDouble("latitud");
			Double posLong= movibus.getDouble("longitud");
			int kil = movibus.getInt("kilometraje"); 
			movi.setId(idMov);
			movi.setEstad(est);
			movi.setKilometraje(kil);
			movi.setPosicionLat(posLat);
			movi.setPosicionLong(posLong);
			
			data.setMovibus(movi);
			
			System.out.println(data.getId());
			System.out.println("Conductor:"+data.getConductor().getNombre());
			System.out.println("Usuario:"+data.getUsuario().getNombre());
			System.out.println("Movibus id:"+data.getMovibus().getId());
			conn.disconnect();
		}catch(Exception e){
			Logger.info(e);
		}
	}
	
	public String reportarTerminacion(int tempR) throws Exception{
		Long tem = new Long(tempR);
		String rta="";
		try{
			URL url = new URL("http://172.24.100.49:9000/pedidoMovibus/reportarPedidoTerminado");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			
			JSONObject movRepPos   = new JSONObject();

			movRepPos.put("id", data.getId());
			movRepPos.put("tiempo",tem);
			
			StringEnvelope env = new StringEnvelope();
			String cipherText = env.wrap(movRepPos.toString(), "aa09cee77e1d606d5ab06500ac95729c");
			JSONObject movRepPos2   = new JSONObject();
			
			movRepPos2.put("envelop", movRepPos);

			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(movRepPos2.toString());
			wr.flush();
			
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			System.out.println("Output from server .... \n");
			while((output = buff.readLine())!=null){
				String[] at1 = output.split(",");
				for (String string : at1) {
					String[] at2=string.split(":");
					if(at2[0].equals("\"error")){
						throw new Exception(at2[1].replace("\"",""));
					}
				}
				System.out.println(output);
			}
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
			Logger.info(e);
		}
	}
}
