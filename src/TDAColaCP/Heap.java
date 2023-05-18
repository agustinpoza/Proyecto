package TDAColaCP;

import java.util.Comparator;

import Auxiliares.Entrada;
import Auxiliares.Entry;
import Excepciones.EmptyPriorityQueueException;
import Excepciones.InvalidKeyException;

public class Heap<K,V> implements PriorityQueue<K,V> {
    protected Entrada<K,V> [] elem;
    protected int size;
    protected Comparator<K> cmp;

    public Heap(int maxElem, Comparator<K> cmp) {
        elem = (Entrada<K, V> []) new Entrada[maxElem];
        size = 0;
        this.cmp = cmp;
    }

    public Heap(Comparator<K> cmp){
        this(1000, cmp);
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
    public Entry<K, V> min() throws EmptyPriorityQueueException {
        if(isEmpty()){
            throw new EmptyPriorityQueueException("Cola vacia.");
        }
        return elem[1];
    }

    @Override
    public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
        if(size+1 >= elem.length){
            reSize();
        }
        checkKey(key);
        Entrada<K,V> insert = new Entrada<K,V>(key, value);
        elem[++size] = insert;
        int pos = size;
        boolean estado = true;
        while (pos > 1 && estado){
            Entrada<K,V> elemPos = elem[pos];
            Entrada<K,V> elemParent = elem[pos/2];
            if(cmp.compare(elemPos.getKey(), elemParent.getKey()) < 0){
                swap(pos, pos/2);
                pos = pos/2;
            }
            else {
                estado = false;
            }
        }
        return insert;
    }

    @Override
    public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
        if(isEmpty()) {
            throw new EmptyPriorityQueueException("Cola vacia.");
        }
        Entrada<K,V> toReturn = elem[1];
        elem[1] = elem[size];
        elem[size--] = null;
        int pos = 1;
        boolean estado = true;
        while(estado) {
            int hi = pos*2; //Hijo izquierdo
            int hd = pos*2 + 1; //Hijo derecho
            int posCompare; //Posicion a comparar con pos (hi o hd)
            if(elem[hi] == null){
                estado = false;
            }
            else {
                if(elem[hi] != null && elem[hd] != null) {
                    if(cmp.compare(elem[hi].getKey(), elem[hd].getKey()) < 0){
                        posCompare = hi;
                    }
                    else {
                        posCompare = hd;
                    }
                }
                else {
                    posCompare = hi;
                }
                if(cmp.compare(elem[pos].getKey(), elem[posCompare].getKey()) > 0) {
                    swap(pos, posCompare);
                    pos = posCompare;
                }
                else {
                    estado = false;
                }
            }
        }
        return toReturn;
    }

    /**
     * Intercambia las entradas correspondientes a las posiciones recibidas.
     * @param pos
     * @param posParent
     */
    private void swap(int pos, int posParent){
        Entrada<K,V> aux = elem[pos];
        elem[pos] = elem[posParent];
        elem[posParent] = aux;
    }

    private void reSize() {
        Entrada<K,V> [] arr = (Entrada<K, V> []) new Entrada[elem.length*2];
        int i = 0;
        for(Entrada<K,V> e : elem){
            arr[i++]= e;
        }
        elem = arr;
    }

    private void checkKey(K key) throws InvalidKeyException {
        if(key == null) {
            throw new InvalidKeyException("Clave incorrecta.");
        }
    }
}
