/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cromosoma;

import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class CromosomaCruceOX extends Cromosoma{

    

    public CromosomaCruceOX(){
        super();
    }
    
    
    @Override
    public void cruce(Cromosoma c2) {
        
        //inicializar
        Random r = new Random();
        int ini = r.nextInt(_longitud-1);
        int fin = r.nextInt(_longitud-ini)+ini;
        int newCrom1[] = new int[_longitud];
        int newCrom2[] = new int[_longitud];        
        java.lang.System.arraycopy(c2._genes, ini, newCrom1, ini, fin-ini+1);
        java.lang.System.arraycopy(_genes , ini, newCrom2, ini, fin-ini+1);
        HashSet<Integer> setcrom1 = new HashSet<Integer>();
        HashSet<Integer> setcrom2 = new HashSet<Integer>();
        for (int i =ini;i<=fin;i++){
            setcrom1.add(newCrom1[i]);
            setcrom2.add(newCrom2[i]);
        }
        
        //cruce cromosoma 1
        int j= fin==_longitud-1?0:fin+1;
        int i= fin==_longitud-1?0:fin+1;
        while(j!=ini){
            if(!setcrom1.contains(_genes[i])){
                newCrom1[j]=_genes[i];
                j = (j+1==_longitud)?0:j+1;//sumar al contador y dar la vuelta si es necesario
            }
            i = (i+1==_longitud)?0:i+1;//sumar al contador y dar la vuelta si es necesario
        }
        
        // cruce cromosoma 2
        j= fin==_longitud-1?0:fin+1;
        i= fin==_longitud-1?0:fin+1;
        while(j!=ini){
            if(!setcrom2.contains(c2._genes[i])){
                newCrom2[j]=c2._genes[i];
                j = (j+1==_longitud)?0:j+1;//sumar al contador y dar la vuelta si es necesario
            }
            i = (i+1==_longitud)?0:i+1;//sumar al contador y dar la vuelta si es necesario
        }
        
        
        _genes = newCrom1;
        c2._genes = newCrom2;
    }
    
    
    
}
