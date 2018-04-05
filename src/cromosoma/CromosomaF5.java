package cromosoma;

public class CromosomaF5 extends CromosomaBool{

	public CromosomaF5(double precision, int num_fen) {
		_longitud = 0;
		double intervalo[] = new double[num_fen];
		for(int i = 0; i < num_fen; i++)
			intervalo[i] = Math.PI;
		for(int i = 0; i < num_fen;i++) {
			_longitud +=(int)(Math.ceil(Math.log(1+(intervalo[i]/precision))/Math.log(2)));
		}
		_genes = new Boolean[_longitud];
		_num_fen = num_fen;
		_const_fenotipo = new double[_num_fen];
		generaAleatorio();
		for(int i = 0; i < num_fen; i++) {
			_const_fenotipo[i] = intervalo[i]/(Math.pow(2,(_longitud/_num_fen))-1);
		}
		_fenotipo = new double[_num_fen];
		fenotipo();
	}
	public CromosomaF5(CromosomaBool copia, double precision) {
		super(copia, precision);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double[] fenotipo() {
		// TODO Auto-generated method stub
		double auxiliar;
		int acumul;
		for(int i = 0; i < _num_fen; i++) {
			auxiliar = 0;
			acumul = 1;
			for(int j = _longitud-(_longitud/_num_fen)*i-1;j>=_longitud-(_longitud/_num_fen)*(i+1);j--) {
				auxiliar += (_genes[j])? acumul*_const_fenotipo[i]:0;
				acumul*=2;
			}
			_fenotipo[i] = auxiliar;
		}
		return _fenotipo;
	}

	@Override
	public double aptitud() {
		// TODO Auto-generated method stub
		return -(Funciones.F5(_fenotipo, _num_fen));
	}

}
