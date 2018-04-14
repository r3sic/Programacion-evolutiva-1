package poblacion;


import java.util.Random;

import cromosoma.Cromosoma;
import cromosoma.FactoriaCromosoma;


/**
 *
 * @author Carlos
 */
public class PoblacionTorneoDeterministico<T> extends Poblacion<T> {

    
    public PoblacionTorneoDeterministico(int tam, String ejercicio, double precision, int num_fen) {
        super(tam, ejercicio, precision, num_fen);
    }

    @Override
    public void seleccion() {
        Cromosoma<T>[] padres = new Cromosoma[_tam];
        Cromosoma<T> mejor;
        Random r = new Random();
        int aux;
        for(int i =0; i<_tam; i++){
            do{
                aux = r.nextInt(_tam);
            }while(aux!=i);
            mejor = (_pob[i].aptitud()>_pob[aux].aptitud())?_pob[i]:_pob[aux];
            padres[i] = FactoriaCromosoma.getCromosomaCopia(mejor, _choice, _precision);
        }
        _pob = padres;
    }
    
}
