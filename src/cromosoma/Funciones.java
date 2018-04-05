package cromosoma;

public class Funciones {
	public static double F1(double[] _fenotipo) {
		return  20 + Math.E - 20*(Math.pow(Math.E,-0.2*_fenotipo[0])) - Math.pow(Math.E, Math.cos(Math.PI*2*_fenotipo[0]));
	}
	public static double F2(double[] _fenotipo) {
		return (-(_fenotipo[1]+47.0))*Math.sin(Math.sqrt(Math.abs(_fenotipo[1]+_fenotipo[0]/2.0+47.0)))- _fenotipo[0]*Math.sin(Math.sqrt(Math.abs(_fenotipo[0]-_fenotipo[1]-47.0)));
	}
	public static double F3(double[] _fenotipo) {
		return 21.5 + _fenotipo[0]*Math.sin(4.0*Math.PI*_fenotipo[0]) + _fenotipo[1]*Math.sin(20.0*Math.PI*_fenotipo[1]);
	}
	public static double F4(double[] _fenotipo) {
		double res1 = 0, res2 = 0;
		for(int i = 1; i < 6; i++) {
			res1 += i*Math.cos((i+1)*_fenotipo[0] + i);
			res2 += i*Math.cos((i+1)*_fenotipo[1] + i);
		}
		return res1 * res2;
	}
	public static double F5(double[] _fenotipo, int n) {
		double res = 0;
		for(int i = 0; i < n;i++) {
			res += Math.sin(_fenotipo[i]) * Math.pow((Math.sin((i+1)*_fenotipo[i]*_fenotipo[i]/Math.PI)),20);
		}
		return -res;
	}
}