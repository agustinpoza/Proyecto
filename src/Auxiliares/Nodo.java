package Auxiliares;

public class Nodo<E> implements Position<E> {
	private E elemento;
	private Nodo<E> siguiente;
	private Nodo<E> anterior;
	
	
	/**
	 * crea un nodo con un elemento y referencia al nodo anterior y al siguiente
	 * @param elemento
	 * @param nodo anterior
	 * @param nodo siguiente
	 */
	public Nodo(E e, Nodo<E> ant, Nodo<E> sig) {
		this.elemento = e;
		this.siguiente = sig;
		this.anterior = ant;
	}
	
	/**
	 * crea un nodo nulo
	 */
	public Nodo() {
		this.elemento = null;
		this.siguiente = null;
		this.anterior = null;
	}

	/**
	 * seteamos en el nodo un elemento
	 * @param elemento
	 */
	public void setElem(E e) {
		this.elemento = e;
	}
	/**
	 * seteamos el nodo que le sigue
	 * @param nodo siguiente
	 */
	public void setNext(Nodo<E> sig) {
		this.siguiente = sig;
	}
	/**
	 * seteamos el nodo anterior
	 * @param nodo anterior
	 */
	public void setPrev(Nodo<E> ant) {
		this.anterior = ant;
	}
	
	/**
	 * consultamos el elemento del nodo
	 * @return elemento
	 */
	public E element() {
		return this.elemento;
	}
	/**
	 * consultamos cual es el nodo siguiente
	 * @return nodo siguiente
	 */
	public Nodo<E> getNext(){
		return this.siguiente;
	}
	/**
	 * consultamos cual es el nodo anterior
	 * @return nodo anterior
	 */
	public Nodo<E> getPrev(){
		return this.anterior;
	}
}
