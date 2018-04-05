package poblacion;

public class Solucion {

	private double[] _mejor_hist;
	private double[] _mejor_actual;
	private double[] _media;
	private int _pos;
	
	public Solucion(int generacion) {
		_mejor_hist = new double[generacion];
		_mejor_actual = new double[generacion];
		_media = new double[generacion];
		_pos = 0;
	}
	
	public void add(double historico, double actual, double media) {
		_mejor_hist[_pos] = historico;
		_mejor_actual[_pos] = actual;
		_media[_pos] = media;
		_pos++;
	}

	public double[] get_mejor_hist() {
		return _mejor_hist;
	}

	public double[] get_mejor_actual() {
		return _mejor_actual;
	}

	public double[] get_media() {
		return _media;
	}

	public int get_pos() {
		return _pos;
	}
	
	
}
