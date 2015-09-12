package Interface;

public interface IEstacionVcub {
	public int llenarEstacion();
	public String retiroVcub(Long usuarioCC);
	public String devolucionVcub(Long usuarioCC) throws Exception;
}
