package Auxiliares;

public interface Entry<K,V> {
	/**
	 * consulta la clave de la entrada
	 * @return clave
	 */
	public K getKey();
	/**
	 * consulta el valor de la entrada
	 * @return valor
	 */
	public V getValue();
}
