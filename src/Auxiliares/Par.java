package Auxiliares;
import Excepciones.DatoInvalidoException;

public class Par<K, V> implements Entry<K,V>{
	
	private V lu;
	private K nota;
	
	/**
	 * se inicializa el par vacio 
	 */
	public Par() {this.lu = null; this.nota = null;}

	/**
	 * seteamos la nota
	 * @param nota
	 * @throws DatoInvalidoException
	 */
	public void setNota(K nota) throws DatoInvalidoException{
		if(nota != null && (Integer)nota>=0 && (Integer)nota<=100) this.nota = nota;
		else throw new DatoInvalidoException("Nota Invalida");
	}

	/**
	 * seteamos el Lu
	 * @param Lu
	 * @throws DatoInvalidoException
	 */
	public void setLu(V lu) throws DatoInvalidoException {
		if(lu != null && (Integer)lu>=0) this.lu = lu;
		else throw new DatoInvalidoException("Lu Invalido");
	}
	
	/**
	 * consultamos el lu
	 * @return Lu
	 */
	public V getValue(){
		return lu;
	}

	/**
	 * conslutamos la nota
	 * @return Nota
	 */
	public K getKey() {
		return nota;
	}
	
	
}
