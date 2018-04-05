package poblacion;

import java.util.Arrays;

public class AGenetico {
	private Poblacion _poblacion;
	private int _num_max_gen;
	private int _gen_actual;
	private double _prob_cruce;
	private double _prob_mut;
	private double _tolerancia;
	private double _mejor;
	private double[] _mejorf;
	private double _elitismo;
	private int _tam;
	private String _seleccion;
	private String _ejercicio;
	private int _num_fen;
	private double _trunk;
	
	
	public AGenetico(int max_gen, double cruce, double mut, double tol, double elitismo, int tam, String seleccion, String ejercicio,double trunk, int num_fen)  {
		_num_max_gen = max_gen;
		_tam = tam;
		_elitismo = elitismo;
		_gen_actual = 0;
		_prob_cruce = cruce;
		_prob_mut = mut;
		_tolerancia = tol;
		_num_fen = num_fen;
		_trunk = trunk;
		_ejercicio = ejercicio;
		_seleccion = seleccion;
	}
	public AGenetico() {
		_gen_actual = 0;
	}
	
	public void ejecutaAG(Solucion sol) {
		_poblacion = FactoriaPoblaciones.getPoblacion(_seleccion, _tam, _ejercicio , _tolerancia,_trunk, _elitismo, _num_fen);
		_mejorf = new double[_poblacion.getMejorFen().length];
		_mejor = _poblacion.getMejorApt();
		System.arraycopy(_poblacion.getMejorFen(), 0, _mejorf, 0, _mejorf.length);
		while(_gen_actual < _num_max_gen) {
			//System.out.println(Arrays.toString(_mejorf)+" :"+_mejor +" -> "+Arrays.toString(_poblacion.getMejorFen()) + "  :" + _poblacion.getMejorApt());
			_poblacion.seleccion();
			_poblacion.cruce(_prob_cruce);
			_poblacion.mutacion(_prob_mut);
			if(_mejor < _poblacion.getMejorApt()) { 
				_mejor = _poblacion.getMejorApt();
				System.arraycopy(_poblacion.getMejorFen(), 0, _mejorf, 0, _mejorf.length);
			}
			sol.add(_mejor, _poblacion.getMejorApt(), _poblacion.media());
			_gen_actual++;
		}
		sol.set_fenotipo(_mejorf);
		//System.out.println(Arrays.toString(_mejorf)+" :"+_mejor +" -> "+Arrays.toString(_poblacion.getMejorFen()) + "  :" + _poblacion.getMejorApt());
	}
	public void Inicializa() {
		this._poblacion = FactoriaPoblaciones.getPoblacion(_seleccion, _tam, _ejercicio , _tolerancia,_trunk, _elitismo, _num_fen);
	}
	public Poblacion get_poblacion() {
		return _poblacion;
	}
	public void set_poblacion() {
		this._poblacion = FactoriaPoblaciones.getPoblacion(_seleccion, _tam, _ejercicio , _tolerancia,_trunk, _elitismo, _num_fen);
	}
	public int get_num_max_gen() {
		return _num_max_gen;
	}
	public void set_num_max_gen(int _num_max_gen) {
		this._num_max_gen = _num_max_gen;
	}
	public int get_gen_actual() {
		return _gen_actual;
	}
	public void set_gen_actual(int _gen_actual) {
		this._gen_actual = _gen_actual;
	}
	public double get_prob_cruce() {
		return _prob_cruce;
	}
	public void set_prob_cruce(double _prob_cruce) {
		this._prob_cruce = _prob_cruce;
	}
	public double get_prob_mut() {
		return _prob_mut;
	}
	public void set_prob_mut(double _prob_mut) {
		this._prob_mut = _prob_mut;
	}
	public double get_tolerancia() {
		return _tolerancia;
	}
	public void set_tolerancia(double _tolerancia) {
		this._tolerancia = _tolerancia;
	}
	public double get_mejor() {
		return _mejor;
	}
	public void set_mejor(double _mejor) {
		this._mejor = _mejor;
	}
	public double[] get_mejorf() {
		return _mejorf;
	}
	public String get_seleccion() {
		return _seleccion;
	}
	public void set_seleccion(String _seleccion) {
		this._seleccion = _seleccion;
	}
	public String get_ejercicio() {
		return _ejercicio;
	}
	public void set_ejercicio(String _ejercicio) {
		this._ejercicio = _ejercicio;
	}
	public double get_elitismo() {
		return _elitismo;
	}
	public void set_elitismo(double _elitismo) {
		this._elitismo = _elitismo;
	}
	public int get_tam() {
		return _tam;
	}
	public void set_tam(int _tam) {
		this._tam = _tam;
	}
	public void set_poblacion(Poblacion _poblacion) {
		this._poblacion = _poblacion;
	}
	public int get_num_fen() {
		return _num_fen;
	}
	public void set_num_fen(int _num_fen) {
		this._num_fen = _num_fen;
	}
	public double get_trunk() {
		return _trunk;
	}
	public void set_trunk(double _trunk) {
		this._trunk = _trunk;
	}
	
}
