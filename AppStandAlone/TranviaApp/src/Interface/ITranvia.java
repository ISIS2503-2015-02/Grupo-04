package Interface;

import DTO.TranviaDTO;

public interface ITranvia {
	/**
	 * Envia al servidor central la posicion actual del tranvia cada x segundos.
	 * @param int lat, coordenada de latitud de la posicion del vehiculo.
	 * @param int lon, coordenada de longitud de la posicion del vehiculo.
	 */
	public int sendPos(TranviaDTO t);
	
	/**
	 * Envia el estado acual del tranvia a la central TBC.
	 */
	public int enviarEstado(TranviaDTO t);
	
	/**
	 * Reporta el kilometraje desde la utlima revision.
	 */
	public int kmUltRevision(TranviaDTO t);
}
