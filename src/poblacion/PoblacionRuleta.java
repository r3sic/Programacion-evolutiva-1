package poblacion;

import java.util.Random;

import cromosoma.Cromosoma;
import cromosoma.FactoriaCromosoma;

public class PoblacionRuleta<T> extends Poblacion<T>{

	public PoblacionRuleta(int tam, String ejercicio,double elitismo, String texto, String choice_mut) {
		super(tam, ejercicio,elitismo, texto, choice_mut);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void seleccion() {
		/*Cromosoma[] pob_aux = new Cromosoma[_tam];
		Random r = new Random();
		double seleccionado;
		int pos;
		for(int i = 0; i < _tam; i++) {
			seleccionado = r.nextDouble()*_suma_aptitud;
			pos = _tam-1;
			boolean encontrado = false;
			while(pos >= 0 && encontrado) {
				if(seleccionado < _punt_acum[pos])
					encontrado = true;
				else pos--;
			}
			pob_aux[i] = FactoriaCromosoma.getCromosomaCopia(_pob[pos]);
		}
		_pob = pob_aux;
	}*/
	Cromosoma[] pob_aux = new Cromosoma[_tam];
	Random r = new Random();
	double seleccionado;
	int pos;
	double traslacion_acum = 0;
    double traslacion_punt[] = new double[_tam];
	int minimo = buscarMenor(_tam, _pob);
    if (_pob[minimo].aptitud(_mensaje_cifrado, _map) < 0){
    	traslacion_punt[0] = _pob[0].aptitud(_mensaje_cifrado, _map) + - _pob[minimo].aptitud(_mensaje_cifrado, _map);
    	for(int i = 1; i < _tam; i++) {
     		traslacion_punt[i] = traslacion_punt[i-1]+_pob[i].aptitud(_mensaje_cifrado, _map) - _pob[minimo].aptitud(_mensaje_cifrado, _map);
     	}
     	traslacion_acum = traslacion_punt[_tam-1];
		for(int i = 0; i < _tam; i++) {
			seleccionado = r.nextDouble()*traslacion_acum;
			pos = 0;
			boolean encontrado = false;
			while(pos < _tam && !encontrado) {
				if(seleccionado <= traslacion_punt[pos])
					encontrado = true;
				else pos++;
			}
			pob_aux[i] = FactoriaCromosoma.getCromosomaCopia(_pob[pos]);
		}
    }
    else {
		for(int i = 0; i < _tam; i++) {
			seleccionado = r.nextDouble()*_suma_aptitud;
			pos = 0;
			boolean encontrado = false;
			while(pos < _tam && !encontrado) {
				if(seleccionado <= _punt_acum[pos])
					encontrado = true;
				else pos++;
			}
			pob_aux[i] = FactoriaCromosoma.getCromosomaCopia(_pob[pos]);
		}
	}
	System.arraycopy(pob_aux, 0, _pob, 0, _tam);
}

}
