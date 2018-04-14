import poblacion.AGenetico;
import poblacion.Solucion;
import vista.Vista;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int gen = 100;
		Solucion sol = new Solucion(gen);
		Vista p = new Vista();
		p.setSize(600, 600);
		p.setVisible(true);
		AGenetico algoritmo = new AGenetico(gen, 0.7,0.2, 0.0001,2, 10, "RULETA","EJ4", 6);
		
		algoritmo.ejecutaAG(sol);
	}
}



