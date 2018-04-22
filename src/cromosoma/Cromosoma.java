package cromosoma;
import java.util.HashMap;
import java.util.Random;

public abstract class Cromosoma {
	protected int[] _genes;
	protected HashMap<Character, Character> _fenotipo;
	protected int _longitud;
	protected double[] _const_fenotipo;// que es esto?
	protected int _num_fen;// y esto, por que tenemos tantos fenotipos
        protected double _aptitud;
        protected String _tipoCrom;
	
        public Cromosoma(){
            _genes = new int[5];
            _fenotipo = new HashMap<Character, Character>();
            _longitud = 5;
            generaAleatorio();
            fenotipo();            
        }
        public Cromosoma(Cromosoma crom){
            _genes = new int[crom._longitud];
            _longitud = crom._longitud;
            System.arraycopy(crom._genes, 0, _genes, 0, _longitud);
            _fenotipo = new HashMap<Character, Character>();
            _fenotipo.putAll(crom._fenotipo);
            
            
        }
        
	public HashMap<Character, Character> fenotipo(){
            HashMap<Character, Character> aux = new HashMap<Character, Character>();
            aux.put(' ', ' ');
            int a = Character.getNumericValue('a');
            for(int i =0;i<_longitud;i++){
                char b = Character.forDigit(i+10, Character.MAX_RADIX);
                aux.put(b, Character.forDigit(_genes[i]+10, Character.MAX_RADIX));
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
            for(int i=_longitud;i>0;i--){
                int posicion = r.nextInt(i);
                int tmp = _genes[i-1];
                _genes[i-1] = _genes[posicion];
                _genes[posicion] = tmp;
            }
	}
        
	public void mutacion(double prob, String tipo, String texto, HashMap map) {
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
                    Funciones.mutacionHeuristica(prob, _genes, this, texto,map);
                    break;
                default:
                    Funciones.mutacionInsercion(prob, _genes);
                    break;
            }
	}
	public abstract void cruce(Cromosoma c2) ;
        
        public HashMap<Character, Character> get_fenotipo(){
            return _fenotipo;
        }
        
        
}
