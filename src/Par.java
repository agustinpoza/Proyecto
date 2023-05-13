import Excepciones.DatoInvalidoException;
import TDADiccionario.Entry;

public class Par<V, K> implements Entry<K,V>{
	
	private K lu;
	private V nota;
	
	public Par() {this.lu = null; this.nota = null;}

	public void setNota(V nota) throws DatoInvalidoException{
		if(nota != null && (Integer)nota>=0 && (Integer)nota<=100) this.nota = nota;
		else throw new DatoInvalidoException("Nota Invalida");
	}

	public void setLu(K lu) throws DatoInvalidoException {
		if(lu != null && (Integer)lu>=0) this.lu = lu;
		else throw new DatoInvalidoException("Lu Invalido");
	}

	public K getKey(){
		return lu;
	}

	public V getValue() {
		return nota;
	}
	
	
}
