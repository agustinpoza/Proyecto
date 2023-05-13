package TDALista;

public class Nodo<E> implements Position<E> {
	private E elemento;
	private Nodo<E> siguiente;
	private Nodo<E> anterior;
	
	public Nodo(E e, Nodo<E> sig) {
		this.elemento = e;
		this.siguiente = sig;
	}
	public Nodo(E e, Nodo<E> ant, Nodo<E> sig) {
		this.elemento = e;
		this.siguiente = sig;
		this.anterior = ant;
	}
	
	public Nodo(E e) {
		this.elemento = e;
		this.siguiente = null;
	}
	public Nodo() {
		this.elemento = null;
		this.siguiente = null;
		this.anterior = null;
	}

	//setters
	public void setElem(E e) {
		this.elemento = e;
	}
	public void setNext(Nodo<E> sig) {
		this.siguiente = sig;
	}
	public void setPrev(Nodo<E> ant) {
		this.anterior = ant;
	}
	
	//getters
	public E element() {
		return this.elemento;
	}
	public Nodo<E> getNext(){
		return this.siguiente;
	}
	public Nodo<E> getPrev(){
		return this.anterior;
	}
}
