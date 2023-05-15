import Auxiliares.Position;
import Excepciones.AlumnoRepetidoException;
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
	public Par<Integer,Integer> getAlumnoLu(Integer lu){ //el tiempo de ejecucion es a lo sumo n donde n es la cantidad de alumnos en la lista
		Par<Integer,Integer> e = null;
		for(Position<Par<Integer, Integer>> p : listaAlumnos.positions()) {
			if(p.element().getValue().equals(lu)) {
				e = p.element();
				break;
			}
		}
		return e;
	}
	public Boolean eliminarAlumnoLu(Integer lu) {
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
	
	
	
	
	
	
	
}
