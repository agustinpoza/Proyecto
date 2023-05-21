import Auxiliares.Entry;
import Auxiliares.Par;
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
	
	/**
	 * inicializamos una lista de alumnos
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Programa(){listaAlumnos = new ListaDE();}
	
	/**
	 * seteamos la materia en la que estamos
	 * @param materia
	 */
	public void setMateria(String s) {
		this.Materia = s;
	}
	/**
	 * consultamos la materia en la que estamos
	 * @return materia
	 */
	public String getMateria() {return this.Materia;}
	
	/**
	 * si el alumno pasado por parametro no esta en la lista lo agregamos
	 * @param alumno
	 * @return si el alumno ya estaba en la lista nos retorna false
	 */
	public Boolean setAlumno(Par<Integer,Integer> e){
		boolean cumplio = false;
		if(this.getAlumnoLu(e.getValue()) == null) {
			listaAlumnos.addLast(e);
			cumplio = true;
		}
		return cumplio;
	}
	
	/**
	 * buscamos un alumno por su lu
	 * @param lu del alumno
	 * @return el alumno con el lu, de lo contrario si no se encuentra, devolvemos null
	 */
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
	/**
	 * eliminamos el alumno con el lu pasado como parametro
	 * @param lu del alumno
	 * @return true si el alumno se encontro, false si el alumno no estaba 
	 */
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
	
	/**public Boolean eliminarAlumno(Par<Integer,Integer> e) {
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
	}*/

	
	public PositionList<Par<Integer, Integer>> getAlumnosAprobados() {
		PositionList<Par<Integer, Integer>> aprobados = new ListaDE<>();
	    for (Par<Integer, Integer> alumno : listaAlumnos) {
	        if (alumno.getKey() >= 60) {
	            aprobados.addLast(alumno);
	        }
	    }
	    return aprobados;
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
	
	/**
	 * calculamos el promedio de todos los alumnos en la lista
	 * @return promedio redondeado a entero
	 */
	public int calcularPromedio() {
		int promedio = 0;
		for(Position<Par<Integer, Integer>> p : listaAlumnos.positions()) {
			promedio = promedio + p.element().getKey();
		}
		return promedio/listaAlumnos.size();

	}
	
	/**
	 * consultamos todos los alumnos de la lista con la nota pasada como parametro
	 * @param nota
	 * @return iterable con los alumnos que tienen la nota
	 */
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
	
	/**
	 * consultamos cual es la nota minima entre todos los alumnos de la lista
	 * @return nota minima
	 */
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
	
	/**
	 * consultamos las notas de los alumnos de mayor a menor
	 * @return una lista con las notas de los alumnos de mayor a menor
	 */
	public PositionList<Entry<Integer,Integer>> NotaMayorMenor() {
		PriorityQueue<Integer,Integer> pq = new Heap<Integer,Integer>(new Comparador2<Integer>());
		PositionList<Entry<Integer,Integer>> pl = new ListaDE<Entry<Integer,Integer>>();
		for(Position<Par<Integer,Integer>> p : listaAlumnos.positions()) {
			try {//la cantidad maxima de alumnos es 1000 fijarse cla clase heap construcotor
				pq.insert(p.element().getKey(), p.element().getValue());
			} catch (InvalidKeyException e2) {}
		}
		try {
			while(pq.size() != 0) {
				pl.addLast(pq.removeMin());
			}
		} catch (EmptyPriorityQueueException e1) {} 
		
		return pl;
	}

	/**
	 *	clase auxiliar para crear un comparador que usaremos en el metodo NotaMayorMenor
	 */
	private class Comparador2<E> implements java.util.Comparator<E> {

		@SuppressWarnings("unchecked")
		/**
		 * compara dos objetos 
		 * @param objeto 1
		 * @param objeto 2
		 * @return un entero positivo si el primer argumento es menor al segundo, un entero negativo si es mayor y cero si son iguales
		 */
		public int compare(E o1, E o2) {
			return (((Comparable<E>) o1).compareTo(o2))*-1;
		}
	}

}