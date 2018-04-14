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
public class CromosomaCrucePMX extends Cromosoma<Integer>{

    @Override
    protected void generaAleatorio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mutacion(double prob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cruce(Cromosoma c2) {
        Random r = new Random();
        int ini = r.nextInt(_longitud-1);
        int fin = r.nextInt(_longitud-ini)+ini;
        int newCrom1[] = new int[_longitud];
        int newCrom2[] = new int[_longitud];
        java.lang.System.arraycopy(newCrom1, ini, c2._genes, ini, fin-ini+1);
        java.lang.System.arraycopy(newCrom2, ini, _genes, ini, fin-ini+1);
        HashSet<Integer> crom1 = new HashSet<Integer>();
        HashSet<Integer> crom2 = new HashSet<Integer>();
        for (int i =ini;i<=fin;i++){
            crom1.add(newCrom1[i]);
            crom2.add(newCrom2[i]);
        }
        for(int i =0;i<_longitud;i++){
            if(!crom1.contains(_genes[i]))
                newCrom1[i]=_genes[i];
            else
                newCrom1[i]=(int) c2._genes[i];
            
            if(!crom2.contains(c2._genes[i]))
                newCrom1[i]= (int) c2._genes[i];
            else
                newCrom1[i]=_genes[i];
        }
    }
    
}
