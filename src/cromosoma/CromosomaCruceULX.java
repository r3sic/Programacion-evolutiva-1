/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cromosoma;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class CromosomaCruceULX extends Cromosoma{

    public CromosomaCruceULX(){
        super();
        _tipoCrom = "ULX";
    }
    
    public CromosomaCruceULX(Cromosoma c2){
        super(c2);
        _tipoCrom = "ULX";
    }
    private void cruceesp(CromosomaCruceULX c2) {
        int[] newCrom1 = new int[_longitud];
        int[] newCrom2 = new int[_longitud];
        HashSet<Integer> elemcrom1 = new HashSet<Integer>();
        HashSet<Integer> elemcrom2 = new HashSet<Integer>();
        //añado los elementos comunes
        for(int i = 0;i<_longitud;i++){
            if(_genes[i]==c2._genes[i]){
                newCrom1[i]=_genes[i];
                newCrom2[i]=c2._genes[i];
            }else{
                elemcrom1.add(_genes[i]);
                elemcrom2.add(c2._genes[i]);
            }
        }
        cruceULX(elemcrom1,newCrom1,c2);
        c2.cruceULX(elemcrom2,newCrom2,this);
        _genes=newCrom1;
        c2._genes=newCrom2;
        
    }
    
    private void cruceULX(HashSet<Integer> elemcrom, int[] newCrom1, Cromosoma c2){
        boolean libre1,libre2;
        for(int i =0;i<_longitud;i++){
            
            libre1=elemcrom.contains(_genes[i]);
            libre2=elemcrom.contains(c2._genes[i]);
            if(libre1&&libre2){ //cierto ambos se coge uno de los dos al azar
                Random r = new Random();
                if(r.nextDouble()>0.5)
                    newCrom1[i]=_genes[i];
                else
                    newCrom1[i]=c2._genes[i]; 
                
            }else if (libre1) // solo se puede coger uno de los dos
                newCrom1[i]=_genes[i];
            else if (libre2)
                newCrom1[i]=c2._genes[i];
            // no se puede coger ninguno
            else if (_genes[i] != c2._genes[i]){
                Iterator<Integer> it = elemcrom.iterator();
                newCrom1[i]=it.next();// se le da uno cualquiera, hashset no esta ordenado asi que sera como un random
            }             
            if (_genes[i] != c2._genes[i])
                elemcrom.remove(newCrom1[i]);
        }
    }

    @Override
    public void cruce(Cromosoma c2) {
        cruceesp((CromosomaCruceULX) c2);
    }
}
