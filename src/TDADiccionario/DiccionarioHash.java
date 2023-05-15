package TDADiccionario;

import Auxiliares.Entrada;
import Auxiliares.Entry;
import Auxiliares.Position;
import Excepciones.InvalidEntryException;
import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;
import TDALista.PositionList;
import TDALista.ListaDE;


public class DiccionarioHash<K,V> implements Dictionary<K,V>{
	
	PositionList<Entry<K,V>> [] a;
	protected int n; //cantidad de entradas del mapeo
	protected int N; //cantidad de buckets, puede ser cualquier numero primo
	protected final float FACTOR_CARGA = 0.9F;
	
	public DiccionarioHash() {
		N = 13;
		n = 0;
		a = new ListaDE[N];
		for(int i=0;i<N;i++) {
			a[i] = new ListaDE<Entry<K,V>>();;
		}
	}
	
	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return n==0;
	}

	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		checkKey(key);  							 //retorna el valor asociado con la clave k en el mapeo, o null si no hay
		int hk = hashKey(key);						 //una entrada con clave k en el mapeo
		Entry<K,V> toReturn = null;
		//para cada posicion p de la lista S hacer:
		for(Position<Entry<K,V>> p : a[hk].positions()) {
		//si la clave de la entrada actual es key:
			if(p.element().getKey().equals(key)) { 
				toReturn = p.element();
			}
		}
		//si sali del for-each, quiere decir que no encontre ninguna entrada con clave key
		return toReturn;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		checkKey(key);
		int hk = hashKey(key);
		PositionList<Entry<K,V>> e = new ListaDE<Entry<K,V>>();
		for(Entry<K, V> d : a[hk]) {
			if(d.getKey().equals(key))
			e.addLast(d);
		}
		return e;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		int hk = hashKey(key);
		Entry<K,V> e = new Entrada<K,V>(key,value);
		a[hk].addLast(e);
		n++;
		if((float)(n/N) >= FACTOR_CARGA) reHash();
		return e;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		try {
			checkEntry(e);
			int hk = hashKey(e.getKey());
			for(Position<Entry<K, V>> p : a[hk].positions()) {
				//si la clave de la entrada actual es key:
					if(p.element() == e) { 
						a[hk].remove(p);
						n--;
						return e;
					}
				}
		} catch (InvalidKeyException | InvalidPositionException e1) {	}
		throw new InvalidEntryException("No se encontro entrada");
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> entries = new ListaDE<Entry<K,V>>();
		for (PositionList<Entry<K, V>> d : a) {
            for(Entry<K,V> e : d) {
            	entries.addLast(e);
            }
        }
		return entries;
	}
	
	private void checkKey(K key) throws InvalidKeyException{
		if(key == null) {
			throw new InvalidKeyException("clave nula");
		}
	}
	private void checkEntry(Entry<K,V> e) throws InvalidEntryException{
		if (e == null) throw new InvalidEntryException("Entrada Nula");
	}

	private int hashKey(K key) throws InvalidKeyException {
		checkKey(key);
		return Math.abs(key.hashCode() % N);
	}
	private int nextPrime(int num){
		int candidato = n + 1; // Empezamos en el siguiente número después de n
	    while (true) { // Iteramos hasta encontrar el siguiente número primo
	        boolean esPrimo = true;
	        for (int i = 2; i <= Math.sqrt(candidato); i++) {
	            if (candidato % i == 0) { // Si encontramos un divisor, no es primo
	                esPrimo = false;
	                break;
	            }
	        }
	        if (esPrimo) { // Si es primo, lo devolvemos
	            return candidato;
	        }
	        candidato++; // Si no es primo, probamos con el siguiente número
	    }
	}
	
	private void reHash() throws InvalidKeyException {
		Iterable<Entry<K,V>> entries = this.entries();
		N = nextPrime(N*2);n = 0;
		a = new ListaDE[N];
		for(int i=0;i<N;i++) {
			a[i] = new ListaDE<Entry<K,V>>();
		}
		for(Entry<K,V> e : entries) {
			this.insert(e.getKey(), e.getValue());
		}
	}

}
