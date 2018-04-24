package poblacion;

import java.util.Random;

import cromosoma.Cromosoma;
import cromosoma.FactoriaCromosoma;
import cromosoma.Funciones;
import java.util.HashMap;

public abstract class Poblacion<T>{
	protected Cromosoma[] _pob;
	protected Cromosoma[] _mejores;
	protected int _num_mejores;
	protected int _pos_mejor;
	protected double[] _puntuacion;
	protected double _suma_aptitud;
	protected double[] _punt_acum;
	protected int _tam;
	protected String _choice;
	protected String _choice_mut;
	protected String _mensaje_cifrado;
	protected HashMap<String,Double> _map;
	private boolean _hayElitismo;
        
	public Poblacion(int tam, String cruce,double elitismo, String texto, String choice_mut) {
		_mensaje_cifrado = Funciones.leerTexto(texto);
                _mensaje_cifrado = _mensaje_cifrado.toLowerCase();
		_map = Funciones.leerDigram();
		_map.putAll(Funciones.leerTrigram());
		_choice = cruce;
		_choice_mut = choice_mut;
		_tam = tam;
		_num_mejores = (elitismo > 0.0)?(int) Math.ceil(_tam*elitismo): 1;
		_hayElitismo = (elitismo > 0.0);
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
			_pob[i] = FactoriaCromosoma.getCromosoma(_choice);
			apt = _pob[i].aptitud(_mensaje_cifrado,_map);
			if(apt > puntu|| i < _num_mejores) {
				_mejores[indice_mejores] = FactoriaCromosoma.getCromosomaCopia(_pob[i]);
				if(i+1 < _num_mejores)
					indice_mejores++;
				else {
					indice_mejores = buscarMenor(_num_mejores, _mejores);
					puntu = _mejores[indice_mejores].getaptitud();	
				}
			}
			_puntuacion[i] = apt;
			_suma_aptitud += apt;
			_punt_acum[i] = _suma_aptitud;
		}
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
                                        _pob[i].aptitud(_mensaje_cifrado, _map);
                                        _pob[aux].aptitud(_mensaje_cifrado, _map);
					eleccion = false;
				}
				else {
					aux = i;
					eleccion = true;
				}
			}		
	}

	public void mutacion(double prob) {
		for(int i = 0; i < _tam; i++)
			if(_pob[i].mutacion(prob, _choice_mut, _mensaje_cifrado, _map))
                            _pob[i].aptitud(_choice, _map);
                        
	}
	
	public void seleccionElitismo() {
		if(_hayElitismo) {
			for(int i = 0; i < _num_mejores; i++) {
				_pob[buscarMenor(_tam, _pob)] = FactoriaCromosoma.getCromosomaCopia(_mejores[i]);
			}
		}
		_suma_aptitud = 0;
		int indice_mejores = buscarMenor(_num_mejores, _mejores);
		double puntu = _mejores[indice_mejores].getaptitud();
		for(int i = 0; i < _tam; i++) {
			if(_pob[i].getaptitud() > puntu) {
				_mejores[indice_mejores] = FactoriaCromosoma.getCromosomaCopia(_pob[i]);
				indice_mejores = buscarMenor(_num_mejores, _mejores);
				puntu = _mejores[indice_mejores].aptitud(_mensaje_cifrado,_map);
			}
			//_pob[i].fenotipo(); //se calcula en los cambios del gen, ya no es necesario calcularlo siempre
			_puntuacion[i] = _pob[i].getaptitud();
			_suma_aptitud += _puntuacion[i];
			_punt_acum[i] = _suma_aptitud;
		}
	}
	
	public HashMap<Character, Character> getMejorFen() {
		return _pob[buscarMejor(_tam,_pob)].fenotipo();
	}
	public double getMejorApt() {
		return _pob[buscarMejor(_tam,_pob)].getaptitud();
	}
	protected int buscarMenor(int tam, Cromosoma[] array) {
		int menor_act = 0;
		for(int i = 1; i < tam;i++)
			if(array[i].getaptitud() < array[menor_act].getaptitud())
				menor_act = i;
		return menor_act;
	}
	private int buscarMejor(int tam, Cromosoma[] array) {
		int mejor_act = 0;
		for(int i = 1; i < tam;i++)
			if(array[i].getaptitud() > array[mejor_act].getaptitud())
				mejor_act = i;
		return mejor_act;
	}

	public double media() {
		// TODO Auto-generated method stub
		return _suma_aptitud/_tam;
	}

	public String get_mensaje_cifrado() {
		return _mensaje_cifrado;
	}
}