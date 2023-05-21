package TDAColaCP;

public class Comparador<E> implements java.util.Comparator<E> {

	/**
	 * compara dos objetos 
	 * @param objeto 1
	 * @param objeto 2
	 * @return un entero positivo si el primer argumento es mayor al segundo, un entero negativo si es menor y cero si son iguales
	 */
	public int compare(E o1, E o2) {
		return ((Comparable<E>) o1).compareTo(o2);
	}
}