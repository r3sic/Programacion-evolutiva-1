package poblacion;

import cromosoma.Cromosoma;
import cromosoma.FactoriaCromosoma;

/**
 *
 * @author Carlos
 */
public class PoblacionTruncamiento<T> extends Poblacion<T> {

    private double _trunck;
    public PoblacionTruncamiento(int tam, String ejercicio, double precision, double trunk,double elitismo, int num_fen) {
        super(tam, ejercicio, precision,elitismo, num_fen);
        _trunck =trunk;
    }

    @Override
    public void seleccion() {
        int pobDistintas = (int) (Math.ceil(_trunck*_tam));
        double puntu = 0;
        int indice_mejores = 0;
        Cromosoma<T>[] padres = new Cromosoma[pobDistintas];
        
        for(int i = 0; i<_tam;i++){
            if(_pob[i].aptitud() > puntu || i < pobDistintas) {
            	padres[indice_mejores] = FactoriaCromosoma.getCromosomaCopia(_pob[i], _choice, _precision);
            	if(i+1 < pobDistintas)
                    indice_mejores++;
            	else {
                    indice_mejores = buscarMenor(pobDistintas, padres);
                    puntu = padres[indice_mejores].aptitud();	
            	}
            }
        }
        int j=0;
        for(int i = 0; i<_tam;i++){
            _pob[i] = FactoriaCromosoma.getCromosomaCopia(padres[j], _choice, _precision);
            j = (j+1)==pobDistintas?0:j+1;
        }
        
    }
    
}

