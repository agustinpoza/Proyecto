package Auxiliares;

public class Entrada<K,V> implements Entry<K,V> {
	private K clave;
	private V valor;
	/**
	 * inicializamos la entrada con una clave y un valor
	 * @param clave
	 * @param valor
	 */
	public Entrada(K clave, V valor) {this.clave = clave;  this.valor = valor;}

	/**
	 * consultamos la clave de la entrada
	 * @return clave
	 */
	public K getKey() {
		return clave;
	}

	/**
	 * consultamos el valor de la entrada
	 */
	public V getValue() {
		return valor;
	}
	/**
	 * seteamos la clave de la entrada
	 * @param clave
	 */
	public void setKey(K clave) {
		this.clave = clave;
	}
	/**
	 * seteamos el valor de la entrada
	 * @param valor
	 */
	public void setValue(V valor) {
		this.valor = valor;
	}
	/**
	 * generamos un string para visualizar los valores de la entrada
	 * @return string de la forma (clave,valor)
	 */
	public String toString() {
		return "("+getKey()+","+getValue()+")";
	}
}
