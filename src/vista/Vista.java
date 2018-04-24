package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

import org.math.plot.Plot2DPanel;
import org.math.plot.*;

import poblacion.AGenetico;
import poblacion.Solucion;
import vista.ConfigPanel.ChoiceOption;
import vista.ConfigPanel.ConfigListener;
import vista.ConfigPanel.DoubleOption;
import vista.ConfigPanel.InnerOption;
import vista.ConfigPanel.IntegerOption;
import vista.ConfigPanel.StrategyOption;

public class Vista extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Plot2DPanel plot;
	public Vista(){
		setTitle("Programacion Evolutiva P2");
		JPanel secondary = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		AGenetico algoritmo = new AGenetico(200, 0.7, 0.3, 0.05, 40, "RULETA", "CICLOS",0.1, "uno", "INSERCION");
		JPanel panelCentral = new JPanel(new GridLayout(2, 2, 4, 4));
		add(panelCentral, BorderLayout.EAST);
		ConfigPanel<AGenetico> formulario = creaPanelConfiguracion();
		formulario.setTarget(algoritmo);
		formulario.initialize();
		secondary.add(formulario, BorderLayout.NORTH);
		add(secondary, BorderLayout.WEST);
		plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		plot.setBorder(BorderFactory.createTitledBorder("Graficas"));
		add(plot,BorderLayout.CENTER);
		JButton boton;
		JTextArea solucion = new JTextArea();
		solucion.setBorder(BorderFactory.createTitledBorder("Solucion"));
		solucion.setSize(500, 500);
		add(solucion, BorderLayout.SOUTH);
		boton = new JButton("Ejecutar");
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Solucion sol;
				formulario.setTarget(algoritmo);
				
				formulario.copyUpdate();
				AGenetico evolutiva = new AGenetico(algoritmo.get_num_max_gen(), algoritmo.get_prob_cruce(), algoritmo.get_prob_mut(), algoritmo.get_elitismo(), algoritmo.get_tam(), algoritmo.get_seleccion(), algoritmo.get_ejercicio(),algoritmo.get_trunk(),algoritmo.get_texto(), algoritmo.get_choice_mut());
				//AGenetico evolutiva = new AGenetico(algoritmo.get_num_max_gen(), algoritmo.get_prob_cruce(), algoritmo.get_prob_mut(), algoritmo.get_elitismo(), algoritmo.get_tam(), algoritmo.get_seleccion(), algoritmo.get_ejercicio(),algoritmo.get_trunk(), algoritmo.get_texto());
				sol = new Solucion(algoritmo.get_num_max_gen());
				evolutiva.ejecutaAG(sol);
				solucion.setText("MEJOR SOLUCION: " +sol.get_fenotipo()+" ("+ sol.get_mejor_hist()[sol.get_pos()-1]+")");
				grafica(sol);
			}
		});
		boton.setSize(new Dimension(40,40));
		secondary.add(boton, BorderLayout.SOUTH);
		secondary.setBorder(BorderFactory.createTitledBorder("Datos"));
	}
	
	public void grafica(Solucion sol) {
		// define your data
		int tam = sol.get_media().length;
		double[] x = new double[tam];
		for (int i =0;i<tam;i++) {
			x[i]=i;
		}
		// add a line plot to the PlotPanel
		plot.removeAllPlots();
		plot.addLinePlot("mejor absoluto", x, sol.get_mejor_hist());
		plot.addLinePlot("mejor actual", x, sol.get_mejor_actual());
		plot.addLinePlot("media", x, sol.get_media());
		
	}
	
	public ConfigPanel<AGenetico> creaPanelConfiguracion() {
		
		ConfigPanel<AGenetico> config = new ConfigPanel<AGenetico>();
		
		// se pueden añadir las opciones de forma independiente, o "de seguido"; el resultado es el mismo.
		config.addOption(new IntegerOption<AGenetico>(  // -- entero
				"Max Generacion", 					     // texto a usar como etiqueta del campo
				"Generaciones Maximas",       // texto a usar como 'tooltip' cuando pasas el puntero
				"_num_max_gen",  						     // campo (espera que haya un getGrosor y un setGrosor)
				1, Integer.MAX_VALUE))							     // min y max (usa Integer.MIN_VALUE /MAX_VALUE para infinitos)
			  .addOption(new DoubleOption<AGenetico>(	 // -- eleccion de objeto no-configurable
			    "Prob Cruce",							 // etiqueta 
			    "Probalidad de cruce", 					 // tooltip
			    "_prob_cruce",   							 // campo (debe haber un getColor y un setColor)
			    0,1))                            // elecciones posibles
			  .addOption(new DoubleOption<AGenetico>(   // -- doble, parecido a entero
			    "Prob Mutacion", 					 // etiqueta
			    "Probabilidad de mutacion",           // tooltip
			    "_prob_mut",                     // campo
			    0, 1 						     // min y max, aplicando factor, si hay; vale usar Double.*_INFINITY) 
			    ))								 // opcional: factor de multiplicacion != 1.0, para mostrar porcentajes
			                            // elecciones (deben implementar Cloneable)
			  
			  .addOption(new DoubleOption<AGenetico>( // -- eleccion de objeto configurable
						"Elitismo",							 // etiqueta
						"Porcentaje elitismo(0-1)",                // tooltip
						"_elitismo",                             // campo
						0, 1))
			  .addOption(new IntegerOption<AGenetico>( // -- eleccion de objeto configurable
						"Tam Poblacion",							 // etiqueta
						"Tamano poblacion",                // tooltip
						"_tam",                             // campo
						1, Integer.MAX_VALUE))
			  .addOption(new DoubleOption<AGenetico>( // -- eleccion de objeto configurable
						"Truncamiento",							 // etiqueta
						"Porcentaje truncamiento (0.1-0.5)",                // tooltip
						"_trunk",                             // campo
						0.1, 0.5))
			  .addOption(new ChoiceOption<AGenetico>(	 // -- eleccion de objeto no-configurable
					    "Cruce",							 // etiqueta 
					    "Elegir forma cruce", 					 // tooltip
					    "_ejercicio",   							 // campo (debe haber un getColor y un setColor)
					    new String[] {"CICLOS","CODORDINAL","OX","PMX"}))
			  .addOption(new ChoiceOption<AGenetico>(	 // -- eleccion de objeto no-configurable
					    "Mutacion",							 // etiqueta 
					    "Elegir mutacion", 					 // tooltip
					    "_choice_mut",   							 // campo (debe haber un getColor y un setColor)
					    new String[] {"INSERCION","INTERCAMBIO","INVERSION","SHUFFLE", "HEURISTICA"}))
			  .addOption(new ChoiceOption<AGenetico>(	 // -- eleccion de objeto no-configurable
					    "Seleccion",							 // etiqueta 
					    "Metodo de seleccion", 					 // tooltip
					    "_seleccion",   							 // campo (debe haber un getColor y un setColor)
					    new String[] {"RULETA","UNIVERSAL","TRUNCAMIENTO", "TORNEO DETERMINISTA", "TORNEO PROBABILISTA"}))
			  .addOption(new ChoiceOption<AGenetico>(	 // -- eleccion de objeto no-configurable
					    "Texto",							 // etiqueta 
					    "Elegir texto a decodificar", 					 // tooltip
					    "_texto",   							 // campo (debe haber un getColor y un setColor)
					    new String[] {"uno","dos","tres","cuatro"}))
			  // para cada clase de objeto interno, hay que definir sus opciones entre un beginInner y un endInner 
			  .endOptions();
		
		return config;
	}
}