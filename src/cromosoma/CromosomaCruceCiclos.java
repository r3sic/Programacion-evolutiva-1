/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cromosoma;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Carlos
 */
public class CromosomaCruceCiclos extends Cromosoma{

    public CromosomaCruceCiclos(){
        super();
        _tipoCrom = "CICLOS";
    }
    
    public CromosomaCruceCiclos(Cromosoma c){
        super(c);
        _tipoCrom = "CICLOS";
    }
    @Override
    public void cruce(Cromosoma c2) {
        int newCrom1[] = new int[_longitud];
        int newCrom2[] = new int[_longitud];
        
        HashSet<Integer> setcrom1 = new HashSet<>();
        HashSet<Integer> setcrom2 = new HashSet<>();

        int i=0;
        boolean ciclo = false;
        while(!ciclo){
            newCrom1[i] = _genes[i];
            newCrom2[i] = c2._genes[i];
            ciclo = setcrom1.contains(_genes[i]);
            setcrom1.add(_genes[i]);
            int val = c2._genes[i];
            int j =0;
            boolean encontrado = ciclo;
            while(j<_longitud && !encontrado){
                encontrado=_genes[j]==val;
                i=j;
                j++;                
            }            
        }
        
        
        // fin inicializacion
        
        //cruce
        for(int j =0;j<_longitud;j++){
            if(!setcrom1.contains(_genes[j]))
                newCrom1[j]=c2._genes[j];            
            if(!setcrom2.contains(c2._genes[j]))
                newCrom2[j]=_genes[j];
            
        }
        
        _genes = newCrom1;
        c2._genes = newCrom2;
        fenotipo();
        c2.fenotipo();
        
        
    }
    
}
