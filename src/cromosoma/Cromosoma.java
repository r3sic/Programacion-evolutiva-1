package cromosoma;
import java.util.HashMap;
import java.util.Random;

public abstract class Cromosoma {
	protected int[] _genes;
	protected HashMap<Character, Character> _fenotipo;
	protected int _longitud;
        protected double _aptitud;
        protected String _tipoCrom;
	
        public Cromosoma(){
            _longitud = 26;
            _genes = new int[_longitud];
            _fenotipo = new HashMap<Character, Character>();
            generaAleatorio();
            fenotipo();
            
        }
        public Cromosoma(Cromosoma crom){
            _genes = new int[crom._longitud];
            _longitud = crom._longitud;
            System.arraycopy(crom._genes, 0, _genes, 0, _longitud);
            _fenotipo = new HashMap<Character, Character>();
            _fenotipo.putAll(crom._fenotipo);
            _aptitud=crom._aptitud;
        }
        
	public HashMap<Character, Character> fenotipo(){
            HashMap<Character, Character> aux = new HashMap<Character, Character>();
            aux.put(' ', ' ');
            for(int i =0;i<_longitud;i++){
                char b = Character.forDigit(i+10, Character.MAX_RADIX);
                aux.put(b, Character.forDigit(_genes[i]+10, Character.MAX_RADIX));
            }
            _fenotipo = aux;
            return _fenotipo;
        };
        
	public double aptitud(String texto, HashMap<String,Double> mapfreq){
            _aptitud = Funciones.aptitud(fenotipo(), texto, mapfreq);
            return _aptitud;
        };
        
        public double getaptitud(){
            return _aptitud;
        }
	protected void generaAleatorio() {
            for(int i=0;i<_longitud;i++)
            	_genes[i] = i;
            Random r = new Random();
            for(int j = 0; j < 3; j++) {
            for(int i=_longitud;i>0;i--){
                int posicion = r.nextInt(i);
                int tmp = _genes[i-1];
                _genes[i-1] = _genes[posicion];
                _genes[posicion] = tmp;
            }}
	}
        
	public boolean mutacion(double prob, String tipo, String texto, HashMap map) {
            boolean cambio = false;
            switch(tipo){
                case "INSERCION": 
                    cambio= Funciones.mutacionInsercion(prob, _genes);
                    
                case "INTERCAMBIO": 
                    cambio= Funciones.mutacionIntercambio(prob, _genes);
                    
                case "INVERSION": 
                    cambio= Funciones.mutacionInversion(prob, _genes);
                    
                case "SHUFFLE": 
                    cambio= Funciones.mutacionShuffle(prob, _genes);
                    
                case "HEURISTICA": 
                    cambio= Funciones.mutacionHeuristica(prob, _genes, this, texto,map);
                    
                default:
                    cambio= Funciones.mutacionInsercion(prob, _genes);
                    
            }
            if(cambio)
                fenotipo();
            return cambio;
	}
	public abstract void cruce(Cromosoma c2) ;
        
        public HashMap<Character, Character> get_fenotipo(){
            return _fenotipo;
        }
        
    public void setGenes(int[] _genes) {
        this._genes = _genes;
    }
        
}
