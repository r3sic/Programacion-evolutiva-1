package poblacion;

import java.util.Random;

import cromosoma.Cromosoma;
import cromosoma.FactoriaCromosoma;

public class PoblacionUniversalEstocastica<T> extends Poblacion<T>{

	public PoblacionUniversalEstocastica(int tam, String ejercicio,double elitismo, String texto, String choice_mut) {
		super(tam, ejercicio, elitismo, texto, choice_mut);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void seleccion() {
		Random r = new Random();
        double dist = 1.0/_tam;
        double marca = r.nextDouble() * dist;
        //marca = marca + dist*(_tam-1);
        
        double traslacion_acum = 0;
        double traslacion_punt[] = new double[_tam];
        int j=0;
        Cromosoma[] pob_aux = new Cromosoma[_tam];
        boolean ent = false;
        int minimo = buscarMenor(_tam, _pob);
        if (_pob[minimo].aptitud(_mensaje_cifrado, _map) < 0){
        	traslacion_punt[0] = _pob[0].aptitud(_mensaje_cifrado, _map) + - _pob[minimo].aptitud(_mensaje_cifrado, _map);
        	for(int i = 1; i < _tam; i++) {
        		traslacion_punt[i] = traslacion_punt[i-1]+_pob[i].aptitud(_mensaje_cifrado, _map) - _pob[minimo].aptitud(_mensaje_cifrado, _map);
        	}
        	traslacion_acum = traslacion_punt[_tam-1];
            for (int i = 0; i<_tam; i++){
                ent = false;
                while (j<_tam && !ent){
                    
                    if (traslacion_punt[j]/traslacion_acum >= marca){
                        if(j==0 || traslacion_punt[j-1]/traslacion_acum < marca){
                        	pob_aux[i] = FactoriaCromosoma.getCromosomaCopia(_pob[j]);
                            marca = Math.min(1,marca + dist);
                            j--;
                            ent= true;
                        }
                    }
                    j++;
                }
                                
            }
        }
        else {
            for (int i = 0; i<_tam; i++){
                ent = false;
                while (j<_tam && !ent){
                    
                    if (_punt_acum[j]/_suma_aptitud >= marca){
                        if(j==0 || _punt_acum[j-1]/_suma_aptitud < marca){
                            pob_aux[i] = FactoriaCromosoma.getCromosomaCopia(_pob[j]);
                            marca = Math.min(1,marca + dist);
                            j--;
                            ent= true;
                        }
                    }
                    j++;
                }
                                
            }
        }
        System.arraycopy(pob_aux, 0, _pob, 0, _tam);
}
  /*          Random r = new Random();
            double dist = 1.0/_tam;
            double marca = r.nextDouble() * dist;
            //marca = marca + dist*(_tam-1);
            
            int j=0;
            Cromosoma[] pob_aux = new Cromosoma[_tam];
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
*/
}
