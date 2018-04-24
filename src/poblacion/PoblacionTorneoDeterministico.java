package poblacion;


import java.util.Random;

import cromosoma.Cromosoma;
import cromosoma.FactoriaCromosoma;


/**
 *
 * @author Carlos
 */
public class PoblacionTorneoDeterministico<T> extends Poblacion<T> {

    
    public PoblacionTorneoDeterministico(int tam, String ejercicio,double elitismo, String texto, String choice_mut) {
        super(tam, ejercicio, elitismo, texto,choice_mut);
    }

    @Override
    public void seleccion() {
        Cromosoma[] padres = new Cromosoma[_tam];
        Cromosoma mejor;
        Random r = new Random();
        int aux;
        for(int i =0; i<_tam; i++){
            do{
                aux = r.nextInt(_tam);
            }while(aux!=i);
            mejor = (_pob[i].aptitud(_mensaje_cifrado,_map)>_pob[aux].aptitud(_mensaje_cifrado, _map))?_pob[i]:_pob[aux];
            padres[i] = FactoriaCromosoma.getCromosomaCopia(mejor);
        }
        System.arraycopy(padres, 0, _pob, 0, _tam);
    }
    
}
