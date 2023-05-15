package TDALista;
import java.util.*;

import Auxiliares.Position;
import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;

public class ElementIterator<E> implements Iterator<E> {
	
	protected PositionList<E> list; //lista a iterar
	protected Position<E> cursor; // posicion del elemento corriente
	
	public ElementIterator(PositionList<E> l) {
		list = l;
		if(list.isEmpty()) cursor = null; // si la lista esta vacia, la posicion corriente es nula
		else
			try {
				cursor = list.first();
			} catch (EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@Override
	public boolean hasNext() {
		return cursor != null;
	}

	@Override
	public E next() throws NoSuchElementException {
		if (cursor == null) throw new NoSuchElementException("error: no hay siguiente");
		E toReturn = cursor.element(); //salvo el elemento corriente
		try {
			cursor = (cursor == list.last()) ? null : list.next(cursor);
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toReturn;
	}

}
