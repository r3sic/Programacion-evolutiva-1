import java.util.HashMap;

import cromosoma.CromosomaCruceCiclos;
import cromosoma.Funciones;
import vista.Vista;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
                CromosomaCruceCiclos a = new CromosomaCruceCiclos();
                a.mutacion(0.6, "INSERCION", "", null);
		/*int gen = 100;
		Solucion sol = new Solucion(gen);
		Vista p = new Vista();
		p.setSize(600, 600);
		p.setVisible(true);
		AGenetico algoritmo = new AGenetico(gen, 0.7,0.2,2, 10, "RULETA","CICLOS", 0.1);
		algoritmo.ejecutaAG(sol);*/
		Vista p = new Vista();
		p.setSize(1100, 900);
		p.setVisible(true);
	}
}