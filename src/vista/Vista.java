package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	JFrame ventana;
	Plot2DPanel plot;
	public Vista(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		AGenetico algoritmo = new AGenetico();
		JPanel panelCentral = new JPanel(new GridLayout(3, 2, 4, 4));
		add(panelCentral, BorderLayout.EAST);
		ConfigPanel<AGenetico> formulario = creaPanelConfiguracion();
		formulario.setTarget(algoritmo);
		formulario.initialize();
		add(formulario, BorderLayout.WEST);
		plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		add(plot,BorderLayout.EAST);
		
	}
	
	public void grafica(Solucion sol) {
		// define your data
		int tam = sol.get_media().length;
		double[] x = new double[tam];
		for (int i =0;i<tam;i++) {
			x[tam]=i;
		}
		// add a line plot to the PlotPanel
		
		plot.addLinePlot("mejor absoluto", x, sol.get_mejor_hist());
		plot.addLinePlot("mejor absoluto", x, sol.get_mejor_actual());
		plot.addLinePlot("mejor absoluto", x, sol.get_media());

	}

	
	public ConfigPanel<AGenetico> creaPanelConfiguracion() {
		
		ConfigPanel<AGenetico> config = new ConfigPanel<AGenetico>();
		
		// se pueden añadir las opciones de forma independiente, o "de seguido"; el resultado es el mismo.
		config.addOption(new IntegerOption<AGenetico>(  // -- entero
				"MaxGeneracion", 					     // texto a usar como etiqueta del campo
				"Generaciones Maximas",       // texto a usar como 'tooltip' cuando pasas el puntero
				"_num_max_gen",  						     // campo (espera que haya un getGrosor y un setGrosor)
				1, Integer.MAX_VALUE))							     // min y max (usa Integer.MIN_VALUE /MAX_VALUE para infinitos)
			  .addOption(new DoubleOption<AGenetico>(	 // -- eleccion de objeto no-configurable
			    "ProbCruce",							 // etiqueta 
			    "Probalidad de cruce", 					 // tooltip
			    "_prob_cruce",   							 // campo (debe haber un getColor y un setColor)
			    0,1))                            // elecciones posibles
			  .addOption(new DoubleOption<AGenetico>(   // -- doble, parecido a entero
			    "ProbMutacion", 					 // etiqueta
			    "Probabilidad de mutacion",           // tooltip
			    "_prob_mut",                     // campo
			    0, 1 						     // min y max, aplicando factor, si hay; vale usar Double.*_INFINITY) 
			    ))								 // opcional: factor de multiplicacion != 1.0, para mostrar porcentajes
			  .addOption(new DoubleOption<AGenetico>( // -- eleccion de objeto configurable
						"tolerancia",							 // etiqueta
						"tolerancia",                // tooltip
						"_tolerancia",                             // campo
						0, Integer.MAX_VALUE))                             // elecciones (deben implementar Cloneable)
			  
			  .addOption(new IntegerOption<AGenetico>( // -- eleccion de objeto configurable
						"Elitismo",							 // etiqueta
						"Porcentaje elitismo",                // tooltip
						"_elitismo",                             // campo
						0, 100))
			  .addOption(new IntegerOption<AGenetico>( // -- eleccion de objeto configurable
						"TamPoblacion",							 // etiqueta
						"Tamano poblacion",                // tooltip
						"_tam",                             // campo
						1, Integer.MAX_VALUE))
			  .addOption(new ChoiceOption<AGenetico>(	 // -- eleccion de objeto no-configurable
					    "Ejercicio",							 // etiqueta 
					    "Elegir funcion", 					 // tooltip
					    "_ejercicio",   							 // campo (debe haber un getColor y un setColor)
					    new String[] {"EJ1","EJ2","EJ3","EJ4","EJ5"}))
			  .addOption(new ChoiceOption<AGenetico>(	 // -- eleccion de objeto no-configurable
					    "Seleccion",							 // etiqueta 
					    "Metodo de seleccion", 					 // tooltip
					    "_seleccion",   							 // campo (debe haber un getColor y un setColor)
					    new String[] {"RULETA"}))
			  .addOption(new IntegerOption<AGenetico>(
					  "N Variables",
					  "Numero de variables(EJ5)",
					  "_num_fen",
					  1,Integer.MAX_VALUE))
			  // para cada clase de objeto interno, hay que definir sus opciones entre un beginInner y un endInner 
			  .endOptions();
		
		return config;
	}
}
