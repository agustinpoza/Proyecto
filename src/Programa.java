import java.util.Iterator;

import Auxiliares.Position;
import Excepciones.InvalidPositionException;
import TDADiccionario.Dictionary;
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
	public PositionList<Par<Integer, Integer>> getAlumnosDesaprobados() {
		PositionList<Par<Integer, Integer>> desaprobados = new ListaDE<>();
	    for (Par<Integer, Integer> alumno : listaAlumnos) {
	        if (alumno.getKey() < 60) {
	            desaprobados.addLast(alumno);
	        }
	    }
	    return desaprobados;
	}
	

}