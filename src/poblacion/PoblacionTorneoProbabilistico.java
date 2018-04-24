package poblacion;


import java.util.Random;

import cromosoma.Cromosoma;
import cromosoma.FactoriaCromosoma;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carlos
 */
public class PoblacionTorneoProbabilistico<T> extends Poblacion<T> {

    private double _prob;
    public PoblacionTorneoProbabilistico(int tam, String ejercicio,double elitismo, double trunk, int num_fen, String texto, String choice_mut) {
        super(tam, ejercicio, elitismo, texto,choice_mut);
        _prob = trunk;
    }

    @Override
    public void seleccion() {
        Cromosoma[] padres = new Cromosoma[_tam];
        Cromosoma mejor;
        Random r = new Random();
        int aux;
        double ran;
        for(int i =0; i<_tam; i++){
            do{
                aux = r.nextInt(_tam);
            }while(aux!=i);
            ran = r.nextDouble();
            mejor = (ran<=_prob)?_pob[i]:_pob[aux];
            padres[i] = FactoriaCromosoma.getCromosomaCopia(mejor);
        }
        System.arraycopy(padres, 0, _pob, 0, _tam);
    }
    
}
