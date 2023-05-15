package TDALista;

import java.util.Iterator;

import Auxiliares.Position;
import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;

public class ListaDE<E> implements PositionList<E> {
    protected Nodo<E> header;
    protected Nodo<E> trailer;
    protected int size;

    public ListaDE() {
        header = new Nodo<E>();
        trailer = new Nodo<E>();
        header.setSiguiente(trailer);
        trailer.setAnterior(trailer);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<E> first() throws EmptyListException {
        if(isEmpty()){
            throw new EmptyListException("Lista vacia.");
        }
        return header.getSiguiente();
    }

    @Override
    public Position<E> last() throws EmptyListException {
        if(isEmpty()){
            throw new EmptyListException("Lista vacia.");
        }
        return trailer.getAnterior();
    }

    @Override
    public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
        Nodo<E> nodo = checkPosition(p);
        if(nodo.getSiguiente() == trailer){
            throw new BoundaryViolationException("La posicion corresponde al utlimo elemento de la lista.");
        }
        return nodo.getSiguiente();
    }

    @Override
    public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
        Nodo<E> nodo = checkPosition(p);
        if(nodo.getAnterior() == header) {
            throw new BoundaryViolationException("La posicion corresponde al primer elemento de la lista.");
        }
        return nodo.getAnterior();
    }

    @Override
    public void addFirst(E element) {
        Nodo<E> insert = new Nodo<E>(element, header, header.getSiguiente());
        header.getSiguiente().setAnterior(insert);
        header.setSiguiente(insert);
        size++;
    }

    @Override
    public void addLast(E element) {
        Nodo<E> insert;
        if(isEmpty()){
            addFirst(element);
        }
        else {
            insert = new Nodo<E>(element, trailer.getAnterior(), trailer);
            trailer.getAnterior().setSiguiente(insert);
            trailer.setAnterior(insert);
            size++;
        }
    }

    @Override
    public void addAfter(Position<E> p, E element) throws InvalidPositionException {
        Nodo<E> nodo = checkPosition(p);
        Nodo<E> insert;
        if(isEmpty()) {
            throw new InvalidPositionException("Lista vacia.");
        }
        insert = new Nodo<E>(element, nodo, nodo.getSiguiente());
        nodo.getSiguiente().setAnterior(insert);
        nodo.setSiguiente(insert);
        size++;
    }

    @Override
    public void addBefore(Position<E> p, E element) throws InvalidPositionException {
        Nodo<E> nodo = checkPosition(p);
        Nodo<E> insert;
        if(isEmpty()) {
            throw new InvalidPositionException("Lista vacia.");
        }
        insert = new Nodo<E>(element, nodo.getAnterior(), nodo);
        nodo.getAnterior().setSiguiente(insert);
        nodo.setAnterior(insert);
        size++;
    }

    @Override
    public E remove(Position<E> p) throws InvalidPositionException {
        Nodo<E> remove = checkPosition(p);
        E toReturn;
        remove.getAnterior().setSiguiente(remove.getSiguiente());
        remove.getSiguiente().setAnterior(remove.getAnterior());
        size--;
        toReturn = remove.element();
        remove.setElement(null);
        remove.setAnterior(null);
        remove.setSiguiente(null);
        return toReturn;
    }

    @Override
    public E set(Position<E> p, E element) throws InvalidPositionException {
        Nodo<E> nodo = checkPosition(p);
        E toReturn;
        if(isEmpty()) {
            throw new InvalidPositionException("Lista vacia.");
        }
        toReturn = nodo.element();
        nodo.setElement(element);
        return toReturn;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator<>(this);
    }

    @Override
    public Iterable<Position<E>> positions() {
        PositionList<Position<E>> toReturn = new ListaDE<Position<E>>();
        Nodo<E> nodo = header.getSiguiente();
        while (nodo != trailer) {
            toReturn.addLast(nodo);
            nodo = nodo.getSiguiente();
        }
        return toReturn;
    }

 

    private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
        try {
            if( p == null )
                throw new InvalidPositionException("Posicion nula.");
            if( p == header )
                throw new InvalidPositionException("Posicion invalida.");
            if( p == trailer )
                throw new InvalidPositionException("Posicion invalida.");
            if( p.element() == null )
                throw new InvalidPositionException("Posicion eliminada previamente.");
            Nodo<E> n = (Nodo<E>) p;
            if ((n.getAnterior() == null) || (n.getSiguiente() == null))
                throw new InvalidPositionException("La posicion no tiene anterior o siguiente");
            return n;
        } catch (ClassCastException e) {
            throw new InvalidPositionException("Posicion no es de tipo Nodo E");
        }
    }

    /**
     * Clase Nodo anidada, permite evitar modificar la lista mediante casting
     * explicito desde la clase cliente.
     * Cliente no debe conocer como funciona la estructura, solamente debe saber
     * que hace.
     * @param <E> generico.
     */
    private class Nodo<E> implements Position<E> {
        private E element;
        private Nodo<E> siguiente;
        private Nodo<E> anterior;

        public Nodo(E element, Nodo<E> anterior,Nodo<E> siguiente) {
            this.element = element;
            this.anterior = anterior;
            this.siguiente = siguiente;
        }

        public Nodo(E element) {
            this(element, null, null);
        }

        public Nodo() {
            this(null, null, null);
        }

        public E element() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Nodo<E> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<E> siguiente) {
            this.siguiente = siguiente;
        }

        public Nodo<E> getAnterior() {
            return anterior;
        }

        public void setAnterior(Nodo<E> anterior) {
            this.anterior = anterior;
        }
    }
}