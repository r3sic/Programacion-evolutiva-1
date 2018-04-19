package cromosoma;
import java.util.HashMap;
import java.util.Random;

public abstract class Cromosoma {
	protected int[] _genes;
	protected HashMap<Character, Character> _fenotipo;
	protected int _longitud;
	protected double[] _const_fenotipo;
	protected int _num_fen;
        protected double _aptitud;
	
	public HashMap<Character, Character> fenotipo(){
            HashMap<Character, Character> aux = new HashMap<Character, Character>();
            int a = Character.getNumericValue('a');
            for(int i =0;i<_longitud;i++){
                aux.put((char) (a+i), (char) _genes[i]);
            }
            _fenotipo = aux;
            return _fenotipo;
        };
        
	public double aptitud(String texto, HashMap<String,Integer> mapfreq){
            _aptitud = Funciones.aptitud(_fenotipo, texto, mapfreq);
            return _aptitud;
        };
        
        public double getaptitud(){
            return _aptitud;
        }
	protected void generaAleatorio() {
            for(int i=0;i<_longitud;i++) {
			//inte = new Integer(i);
                _genes[i] = i;
            }
        
            Random r = new Random();
            for(int i=_longitud;i>0;i++){
                int posicion = r.nextInt(i);
                int tmp = (int) _genes[i-1];
                _genes[i-1] = _genes[posicion];
                _genes[posicion] = tmp;
            }
	}
        
	public void mutacion(double prob, String tipo) {
            switch(tipo){
                case "INSERCION": 
                    Funciones.mutacionInsercion(prob, _genes);
                    break;
                case "INTERCAMBIO": 
                    Funciones.mutacionIntercambio(prob, _genes);
                    break;
                case "INVERSION": 
                    Funciones.mutacionInversion(prob, _genes);
                    break;
                case "SHUFFLE": 
                    Funciones.mutacionShuffle(prob, _genes);
                    break;
                case "HEURISTICA": 
                    Funciones.mutacionHeuristica(prob, _genes, this);
                    break;
                default:
                    Funciones.mutacionInsercion(prob, _genes);
                    break;
            }
	}
	public abstract void cruce(Cromosoma c2) ;
        
        
}
