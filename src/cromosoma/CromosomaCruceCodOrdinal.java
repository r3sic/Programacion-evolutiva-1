/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cromosoma;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class CromosomaCruceCodOrdinal extends Cromosoma{

    public CromosomaCruceCodOrdinal(){
        super();
        _tipoCrom = "CODORDINAL";
    }

    public CromosomaCruceCodOrdinal(Cromosoma c) {
        super(c);
        _tipoCrom = "CODORDINAL";
    }
    
    
    private void cruceesp(CromosomaCruceCodOrdinal c2){
        int[] cifrado1 = this.cifrar();
        int[] cifrado2 = c2.cifrar();
        Random r = new Random();
        int punto = r.nextInt(_longitud-2)+1;
        
        int[] aux1 = new int[_longitud];
        int[] aux2 = new int[_longitud];
        
        System.arraycopy(cifrado1, 0, aux1, 0, punto);
        System.arraycopy(cifrado2, punto, aux1, punto, _longitud-punto);
        System.arraycopy(cifrado1, 0, aux2, 0, punto);
        System.arraycopy(cifrado2, punto, aux2, punto, _longitud-punto);
        _genes=this.descifrar(aux1);
        c2._genes=c2.descifrar(aux2);
    }
    
    
    
    
    
    
    
    @Override
    public void cruce(Cromosoma c2) {
        cruceesp((CromosomaCruceCodOrdinal) c2);        
    }
    
    private ArrayList<Integer> inicializar(){
        ArrayList<Integer> lista = new ArrayList();
        for(int i = 0; i<_longitud;i++){
            lista.add(i);
        }
        return lista;
    }
    
    private int[] cifrar(){
        int[] cifrado = new int[_longitud];
        ArrayList<Integer> lista = inicializar();
        for(int i = 0; i< _longitud;i++){
            boolean encontrado = false;
            int j=0;
            Iterator<Integer> it = lista.iterator();
            while(!encontrado){
                if(0 ==  it.next().compareTo(_genes[i])){
                    cifrado[i]=j;
                    it.remove();
                    encontrado=true;
                }
                j++;
            }
            
        }
        return cifrado;
    }
    
    private int[] descifrar(int[] array){
        int[] cifrado = new int[_longitud];
        ArrayList<Integer> lista = inicializar();
        for(int i = 0; i< _longitud;i++){
            cifrado[i]=lista.get(array[i]);
            lista.remove(array[i]);
        }
        return cifrado;
    }
}
