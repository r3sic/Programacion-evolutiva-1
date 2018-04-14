package poblacion;

import java.util.Random;

import cromosoma.Cromosoma;

public class PoblacionUniversalEstocastica<T> extends Poblacion<T>{

	public PoblacionUniversalEstocastica(int tam, String ejercicio, double precision, int num_fen) {
		super(tam, ejercicio, precision, num_fen);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void seleccion() {
            
            Random r = new Random();
            double dist = 1.0/_tam;
            double marca = r.nextDouble() * dist;
            //marca = marca + dist*(_tam-1);
            
            int j=0;
            Cromosoma<T>[] pob_aux = new Cromosoma[_tam];
            boolean ent = false;
            for (int i = 0; i<_tam; i++){
                ent = false;
                while (j<_tam && !ent){
                    
                    if (_punt_acum[j]/_suma_aptitud >= marca){
                        if(j==0 || _punt_acum[j-1]/_suma_aptitud < marca){
                            pob_aux[i] = _pob[j];
                            marca = Math.min(1,marca + dist);
                            j--;
                            ent= true;
                        }
                    }
                    j++;
                }
                                
            }
            
            _pob = pob_aux;
	}

}
