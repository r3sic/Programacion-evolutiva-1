package cromosoma;

import java.util.Random;

public class Funciones {
	/*public static double F1(double[] _fenotipo) {
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
		return res;
	}*/
	public static void mutacionInsercion(double prob, Integer[] array) {
        Random r = new Random();
        boolean encontrado = false;
        double d;
        int letra=-1;
        while (!encontrado && letra<=array.length){
            d = r.nextDouble();
            encontrado =(d<=prob);
            letra++;
        }        
        if (encontrado){
            int posnueva = r.nextInt(array.length);
            encontrado = false;
            int i=0;
            while (!encontrado && i<array.length){            
                encontrado=(array[i]==letra);
                i += encontrado?0:1;
            }
            if(posnueva<i)
                rotar(posnueva,i, array);
            else
                rotar(i,posnueva, array); 
       }
    }
	public static void mutacionIntercambio(double prob, Integer[] array) {
        Random r = new Random();
        boolean encontrado = false;
        double d;
        int a;
        int b=-1;
        
        while (!encontrado && b<=array.length){
            d = r.nextDouble();
            encontrado =(d<=prob);
            b++;
        }
        if (encontrado){
            a = r.nextInt(array.length);        
            int tmp = array[a];
            array[a] = array[b];
            array[b]=tmp;
        }
    }
	public static void mutacionInversion(double prob, Integer[] array) {
        Random r = new Random();
        double d;
        boolean encontrado = false;
        int i=0;
        while(!encontrado && i <array.length){
            d = r.nextDouble();
            encontrado= (d<=prob);
            i += encontrado?0:1;
        }
        if (encontrado){
            int j = r.nextInt(array.length);
            if(i>j)
                invertir(j,i, array);
            else
                invertir(i,j, array);
        }
    }

	public static void mutacionHeuristica(double prob, Integer[] array, Cromosoma<Integer> cromosoma) {
		Random r = new Random();
        double d;
        boolean encontrado = false;
        int i=0;
        while(!encontrado && i <array.length){
            d = r.nextDouble();
            encontrado= (d<=prob);
            i += encontrado?0:1;
        }
        if(encontrado) {
        	int a = r.nextInt(array.length), b = r.nextInt(array.length);
        	/*a completar*/
        }
	}
	
	public static void mutacionShuffle(double prob, Integer[] array) {
		Random r = new Random();
        double d;
        boolean encontrado = false;
        int i=0;
        while(!encontrado && i <array.length){
            d = r.nextDouble();
            encontrado= (d<=prob);
            i += encontrado?0:1;
        }
        if (encontrado){
            int j = r.nextInt(array.length);
            if(i>j)
                shuffle(j,i, array);
            else
                shuffle(i,j, array);
        }
	}
	
    private static void shuffle(int ini, int fin, Integer[] array){
        int sub[] = new int[fin-ini+1];
        for(int i=ini;i<=fin;i++){
            sub[fin-1] = array[i];
        }
        Random rnd = new Random();
        for (int i = sub.length - 1; i > 0; i--)
        {
          int index = rnd.nextInt(i + 1);
          // Simple swap
          int a = sub[index];
          sub[index] = sub[i];
          sub[i] = a;
        }
        for (int i =0;i<sub.length;i++){
            array[i+ini] =sub[i];
        }
    }
    private static void invertir(int ini, int fin, Integer[] array){
        int sub[] = new int[fin-ini+1];
        for(int i=ini;i<=fin;i++){
            sub[fin-1] = array[i];
        }
        for (int i =0;i<sub.length;i++){
            array[i+ini] =sub[i];
        }
    }
	 private static void rotar (int inicio, int fin, Integer[] array){
	        int tmp =array[fin];
	        for(int i = inicio;i<=fin;i++){
	            int tmp2 = array[i];
	            array[i] = tmp;
	            tmp = tmp2;
	            
	        }
	 }
	 private static void permutar(Integer[] array, int i, int a, int b, Cromosoma c) {
		 double maxFen;
		 Integer[][] valores = new Integer[][]{{i,a,b},{i,b,a},{a,i,b},{a,b,i},{b,i,a},{b,a,i}};
		 Integer sub[] = new Integer[array.length];
		 System.arraycopy(array, 0, sub, 0, array.length);
		 maxFen =/*o¡btener el primer valor de aptitud con i a b*/0;
		 for(int k = 1; k < 6;k++) {
			 double val;/*calculamos aptitud de esa con vaalores[k][1][k][2][k][3]*/
			/*comprobamos cual es mayor*/
			/*si es mayor la guardamos y actualizamos array con sub;*/		 
		 }
	 }
}