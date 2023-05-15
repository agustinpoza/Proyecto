import Excepciones.DatoInvalidoException;
import TDADiccionario.Entry;

public class Par<K, V> implements Entry<K,V>{
	
	private V lu;
	private K nota;
	
	public Par() {this.lu = null; this.nota = null;}

	public void setNota(K nota) throws DatoInvalidoException{
		if(nota != null && (Integer)nota>=0 && (Integer)nota<=100) this.nota = nota;
		else throw new DatoInvalidoException("Nota Invalida");
	}

	public void setLu(V lu) throws DatoInvalidoException {
		if(lu != null && (Integer)lu>=0) this.lu = lu;
		else throw new DatoInvalidoException("Lu Invalido");
	}

	public V getValue(){
		return lu;
	}

	public K getKey() {
		return nota;
	}
	
	
}
