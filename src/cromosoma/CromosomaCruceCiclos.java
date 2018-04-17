/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cromosoma;

import java.util.HashSet;

/**
 *
 * @author Carlos
 */
public class CromosomaCruceCiclos extends Cromosoma<Integer>{

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
        int newCrom1[] = new int[_longitud];
        int newCrom2[] = new int[_longitud];
        
        HashSet<Integer> setcrom1 = new HashSet<>();
        HashSet<Integer> setcrom2 = new HashSet<>();
        
        newCrom2[0] = (int) c2._genes[0];
        setcrom1.add((Integer) c2._genes[0]);
        
        
        int i=0;
        boolean ciclo = false;
        while(!ciclo){
            newCrom1[i] = _genes[i];
            setcrom1.add(_genes[i]);
            int val = (Integer) c2._genes[i];
            ciclo = setcrom1.contains(val);
            int j =0;
            boolean encontrado = ciclo;
            while(j<_longitud && !encontrado){
                encontrado=_genes[j]==val;
                i=j;
                j++;                
            }            
        }
        
        i=0;
        ciclo = false;
        while(!ciclo){
            newCrom2[i] = (Integer) c2._genes[i];
            setcrom2.add((Integer) c2._genes[i]);
            int val = _genes[i];
            ciclo = setcrom2.contains(val);
            int j =0;
            boolean encontrado = ciclo;
            while(j<_longitud && !encontrado){
                encontrado=(Integer)c2._genes[j]==val;
                i=j;
                j++;                
            }            
        }
        
        // fin inicializacion
        
        //cruce
        for(int j =0;j<_longitud;j++){
            if(!setcrom1.contains(newCrom1[i]))
                newCrom1[i]=(int) c2._genes[i];
            if(!setcrom2.contains(newCrom2[i]))
                newCrom2[i]=_genes[i];
        }
        
    }
    
}
