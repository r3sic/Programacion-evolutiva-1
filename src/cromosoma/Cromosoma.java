package cromosoma;
import java.util.Random;

public abstract class Cromosoma<Integer> {
	protected Integer[] _genes;
	protected double[] _fenotipo;
	protected int _longitud;
	protected double[] _const_fenotipo;
	protected int _num_fen;
	
	public double[] fenotipo(){
            return null;
        };
	public double aptitud(){
            return 0;
        };
	protected void generaAleatorio() {
		
		for(int i=0;i<_longitud;i++) {
			//inte = new Integer(i);
            _genes[i] = Integer.valueOf(i);;
		}
        
        Random r = new Random();
        for(int i=_longitud;i>0;i++){
            int posicion = r.nextInt(i);
            int tmp = _genes[i-1];
            _genes[i-1] = _genes[posicion];
            _genes[posicion] = tmp;
            
        }
	}
	public void mutacion(double prob) {
		
	}
	public abstract void cruce(Cromosoma c2) ;
}
