package poblacion;

import java.util.Random;

import cromosoma.Cromosoma;
import cromosoma.FactoriaCromosoma;

public abstract class Poblacion<T>{
	protected Cromosoma<T>[] _pob;
	protected Cromosoma<T>[] _mejores;
	protected int _num_mejores;
	protected int _pos_mejor;
	protected double[] _puntuacion;
	protected double _suma_aptitud;
	protected double[] _punt_acum;
	protected int _tam;
	protected String _choice;
	protected double _precision;
	
	public Poblacion(int tam, String ejercicio, double precision, int num_fen) {
		_choice = ejercicio;
		_precision = precision;
		_tam = tam;
		_num_mejores = (int) Math.ceil((_tam/100.0)*2.0);
		_suma_aptitud = 0;
		_pob = new Cromosoma[_tam];
		_mejores = new Cromosoma[_num_mejores];
		_puntuacion = new double[_tam];
		_punt_acum = new double[_tam];
		double puntu = 0;
		double apt;
		int indice_mejores = 0;
		//generar poblaciones
		for(int i = 0; i < _tam;i++) {
			_pob[i] = FactoriaCromosoma.getCromosoma(_choice,_precision, num_fen);
			apt = _pob[i].aptitud();
			if(apt > puntu) {
				_mejores[indice_mejores] = FactoriaCromosoma.getCromosomaCopia(_pob[i],_choice,_precision);
				if(i+1 < _num_mejores)
					indice_mejores++;
				else {
					indice_mejores = buscarMenor(_num_mejores, _mejores);
					puntu = _mejores[indice_mejores].aptitud();	
				}
			}
			_puntuacion[i] = apt;
			_suma_aptitud += apt;
			_punt_acum[i] = _suma_aptitud;
		}
		_pos_mejor = buscarMejorTotal();
	}

	public abstract void seleccion(); 
	
	public void cruce(double prob) {
		int aux = 0;
		boolean eleccion = false;
		Random r = new Random();
		for(int i = 0; i < _tam; i++) 
			if(r.nextDouble() < prob) {
				if(eleccion) {
					_pob[i].cruce(_pob[aux]);
					eleccion = false;
				}
				else {
					aux = i;
					eleccion = true;
				}
			}		
	}

	public void mutacion(double prob) {
		for(int i = 0; i < _tam; i++) {
			_pob[i].mutacion(prob);
		}
		for(int i = 0; i < _num_mejores; i++) {
			_pob[buscarMenor(_tam, _pob)] = _mejores[i];
		}
		_suma_aptitud = 0;
		int indice_mejores = buscarMenor(_num_mejores, _mejores);
		double puntu = _mejores[indice_mejores].aptitud();
		for(int i = 0; i < _tam; i++) {
			if(_pob[i].aptitud() > puntu) {
				_mejores[indice_mejores] = FactoriaCromosoma.getCromosomaCopia(_pob[i],_choice,_precision);;
				indice_mejores = buscarMenor(_num_mejores, _mejores);
				puntu = _mejores[indice_mejores].aptitud();
			}
			_puntuacion[i] = _pob[i].aptitud();
			_suma_aptitud += _puntuacion[i];
			_punt_acum[i] = _suma_aptitud;
		}
		_pos_mejor = buscarMejorTotal();
	}
	
	public double[] getMejorFen() {
		return _mejores[_pos_mejor].fenotipo();
	}
	public double getMejorApt() {
		return _mejores[_pos_mejor].aptitud();
	}
	protected int buscarMenor(int tam, Cromosoma[] array) {
		int menor_act = 0;
		for(int i = 1; i < tam;i++)
			if(array[i].aptitud() < array[menor_act].aptitud())
				menor_act = i;
		return menor_act;
	}
	private int buscarMejorTotal() {
		int mejor_act = 0;
		for(int i = 1; i < _num_mejores;i++)
			if(_mejores[i].aptitud() > _mejores[mejor_act].aptitud())
				mejor_act = i;
		return mejor_act;
	}

	public double media() {
		// TODO Auto-generated method stub
		return _suma_aptitud/_tam;
	}
}