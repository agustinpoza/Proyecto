import Auxiliares.Entry;
import Auxiliares.Position;
import Excepciones.EmptyPriorityQueueException;
import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;
import TDAColaCP.Comparador;
import TDAColaCP.Heap;
import TDAColaCP.PriorityQueue;
import TDADiccionario.Dictionary;
import TDADiccionario.DiccionarioHash;
import TDALista.PositionList;
import TDALista.ListaDE;

public class Programa{
	protected String Materia;
	protected PositionList<Par<Integer, Integer>> listaAlumnos;
	
	
	public Programa(){listaAlumnos = new ListaDE();}
	
	public void setMateria(String s) {
		this.Materia = s;
	}
	public String getMateria() {return this.Materia;}
	
	public Boolean setAlumno(Par<Integer,Integer> e){
		boolean cumplio = false;
		if(this.getAlumnoLu(e.getValue()) == null) {
			listaAlumnos.addLast(e);
			cumplio = true;
		}
		return cumplio;
	}
	public Par<Integer,Integer> getAlumnoLu(int lu){ //el tiempo de ejecucion es a lo sumo n donde n es la cantidad de alumnos en la lista
		Par<Integer,Integer> e = null;
		for(Position<Par<Integer, Integer>> p : listaAlumnos.positions()) {
			if(p.element().getValue().equals(lu)) {
				e = p.element();
				break;
			}
		}
		return e;
	}
	
	public Boolean eliminarAlumnoLu(int lu) { // se cambio Integer por int para mas eficiencia
		boolean cumplio = false;
		for(Position<Par<Integer, Integer>> p : listaAlumnos.positions()) {
			if(p.element().getValue().equals(lu)) {
				try {
					listaAlumnos.remove(p);
					cumplio = true;
				} catch (InvalidPositionException e1) {}
				break;
			}
		}
		return cumplio;
	}
	
	public Boolean eliminarAlumno(Par<Integer,Integer> e) {
		boolean cumplio = false;
		for(Position<Par<Integer, Integer>> p : listaAlumnos.positions()) {
			if(p.element().equals(e)) {
				try {
					listaAlumnos.remove(p);
					cumplio = true;
				} catch (InvalidPositionException e1) {}
				break;
			}
		}
		return cumplio;
	}


	public PositionList<Par<Integer, Integer>> getAlumnosAprobados() {
		PositionList<Par<Integer, Integer>> aprobados = new ListaDE<>();
	    for (Par<Integer, Integer> alumno : listaAlumnos) {
	        if (alumno.getKey() >= 60) {
	            aprobados.addLast(alumno);
	        }
	    }
	    return aprobados;
	}
	public int calcularPromedio() {
		int promedio = 0;
		for(Position<Par<Integer, Integer>> p : listaAlumnos.positions()) {
			promedio = promedio + p.element().getKey();
		}
		return promedio/listaAlumnos.size();

	}
	public PositionList<Par<Integer, Integer>> getAlumnosDesaprobados() {
		PositionList<Par<Integer, Integer>> desaprobados = new ListaDE<>();
	    for (Par<Integer, Integer> alumno : listaAlumnos) {
	        if (alumno.getKey() < 60) {
	            desaprobados.addLast(alumno);
	        }
	    }
	    return desaprobados;
	}
	public Iterable<Entry<Integer,Integer>> alumnosConNota(int nota) {
		Dictionary<Integer,Integer> d = new DiccionarioHash<Integer,Integer>();
		Iterable<Entry<Integer,Integer>> e = null;
		for(Position<Par<Integer, Integer>> p : listaAlumnos.positions()) {
			try {
				d.insert(p.element().getKey(), p.element().getValue());
			} catch (InvalidKeyException e2) {}
		}
		try {
			e = d.findAll(nota);
		} catch (InvalidKeyException e1) {}
		
		return e;
	}
	
	public int NotaMinima() {
		PriorityQueue<Integer,Integer> pq = new Heap<Integer,Integer>(new Comparador<Integer>());
		int toReturn = 0;
		for(Position<Par<Integer,Integer>> p : listaAlumnos.positions()) {
			try {
				pq.insert(p.element().getKey(), p.element().getValue());
			} catch (InvalidKeyException e) {}
		}
		try {
			toReturn = pq.min().getKey();
		} catch (EmptyPriorityQueueException e) {}
		
		return toReturn;
	}
	
	public PositionList<Entry<Integer,Integer>> NotaMaxima() {
		PriorityQueue<Integer,Integer> pq = new Heap<Integer,Integer>(new Comparador2<Integer>());
		PositionList<Entry<Integer,Integer>> pl = new ListaDE<Entry<Integer,Integer>>();
		for(Position<Par<Integer,Integer>> p : listaAlumnos.positions()) {
			try {//la cantidad maxima de alumnos es 1000 fijarse cla clase heap construcotor
				pq.insert(p.element().getKey(), p.element().getValue());
			} catch (InvalidKeyException e2) {}
		}
		try {
			while(pq.size() != 0) {
				pl.addLast( pq.removeMin());
			}
		} catch (EmptyPriorityQueueException e1) {} 
		
		return pl;
	}
	
	private class Comparador2<E> implements java.util.Comparator<E> {

		@Override
		public int compare(E o1, E o2) {
			
			return (((Comparable<E>) o1).compareTo(o2))*-1;
		}
	}

}