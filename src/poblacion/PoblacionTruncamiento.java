package poblacion;

import cromosoma.Cromosoma;
import cromosoma.FactoriaCromosoma;

/**
 *
 * @author Carlos
 */
public class PoblacionTruncamiento<T> extends Poblacion<T> {

    private double _trunck;
    public PoblacionTruncamiento(int tam, String ejercicio, double elitismo, double trunk, String texto, String choice_mut) {
        super(tam, ejercicio,elitismo, texto, choice_mut);
        _trunck =trunk;
    }

    @Override
    public void seleccion() {
        int pobDistintas = (int) (_trunck*_tam);
        double puntu = 0;
        int indice_mejores = 0;
        Cromosoma[] padres = new Cromosoma[pobDistintas];
        for(int i = 0; i<_tam;i++){
            if(_pob[i].aptitud(_mensaje_cifrado,_map) > puntu || i < pobDistintas) {
            		padres[indice_mejores] = FactoriaCromosoma.getCromosomaCopia(_pob[i]);
		if(i+1 < pobDistintas)
                    indice_mejores++;
		else {
                    indice_mejores = buscarMenor(pobDistintas, padres);
                    puntu = padres[indice_mejores].aptitud(_mensaje_cifrado,_map);	
		}
            }
        }
        int j=0;
        for(int i = 0; i<_tam;i++){
            _pob[i] = FactoriaCromosoma.getCromosomaCopia(padres[j]);
            j = (j+1)==pobDistintas?0:j+1;
        }
        
    }
    
}

