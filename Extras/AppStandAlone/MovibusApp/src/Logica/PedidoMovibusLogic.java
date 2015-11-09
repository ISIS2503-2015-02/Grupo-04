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

	//----------------------------------------------------------------------------
	//  Constantes
	//----------------------------------------------------------------------------
	
	/**
	 * Algo del id del pedido
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constante que nos da la URL base para realizar pedidos de servicios al servidor concernietes a PedidoMovibus
	 */
	private static final String URL="http://172.24.100.49:9000/pedidoMovibus/";
	
	//----------------------------------------------------------------------------
	//  Atributos
	//----------------------------------------------------------------------------
	
	/**
	 * Atributo que guarda la logica del PedidoMovibusSerializable
	 */
	private PedidoMovibusSerializable data;

	//----------------------------------------------------------------------------
	//  Constructor
	//----------------------------------------------------------------------------
	
	/**
	 * Constructor que inicializa el atributo data con un PedidoMovibusSerializable sin datos
	 */
	public PedidoMovibusLogic(){
		data = new PedidoMovibusSerializable();
	}

	//----------------------------------------------------------------------------
	//   Metodos
	//----------------------------------------------------------------------------
	
	/**
	 * Nos da el id del pedido actual
	 * @return Long que es el id del pedido actual si es q hay en caso contrario retorna null
	 */
	public Long getId(){
		return data.getId();
	}
	
	/**
	 * Metodo que nos da el pedidoMovibusSerializable actual
	 * @return data, puede estar creada y no ser null, pero los datos de esta pueden no estar inicializados y ser null
	 */
	public PedidoMovibusSerializable getPedido(){
		return data;
	}
	
	/**
	 * Metodo que le pide al servidor los datos de un pedidoMovibus basado en la id del movibus q se da por parametro
	 * @param long1 id del movibus del cual se quiere informacion sobre su pedido actual
	 */
	public void getPedidoMovibus(Long long1){
		try{
			//  Conrexion con el servidor y realizacion del pedido del servicio de dar pedido movibus
			URL url = new URL(URL+"/byMovibus");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			// Envio de los datos de movibus
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			JSONObject p = new JSONObject();
			StringEnvelope env = new StringEnvelope();
			String ciphertext = StringEnvelope.wrap(long1.toString(), "key");    // Encryptado de los datos a enviar
			p.put("id", ciphertext);
			
			wr.write(p.toString());
			wr.flush();
			
			if(conn.getResponseCode()!=200){
				throw new MovibusException("Failed : HTTP error code : " + conn.getResponseCode());
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
			conn.disconnect();
		}catch(Exception e){
			Logger.info(e);
		}
	}
	
	/**
	 * Metodo que informa al servidor que se termino el pedido y el dice cuanto tiempo se demoro en este para q termine el pedido en el servidor
	 * @param tempR int que indica el tiempo que se demoro en realizar el pedido
	 * @throws MovibusException En caso de haber problemas de conexion con el servidor
	 */
	public void reportarTerminacion(int tempR) throws MovibusException{
		Long tem = new Long(tempR);
		try{
			URL url = new URL(URL+"reportarPedidoTerminado");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			
			JSONObject movRepPos   = new JSONObject();

			movRepPos.put("id", data.getId());
			movRepPos.put("tiempo",tem);
			
			String cipherText = StringEnvelope.wrap(movRepPos.toString(), "aa09cee77e1d606d5ab06500ac95729c");
			JSONObject movRepPos2   = new JSONObject();
			
			movRepPos2.put("envelop", cipherText);

			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(movRepPos2.toString());
			wr.flush();
			
			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			while((output = buff.readLine())!=null){
				String[] at1 = output.split(",");
				for (String string : at1) {
					String[] at2=string.split(":");
					if("\"error".equals(at2[0])){
						throw new MovibusException(at2[1].replace("\"",""));
					}
				}
			}
			conn.disconnect();
		}catch(Exception e){
			throw new MovibusException(e.getMessage());
		}
	}
}
