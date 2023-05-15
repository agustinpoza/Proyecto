package TDALista;

import java.util.Iterator;

import Auxiliares.Position;
import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;

/**
 * Interface PositionList
 * @author C�tedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computaci�n, UNS.
 */

public interface PositionList<E> extends Iterable<E>
{
	/**
	 * Consulta la cantidad de elementos de la lista.
	 * @return Cantidad de elementos de la lista.
	 */
	public int size();
	
	/**
	 * Consulta si la lista est� vac�a.
	 * @return Verdadero si la lista est� vac�a, falso en caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve la posici�n del primer elemento de la lista. 
	 * @return Posici�n del primer elemento de la lista.
	 * @throws EmptyListException si la lista est� vac�a.
	 */
	public Position<E> first() throws EmptyListException;
	
	/**
	 * Devuelve la posici�n del �ltimo elemento de la lista. 
	 * @return Posici�n del �ltimo elemento de la lista.
	 * @throws EmptyListException si la lista est� vac�a.
	 * 
	 */
	public Position<E> last() throws EmptyListException;;
	
	/**
	 * Devuelve la posici�n del elemento siguiente a la posici�n pasada por par�metro.
	 * @param p Posici�n a obtener su elemento siguiente.
	 * @return Posici�n del elemento siguiente a la posici�n pasada por par�metro.
	 * @throws InvalidPositionException si el posici�n pasada por par�metro es inv�lida o la lista est� vac�a.
	 * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde al �ltimo elemento de la lista.
	 */
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Devuelve la posici�n del elemento anterior a la posici�n pasada por par�metro.
	 * @param p Posici�n a obtener su elemento anterior.
	 * @return Posici�n del elemento anterior a la posici�n pasada por par�metro.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o la lista est� vac�a.
	 * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde al primer elemento de la lista.
	 * @throws EmptyListException 
	 */
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Inserta un elemento al principio de la lista.
	 * @param element Elemento a insertar al principio de la lista.
	 */
	public void addFirst(E element);
	
	/**
	 * Inserta un elemento al final de la lista.
	 * @param element Elemento a insertar al final de la lista.
	 */
	public void addLast(E element);
	
	/**
	 * Inserta un elemento luego de la posici�n pasada por par�matro.
	 * @param p Posici�n en cuya posici�n siguiente se insertar� el elemento pasado por par�metro.
	 * @param element Elemento a insertar luego de la posici�n pasada como par�metro.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.
	 */
	public void addAfter(Position<E> p, E element) throws InvalidPositionException;
	
	/**
	 * Inserta un elemento antes de la posici�n pasada como par�metro.
	 * @param p Posici�n en cuya posici�n anterior se insertar� el elemento pasado por par�metro. 
	 * @param element Elemento a insertar antes de la posici�n pasada como par�metro.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.
	 */
	public void addBefore(Position<E> p, E element) throws InvalidPositionException;
	
	/**
	 * Remueve el elemento que se encuentra en la posici�n pasada por par�metro.
	 * @param p Posici�n del elemento a eliminar.
	 * @return element Elemento removido.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.
	 */	
	public E remove(Position<E> p) throws InvalidPositionException;

	/**
	
	 * Establece el elemento en la posici�n pasados por par�metro. Reemplaza el elemento que se encontraba anteriormente en esa posici�n y devuelve el elemento anterior.
	 * @param p Posici�n a establecer el elemento pasado por par�metro.
	 * @param element Elemento a establecer en la posici�n pasada por par�metro.
	 * @return Elemento anterior.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.	 
	 */
	public E set(Position<E> p, E element) throws InvalidPositionException;
	
	/**
	 * Devuelve un un iterador de todos los elementos de la lista.
	 * @return Un iterador de todos los elementos de la lista.
	 */
	public Iterator<E> iterator();
	
	/**
	 * Devuelve una colecci�n iterable de posiciones.
	 * @return Una colecci�n iterable de posiciones.
	 */
	public Iterable<Position<E>> positions();
}