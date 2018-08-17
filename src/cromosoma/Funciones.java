package cromosoma;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

public class Funciones {

	public static HashMap<String, Double> leerDigram(){
		HashMap<String, Double> mapa = new HashMap<String, Double>();
		try {
			String line;
			double suma = 0.0;
			BufferedReader in = new BufferedReader(new FileReader("english_bigrams.txt"));
			BufferedReader in2 = new BufferedReader(new FileReader("english_bigrams.txt"));
			while((line = in2.readLine()) != null)
				suma+=Integer.parseInt(line.substring(line.indexOf(" ")+1));
			while((line = in.readLine()) != null) {
				line.indexOf(" ");
				mapa.put(line.substring(0,line.indexOf(" ")), (Double.valueOf(line.substring(line.indexOf(" ")+1)))/suma);
			}
			in.close();
			in2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapa;
	}
	public static HashMap<String, Double> leerTrigram(){
		HashMap<String, Double> mapa = new HashMap<String, Double>();
		try {
			String line;
			double suma = 1.0;
			BufferedReader in = new BufferedReader(new FileReader("english_trigrams.txt"));
			BufferedReader in2 = new BufferedReader(new FileReader("english_trigrams.txt"));
			while((line = in2.readLine()) != null)
				suma+=Integer.parseInt(line.substring(line.indexOf(" ")+1));
			while((line = in.readLine()) != null) {
				line.indexOf(" ");
				mapa.put(line.substring(0,line.indexOf(" ")), (Double.valueOf(line.substring(line.indexOf(" ")+1)))/suma);
			}
			in.close();
			in2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapa;
	}
	
	public static String leerTexto(String texto) {
		String cadena = "", line, archivo;
		switch(texto) {
		case "uno":
			archivo = "Uno.txt";
			break;
		case "dos":
			archivo = "Dos.txt";
			break;
		case "tres":
			archivo = "Tres.txt";
			break;
		case "cuatro":
			archivo = "cuatro.txt";
			break;
		default:
			archivo = "uno.txt";
		}
		try {
			BufferedReader in = new BufferedReader(new FileReader(archivo));
			while((line = in.readLine()) != null)
				cadena += line;
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cadena;
	}
    public static String traduccion(HashMap<Character, Character> _fenotipo, String texto) {
        String trad = "";
        String text2 = texto.toLowerCase();
        int i = 0;
        while (i < text2.length()) {
            if (_fenotipo.containsKey(text2.charAt(i))) {
                trad += _fenotipo.get(text2.charAt(i)).toString();
                
            } else {
                trad.concat(String.valueOf(text2.charAt(i)));
            }
            i++;
        }
        return trad;
    }

    public static HashMap<String, Double> IncicFreqDig(String trad){
        HashMap<String, Double> freq = new HashMap();
        String aux1,aux2;
        int i=0,f =2;
        StringTokenizer st = new StringTokenizer(trad);
        while (st.hasMoreTokens()){
            aux1=st.nextToken();
            while(f<=aux1.length()){
                aux2=aux1.substring(i, f);
                if(!freq.containsKey(aux2))
                    freq.put(aux2, 1.0);
                else
                    freq.put(aux2, freq.get(aux2)+1);
                i++;
                f++;
            }
            i=0;
            f=2;
        }
        return freq;
    }
    
    public static HashMap<String, Double> InicFreq(String trad, HashMap<String,Double> mapa_t){
        HashMap<String, Double> freq = new HashMap<String, Double>();
        String aux = "";
        int i = 0;
        while(i<trad.length()-2){
            aux =trad.substring(i, i + 2);
            if(mapa_t.containsKey(aux.toUpperCase())) {
	            if(!freq.containsKey(aux.toUpperCase()))
	                freq.put(aux.toUpperCase(), 1.0/(trad.length()));
	            else
	                freq.replace(aux.toUpperCase(), (freq.get(aux.toUpperCase())+(1.0/(trad.length()))));
	            i++;
            }
            else
            	i++;
        }
        while(i<trad.length()-3){
            aux =trad.substring(i, i + 3);
            if(mapa_t.containsKey(aux.toUpperCase())) {
	            if(!freq.containsKey(aux.toUpperCase()))
	                freq.put(aux.toUpperCase(), 1.0/(trad.length()));
	            else
	                freq.replace(aux.toUpperCase(), (freq.get(aux.toUpperCase())+(1.0/(trad.length()))));
	            i++;
            }
            else
            	i++;
        }
        
        return freq;
    }
    
    /**
     * 
     * @param _fenotipo -> el fenotipo del cromosoma
     * @param texto -> el texto a traducir
     * @param mapDig -> el mapa con los digramas y su aparicion en el ingles. estoy suponiendo que el double del map no son valores alrederor del 1.0 si no 300 o valores altos en general
     * @return 
     */
    public static double aptitud(HashMap<Character, Character> _fenotipo, String texto,HashMap<String, Double> mapDig ){
        String trad = "";
        trad = traduccion(_fenotipo, texto);
        HashMap<String, Double> freq = IncicFreqDig(trad);
        double apt=0.0;
        double Fp,Ft;
        for(Map.Entry<String,Double> entrada : freq.entrySet()){
            Fp=freq.get(entrada.getKey());
            Ft=mapDig.containsKey(entrada.getKey().toLowerCase())?mapDig.get(entrada.getKey().toLowerCase()):1.0;//si el map de digramas esta con minusculas va bien, si no, cambier los toLowerCase a toUperCase
            Ft = Math.log(Ft);
            apt +=(Fp*Ft);
        }
        return apt;
    }
    /*public static double aptitud(HashMap<Character, Character> _fenotipo, String texto, HashMap<String, Double> mapfreq) {
        
        String trad = "";
        trad = traduccion(_fenotipo, texto);
        HashMap<String, Double> freq = InicFreq(trad, mapfreq);
        double apt = 0.0;
        double a,b;
        double[] arr = new double[freq.size()];
        double suma_total = 0;
        int i = 0;
        
        for(Map.Entry<String, Double> entrada : freq.entrySet()) {
        	//a = entrada.getValue();
        	arr[i] = (mapfreq.containsKey(entrada.getKey()))?mapfreq.get(entrada.getKey()):0;
        	suma_total += arr[i];
        	i++;	
        }/*
        for(int j = 0; j < i; j++) {
        	arr[j] /= suma_total;
        }*/
        /*i = 0;
        for(Map.Entry<String, Double> entrada : freq.entrySet()) {
        	a = entrada.getValue();
        	b = (mapfreq.containsKey(entrada.getKey()))?mapfreq.get(entrada.getKey()):0;
                b = Math.log(b);//opcion a, mira que tal
                
        	//i++;
        	apt += Math.pow(a-b,2);
        }*/
        /*
        opcion b
        apt = 1.0;
        for(Map.Entry<String, Double> entrada : freq.entrySet()){
            a = entrada.getValue();
            b = (mapfreq.containsKey(entrada.getKey()))?mapfreq.get(entrada.getKey()):0;
            apt -= beta*Math.abs(b-a);
            //si usamos Bigramos Y trigramas -> con los trigramas en vez de multiplicarlo por beta hay que multiplicarlo por un gamma tal que beta+gamma=1
            //bata y gamma es para darle mas enfasis en los bigramas o en los trigramas
        }
        */
        /*
        while (i < texto.length() - 3) {
            if (texto.charAt(i) == ' ') {
                i++;
            }
            else {
            String aux1="";
            String aux2="";
            aux1 +=texto.substring(i, i + 2);
            aux2 +=trad.substring(i, i + 2);
            
            a =freq.containsKey(aux2.toUpperCase())?freq.get(aux2.toUpperCase()):0;
            b =mapfreq.containsKey(aux1.toUpperCase())?mapfreq.get(aux1.toUpperCase()):0;
            apt += Math.pow(a-b, 2); //Cuando el hassMap de traduccion de frecuencias de digramas descomentar y terminar,tal vez dar la vuelta a la resta                
            i=i+2;
            }
        }*//*
        return -apt;
    }*/
    public static boolean mutacionInsercion(double prob, int[] array) {
        Random r = new Random();
        boolean encontrado = false,ret= false;
        double d;
        int letra = -1;
        while (!encontrado && letra < array.length) {
            d = r.nextDouble();
            encontrado = (d <= prob);
            letra++;
        }
        if (encontrado) {
            ret=true;
            int posnueva = r.nextInt(array.length);
            encontrado = false;
            int i = 0;
            while (!encontrado && i < array.length) {
                encontrado = (array[i] == letra);
                i += encontrado ? 0 : 1;
            }
            if (encontrado) {
            if (posnueva < i) {
                rotar(posnueva, i, array);
            } else {
                rotar(i, posnueva, array);
            }
            }
            
        }
        return encontrado;
    }

    public static boolean mutacionIntercambio(double prob, int[] array) {
        Random r = new Random();
        boolean encontrado = false;
        double d;
        int a;
        int b = -1;

        while (!encontrado && b < array.length) {
            d = r.nextDouble();
            encontrado = (d <= prob);
            b++;
        }
        if (encontrado && b < array.length) {
            a = r.nextInt(array.length);
            int tmp = array[a];
            array[a] = array[b];
            array[b] = tmp;
        }
        return encontrado;
    }

    public static boolean mutacionInversion(double prob, int[] array) {
        Random r = new Random();
        double d;
        boolean encontrado = false;
        int i = 0;
        while (!encontrado && i < array.length) {
            d = r.nextDouble();
            encontrado = (d <= prob);
            i += encontrado ? 0 : 1;
        }
        if (encontrado) {
            int j = r.nextInt(array.length);
            if (i > j) {
                invertir(j, i, array);
            } else {
                invertir(i, j, array);
            }
        }
        return encontrado;
    }

    public static boolean mutacionHeuristica(double prob, int[] array, Cromosoma cromosoma,String texto, HashMap<String,Double> map) {
        
        Random r = new Random();
        double d;
        boolean encontrado = false;
        int c = 0;
        while (!encontrado && c < array.length) {
            d = r.nextDouble();
            encontrado = (d <= prob);
            c += encontrado ? 0 : 1;
        }
        if (encontrado) {
            int a = r.nextInt(array.length);
            if (a>c){
                int aux = a;
                a=c;
                c=aux;
            }
            int b = r.nextInt(array.length);
            if(b>c){
                int aux = b;
                b=c;
                c=aux;
            }
            if(a>b){
                int aux = a;
                a=b;
                b=aux;
            }
               
            //a<c b<c a<b          
           
            // puesto por debugeo pero tiene que estar fuera en la version final          
            Cromosoma CromMejor = FactoriaCromosoma.getCromosomaCopia(cromosoma);// cambiarlo a copia, por seguridad. // caso abc
            Cromosoma CromPrueba = FactoriaCromosoma.getCromosomaCopia(cromosoma);// cambiarlo a copia, por seguridad.
            double mejoraptitudactual = aptitud(cromosoma._fenotipo,texto,map);
            double aptitudprueba;
            // caso acb
            int aux = CromPrueba._genes[c];
            CromPrueba._genes[c]=CromPrueba._genes[b];
            CromPrueba._genes[b]=aux;            
            aptitudprueba = aptitud(CromPrueba.fenotipo(),texto,map);
            if(mejoraptitudactual<aptitudprueba){
                CromMejor=CromPrueba;
                mejoraptitudactual=aptitudprueba;
            }
            //caso bac
            CromPrueba = FactoriaCromosoma.getCromosomaCopia(cromosoma);
            aux = CromPrueba._genes[b];
            CromPrueba._genes[b]=CromPrueba._genes[a];
            CromPrueba._genes[a]=aux;
            aptitudprueba = aptitud(CromPrueba.fenotipo(),texto,map);
            if(mejoraptitudactual<aptitudprueba){
                CromMejor=CromPrueba;
                mejoraptitudactual=aptitudprueba;
            }
            //caso bca
            CromPrueba = FactoriaCromosoma.getCromosomaCopia(cromosoma);
            aux = CromPrueba._genes[b];
            CromPrueba._genes[b]=CromPrueba._genes[a];
            CromPrueba._genes[a]=aux;
            aux = CromPrueba._genes[b];
            CromPrueba._genes[b]=CromPrueba._genes[c];
            CromPrueba._genes[c]=aux;
            aptitudprueba = aptitud(CromPrueba.fenotipo(),texto,map);
            if(mejoraptitudactual<aptitudprueba){
                CromMejor=CromPrueba;
                mejoraptitudactual=aptitudprueba;
            }
            
            //caso cab
            CromPrueba = FactoriaCromosoma.getCromosomaCopia(cromosoma);
            aux = CromPrueba._genes[b];
            CromPrueba._genes[b]=CromPrueba._genes[a];
            CromPrueba._genes[a]=aux;
            aux = CromPrueba._genes[a];
            CromPrueba._genes[a]=CromPrueba._genes[c];
            CromPrueba._genes[c]=aux;
            aptitudprueba = aptitud(CromPrueba.fenotipo(),texto,map);
            if(mejoraptitudactual<aptitudprueba){
                CromMejor=CromPrueba;
                mejoraptitudactual=aptitudprueba;
            }
            //caso cab
            CromPrueba = FactoriaCromosoma.getCromosomaCopia(cromosoma);
            aux = CromPrueba._genes[c];
            CromPrueba._genes[c]=CromPrueba._genes[a];
            CromPrueba._genes[a]=aux;
            aptitudprueba = aptitud(CromPrueba.fenotipo(),texto,map);
            if(mejoraptitudactual<aptitudprueba){
                CromMejor=CromPrueba;
                mejoraptitudactual=aptitudprueba;
            }
            cromosoma=CromMejor;
            
            
        }
        return encontrado;
    }
    
    
    public static boolean mutacionShuffle(double prob, int[] array) {
        Random r = new Random();
        double d;
        boolean encontrado = false;
        int i = 0;
        while (!encontrado && i < array.length) {
            d = r.nextDouble();
            encontrado = (d <= prob);
            i += encontrado ? 0 : 1;
        }
        if (encontrado) {
            int j = r.nextInt(array.length);
            if (i > j) {
                shuffle(j, i, array);
            } else {
                shuffle(i, j, array);
            }
        }
        return encontrado;
    }

    private static void shuffle(int ini, int fin, int[] array) {
        int sub[] = new int[fin - ini + 1];
        for (int i = ini; i <= fin; i++) {
            sub[fin - i] = array[i];
        }
        Random rnd = new Random();
        for (int i = sub.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = sub[index];
            sub[index] = sub[i];
            sub[i] = a;
        }
        for (int i = 0; i < sub.length; i++) {
            array[i + ini] = sub[i];
        }
    }

    private static void invertir(int ini, int fin, int[] array) {
        int sub[] = new int[fin - ini + 1];
        for (int i = ini; i <= fin; i++) {
            sub[fin - i] = array[i];
        }
        for (int i = 0; i < sub.length; i++) {
            array[i + ini] = sub[i];
        }
    }

    private static void rotar(int inicio, int fin, int[] array) {
        int tmp = array[fin];
        for (int i = inicio; i <= fin; i++) {
            int tmp2 = array[i];
            array[i] = tmp;
            tmp = tmp2;

        }
    }

    private static void permutar(Integer[] array, int i, int a, int b, Cromosoma c) {
        double maxFen;
        Integer[][] valores = new Integer[][]{{i, a, b}, {i, b, a}, {a, i, b}, {a, b, i}, {b, i, a}, {b, a, i}};
        Integer sub[] = new Integer[array.length];
        System.arraycopy(array, 0, sub, 0, array.length);
        maxFen =/*o¡btener el primer valor de aptitud con i a b*/ 0;
        for (int k = 1; k < 6; k++) {
            double val;/*calculamos aptitud de esa con vaalores[k][1][k][2][k][3]*/
 /*comprobamos cual es mayor*/
 /*si es mayor la guardamos y actualizamos array con sub;*/
        }
    }
/*
    public static HashMap<String, Double> initializeFrecuencies() {
        HashMap<String, Integer> frequencies = new HashMap<String, Integer>();
        
        frequencies.put("AA", 1);
        frequencies.put("AB", 32);
        frequencies.put("AC", 39);
        frequencies.put("AD", 15);
        frequencies.put("AE", 0);
        frequencies.put("AF", 10);
        frequencies.put("AG", 18);
        frequencies.put("AH", 0);
        frequencies.put("AI", 16);
        frequencies.put("AJ", 0);
        frequencies.put("AK", 10);
        frequencies.put("AL", 77);
        frequencies.put("AM", 18);
        frequencies.put("AN", 172);
        frequencies.put("AO", 2);
        frequencies.put("AP", 31);
        frequencies.put("AQ", 1);
        frequencies.put("AR", 101);
        frequencies.put("AS", 67);
        frequencies.put("AT", 124);
        frequencies.put("AU", 12);
        frequencies.put("AV", 24);
        frequencies.put("AW", 7);
        frequencies.put("AX", 0);
        frequencies.put("AY", 127);
        frequencies.put("AZ", 1);

        frequencies.put("BA", 8);
        frequencies.put("BB", 0);
        frequencies.put("BC", 0);
        frequencies.put("BD", 0);
        frequencies.put("BE", 58);
        frequencies.put("BF", 0);
        frequencies.put("BG", 0);
        frequencies.put("BH", 0);
        frequencies.put("BI", 6);
        frequencies.put("BJ", 2);
        frequencies.put("BK", 0);
        frequencies.put("BL", 21);
        frequencies.put("BM", 1);
        frequencies.put("BN", 0);
        frequencies.put("BO", 11);
        frequencies.put("BP", 0);
        frequencies.put("BQ", 0);
        frequencies.put("BR", 6);
        frequencies.put("BS", 5);
        frequencies.put("BT", 0);
        frequencies.put("BU", 25);
        frequencies.put("BV", 0);
        frequencies.put("BW", 0);
        frequencies.put("BX", 0);
        frequencies.put("BY", 19);
        frequencies.put("BZ", 0);

        frequencies.put("CA", 44);
        frequencies.put("CB", 0);
        frequencies.put("CC", 12);
        frequencies.put("CD", 0);
        frequencies.put("CE", 55);
        frequencies.put("CF", 1);
        frequencies.put("CG", 0);
        frequencies.put("CH", 46);
        frequencies.put("CI", 15);
        frequencies.put("CJ", 0);
        frequencies.put("CK", 8);
        frequencies.put("CL", 16);
        frequencies.put("CM", 0);
        frequencies.put("CN", 0);
        frequencies.put("CO", 59);
        frequencies.put("CP", 1);
        frequencies.put("CQ", 0);
        frequencies.put("CR", 7);
        frequencies.put("CS", 1);
        frequencies.put("CT", 38);
        frequencies.put("CU", 16);
        frequencies.put("CV", 0);
        frequencies.put("CW", 1);
        frequencies.put("CX", 0);
        frequencies.put("CY", 0);
        frequencies.put("CZ", 0);

        frequencies.put("DA", 45);
        frequencies.put("DB", 18);
        frequencies.put("DC", 14);
        frequencies.put("DD", 10);
        frequencies.put("DE", 39);
        frequencies.put("DF", 12);
        frequencies.put("DG", 2);
        frequencies.put("DH", 3);
        frequencies.put("DI", 57);
        frequencies.put("DJ", 1);
        frequencies.put("DK", 0);
        frequencies.put("DL", 7);
        frequencies.put("DM", 9);
        frequencies.put("DN", 5);
        frequencies.put("DO", 37);
        frequencies.put("DP", 7);
        frequencies.put("DQ", 1);
        frequencies.put("DR", 10);
        frequencies.put("DS", 32);
        frequencies.put("DT", 39);
        frequencies.put("DU", 8);
        frequencies.put("DV", 4);
        frequencies.put("DW", 9);
        frequencies.put("DX", 0);
        frequencies.put("DY", 6);
        frequencies.put("DZ", 0);

        frequencies.put("EA", 131);
        frequencies.put("EB", 11);
        frequencies.put("EC", 64);
        frequencies.put("ED", 107);
        frequencies.put("EE", 39);
        frequencies.put("EF", 23);
        frequencies.put("EG", 20);
        frequencies.put("EH", 15);
        frequencies.put("EI", 40);
        frequencies.put("EJ", 1);
        frequencies.put("EK", 2);
        frequencies.put("EL", 46);
        frequencies.put("EM", 43);
        frequencies.put("EN", 120);
        frequencies.put("EO", 46);
        frequencies.put("EP", 32);
        frequencies.put("EQ", 14);
        frequencies.put("ER", 154);
        frequencies.put("ES", 145);
        frequencies.put("ET", 80);
        frequencies.put("EU", 7);
        frequencies.put("EV", 16);
        frequencies.put("EW", 41);
        frequencies.put("EX", 17);
        frequencies.put("EY", 17);
        frequencies.put("EZ", 0);

        frequencies.put("FA", 21);
        frequencies.put("FB", 2);
        frequencies.put("FC", 9);
        frequencies.put("FD", 1);
        frequencies.put("FE", 25);
        frequencies.put("FF", 14);
        frequencies.put("FG", 1);
        frequencies.put("FH", 6);
        frequencies.put("FI", 21);
        frequencies.put("FJ", 1);
        frequencies.put("FK", 0);
        frequencies.put("FL", 10);
        frequencies.put("FM", 3);
        frequencies.put("FN", 2);
        frequencies.put("FO", 38);
        frequencies.put("FP", 3);
        frequencies.put("FQ", 0);
        frequencies.put("FR", 4);
        frequencies.put("FS", 8);
        frequencies.put("FT", 42);
        frequencies.put("FU", 11);
        frequencies.put("FV", 1);
        frequencies.put("FW", 4);
        frequencies.put("FX", 0);
        frequencies.put("FY", 1);
        frequencies.put("FZ", 0);

        frequencies.put("GA", 11);
        frequencies.put("GB", 2);
        frequencies.put("GC", 1);
        frequencies.put("GD", 1);
        frequencies.put("GE", 32);
        frequencies.put("GF", 3);
        frequencies.put("GG", 1);
        frequencies.put("GH", 16);
        frequencies.put("GI", 10);
        frequencies.put("GJ", 0);
        frequencies.put("GK", 0);
        frequencies.put("GL", 4);
        frequencies.put("GM", 1);
        frequencies.put("GN", 3);
        frequencies.put("GO", 23);
        frequencies.put("GP", 1);
        frequencies.put("GQ", 0);
        frequencies.put("GR", 21);
        frequencies.put("GS", 7);
        frequencies.put("GT", 13);
        frequencies.put("GU", 8);
        frequencies.put("GV", 0);
        frequencies.put("GW", 2);
        frequencies.put("GX", 0);
        frequencies.put("GY", 1);
        frequencies.put("GZ", 0);

        frequencies.put("HA", 84);
        frequencies.put("HB", 1);
        frequencies.put("HC", 2);
        frequencies.put("HD", 1);
        frequencies.put("HE", 251);
        frequencies.put("HF", 2);
        frequencies.put("HG", 0);
        frequencies.put("HH", 5);
        frequencies.put("HI", 72);
        frequencies.put("HJ", 0);
        frequencies.put("HK", 0);
        frequencies.put("HL", 3);
        frequencies.put("HM", 1);
        frequencies.put("HN", 2);
        frequencies.put("HO", 46);
        frequencies.put("HP", 1);
        frequencies.put("HQ", 0);
        frequencies.put("HR", 8);
        frequencies.put("HS", 3);
        frequencies.put("HT", 22);
        frequencies.put("HU", 2);
        frequencies.put("HV", 0);
        frequencies.put("HW", 7);
        frequencies.put("HX", 0);
        frequencies.put("HY", 1);
        frequencies.put("HZ", 0);

        frequencies.put("IA", 18);
        frequencies.put("IB", 7);
        frequencies.put("IC", 55);
        frequencies.put("ID", 16);
        frequencies.put("IE", 37);
        frequencies.put("IF", 27);
        frequencies.put("IG", 10);
        frequencies.put("IH", 0);
        frequencies.put("II", 0);
        frequencies.put("IJ", 0);
        frequencies.put("IK", 8);
        frequencies.put("IL", 39);
        frequencies.put("IM", 32);
        frequencies.put("IN", 169);
        frequencies.put("IO", 63);
        frequencies.put("IP", 3);
        frequencies.put("IQ", 0);
        frequencies.put("IR", 21);
        frequencies.put("IS", 106);
        frequencies.put("IT", 88);
        frequencies.put("IU", 0);
        frequencies.put("IV", 14);
        frequencies.put("IW", 1);
        frequencies.put("IX", 1);
        frequencies.put("IY", 0);
        frequencies.put("IZ", 4);

        frequencies.put("JA", 0);
        frequencies.put("JB", 0);
        frequencies.put("JC", 0);
        frequencies.put("JD", 0);
        frequencies.put("JE", 2);
        frequencies.put("JF", 0);
        frequencies.put("JG", 0);
        frequencies.put("JH", 0);
        frequencies.put("JI", 0);
        frequencies.put("JJ", 0);
        frequencies.put("JK", 0);
        frequencies.put("JL", 0);
        frequencies.put("JM", 0);
        frequencies.put("JN", 0);
        frequencies.put("JO", 4);
        frequencies.put("JP", 0);
        frequencies.put("JQ", 0);
        frequencies.put("JR", 0);
        frequencies.put("JS", 0);
        frequencies.put("JT", 0);
        frequencies.put("JU", 4);
        frequencies.put("JV", 0);
        frequencies.put("JW", 0);
        frequencies.put("JX", 0);
        frequencies.put("JY", 0);
        frequencies.put("JZ", 0);

        frequencies.put("KA", 0);
        frequencies.put("KB", 0);
        frequencies.put("KC", 0);
        frequencies.put("KD", 0);
        frequencies.put("KE", 28);
        frequencies.put("KF", 0);
        frequencies.put("KG", 0);
        frequencies.put("KH", 0);
        frequencies.put("KI", 8);
        frequencies.put("KJ", 0);
        frequencies.put("KK", 0);
        frequencies.put("KL", 0);
        frequencies.put("KM", 0);
        frequencies.put("KN", 3);
        frequencies.put("KO", 3);
        frequencies.put("KP", 0);
        frequencies.put("KQ", 0);
        frequencies.put("KR", 0);
        frequencies.put("KS", 2);
        frequencies.put("KT", 1);
        frequencies.put("KU", 0);
        frequencies.put("KV", 0);
        frequencies.put("KW", 3);
        frequencies.put("KX", 0);
        frequencies.put("KY", 3);
        frequencies.put("KZ", 0);
        //L
        frequencies.put("LA", 34);
        frequencies.put("LB", 7);
        frequencies.put("LC", 8);
        frequencies.put("LD", 28);
        frequencies.put("LE", 72);
        frequencies.put("LF", 5);
        frequencies.put("LG", 1);
        frequencies.put("LH", 0);
        frequencies.put("LI", 57);
        frequencies.put("LJ", 1);
        frequencies.put("LK", 3);
        frequencies.put("LL", 55);
        frequencies.put("LM", 4);
        frequencies.put("LN", 1);
        frequencies.put("LO", 28);
        frequencies.put("LP", 2);
        frequencies.put("LQ", 2);
        frequencies.put("LR", 2);
        frequencies.put("LS", 12);
        frequencies.put("LT", 19);
        frequencies.put("LU", 8);
        frequencies.put("LV", 2);
        frequencies.put("LW", 5);
        frequencies.put("LX", 0);
        frequencies.put("LY", 47);
        frequencies.put("LZ", 0);
        //M
        frequencies.put("MA", 56);
        frequencies.put("MB", 9);
        frequencies.put("MC", 1);
        frequencies.put("MD", 2);
        frequencies.put("ME", 48);
        frequencies.put("MF", 0);
        frequencies.put("MG", 0);
        frequencies.put("MH", 1);
        frequencies.put("MI", 26);
        frequencies.put("MJ", 0);
        frequencies.put("MK", 0);
        frequencies.put("ML", 0);
        frequencies.put("MM", 5);
        frequencies.put("MN", 3);
        frequencies.put("MO", 28);
        frequencies.put("MP", 16);
        frequencies.put("MQ", 0);
        frequencies.put("MR", 0);
        frequencies.put("MS", 6);
        frequencies.put("MT", 6);
        frequencies.put("MU", 13);
        frequencies.put("MV", 0);
        frequencies.put("MW", 2);
        frequencies.put("MX", 0);
        frequencies.put("MY", 3);
        frequencies.put("MZ", 0);
        //N
        frequencies.put("NA", 54);
        frequencies.put("NB", 7);
        frequencies.put("NC", 31);
        frequencies.put("ND", 118);
        frequencies.put("NE", 64);
        frequencies.put("NF", 8);
        frequencies.put("NG", 75);
        frequencies.put("NH", 9);
        frequencies.put("NI", 37);
        frequencies.put("NJ", 3);
        frequencies.put("NK", 3);
        frequencies.put("NL", 10);
        frequencies.put("NM", 7);
        frequencies.put("NN", 9);
        frequencies.put("NO", 65);
        frequencies.put("NP", 7);
        frequencies.put("NQ", 0);
        frequencies.put("NR", 5);
        frequencies.put("NS", 51);
        frequencies.put("NT", 110);
        frequencies.put("NU", 12);
        frequencies.put("NV", 4);
        frequencies.put("NW", 15);
        frequencies.put("NX", 1);
        frequencies.put("NY", 14);
        frequencies.put("NZ", 0);
        //O
        frequencies.put("OA", 9);
        frequencies.put("OB", 18);
        frequencies.put("OC", 18);
        frequencies.put("OD", 16);
        frequencies.put("OE", 3);
        frequencies.put("OF", 94);
        frequencies.put("OG", 3);
        frequencies.put("OH", 3);
        frequencies.put("OI", 13);
        frequencies.put("OJ", 0);
        frequencies.put("OK", 5);
        frequencies.put("OL", 17);
        frequencies.put("OM", 44);
        frequencies.put("ON", 145);
        frequencies.put("OO", 23);
        frequencies.put("OP", 29);
        frequencies.put("OQ", 0);
        frequencies.put("OR", 113);
        frequencies.put("OS", 37);
        frequencies.put("OT", 53);
        frequencies.put("OU", 96);
        frequencies.put("OV", 13);
        frequencies.put("OW", 36);
        frequencies.put("OX", 0);
        frequencies.put("OY", 4);
        frequencies.put("OZ", 2);
        //P
        frequencies.put("PA", 21);
        frequencies.put("PB", 1);
        frequencies.put("PC", 0);
        frequencies.put("PD", 0);
        frequencies.put("PE", 40);
        frequencies.put("PF", 0);
        frequencies.put("PG", 0);
        frequencies.put("PH", 7);
        frequencies.put("PI", 8);
        frequencies.put("PJ", 0);
        frequencies.put("PK", 0);
        frequencies.put("PL", 29);
        frequencies.put("PM", 0);
        frequencies.put("PN", 0);
        frequencies.put("PO", 28);
        frequencies.put("PP", 26);
        frequencies.put("PQ", 0);
        frequencies.put("PR", 42);
        frequencies.put("PS", 3);
        frequencies.put("PT", 14);
        frequencies.put("PU", 7);
        frequencies.put("PV", 0);
        frequencies.put("PW", 1);
        frequencies.put("PX", 0);
        frequencies.put("PY", 2);
        frequencies.put("PZ", 0);
        //Q
        frequencies.put("QA", 0);
        frequencies.put("QB", 0);
        frequencies.put("QC", 0);
        frequencies.put("QD", 0);
        frequencies.put("QE", 0);
        frequencies.put("QF", 0);
        frequencies.put("QG", 0);
        frequencies.put("QH", 0);
        frequencies.put("QI", 0);
        frequencies.put("QJ", 0);
        frequencies.put("QK", 0);
        frequencies.put("QL", 0);
        frequencies.put("QM", 0);
        frequencies.put("QN", 0);
        frequencies.put("QO", 0);
        frequencies.put("QP", 0);
        frequencies.put("QQ", 0);
        frequencies.put("QR", 0);
        frequencies.put("QS", 0);
        frequencies.put("QT", 0);
        frequencies.put("QU", 20);
        frequencies.put("QV", 0);
        frequencies.put("QW", 0);
        frequencies.put("QX", 0);
        frequencies.put("QY", 0);
        frequencies.put("QZ", 0);
        //R
        frequencies.put("RA", 57);
        frequencies.put("RB", 4);
        frequencies.put("RC", 14);
        frequencies.put("RD", 16);
        frequencies.put("RE", 148);
        frequencies.put("RF", 6);
        frequencies.put("RG", 6);
        frequencies.put("RH", 3);
        frequencies.put("RI", 77);
        frequencies.put("RJ", 1);
        frequencies.put("RK", 11);
        frequencies.put("RL", 12);
        frequencies.put("RM", 15);
        frequencies.put("RN", 12);
        frequencies.put("RO", 54);
        frequencies.put("RP", 8);
        frequencies.put("RQ", 0);
        frequencies.put("RR", 18);
        frequencies.put("RS", 39);
        frequencies.put("RT", 63);
        frequencies.put("RU", 6);
        frequencies.put("RV", 5);
        frequencies.put("RW", 10);
        frequencies.put("RX", 0);
        frequencies.put("RY", 17);
        frequencies.put("RZ", 0);
        //S
        frequencies.put("SA", 75);
        frequencies.put("SB", 13);
        frequencies.put("SC", 21);
        frequencies.put("SD", 6);
        frequencies.put("SE", 84);
        frequencies.put("SF", 13);
        frequencies.put("SG", 6);
        frequencies.put("SH", 30);
        frequencies.put("SI", 42);
        frequencies.put("SJ", 0);
        frequencies.put("SK", 2);
        frequencies.put("SL", 6);
        frequencies.put("SM", 14);
        frequencies.put("SN", 19);
        frequencies.put("SO", 71);
        frequencies.put("SP", 24);
        frequencies.put("SQ", 2);
        frequencies.put("SR", 6);
        frequencies.put("SS", 41);
        frequencies.put("ST", 121);
        frequencies.put("SU", 30);
        frequencies.put("SV", 2);
        frequencies.put("SW", 27);
        frequencies.put("SX", 0);
        frequencies.put("SY", 4);
        frequencies.put("SZ", 0);
        //T
        frequencies.put("TA", 56);
        frequencies.put("TB", 14);
        frequencies.put("TC", 6);
        frequencies.put("TD", 9);
        frequencies.put("TE", 94);
        frequencies.put("TF", 5);
        frequencies.put("TG", 1);
        frequencies.put("TH", 315);
        frequencies.put("TI", 128);
        frequencies.put("TJ", 0);
        frequencies.put("TK", 0);
        frequencies.put("TL", 12);
        frequencies.put("TM", 14);
        frequencies.put("TN", 8);
        frequencies.put("TO", 111);
        frequencies.put("TP", 8);
        frequencies.put("TQ", 0);
        frequencies.put("TR", 30);
        frequencies.put("TS", 32);
        frequencies.put("TT", 53);
        frequencies.put("TU", 22);
        frequencies.put("TV", 4);
        frequencies.put("TW", 16);
        frequencies.put("TX", 0);
        frequencies.put("TY", 21);
        frequencies.put("TZ", 0);
        //U
        frequencies.put("UA", 18);
        frequencies.put("UB", 5);
        frequencies.put("UC", 17);
        frequencies.put("UD", 11);
        frequencies.put("UE", 11);
        frequencies.put("UF", 1);
        frequencies.put("UG", 12);
        frequencies.put("UH", 2);
        frequencies.put("UI", 5);
        frequencies.put("UJ", 0);
        frequencies.put("UK", 0);
        frequencies.put("UL", 28);
        frequencies.put("UM", 9);
        frequencies.put("UN", 33);
        frequencies.put("UO", 2);
        frequencies.put("UP", 17);
        frequencies.put("UQ", 0);
        frequencies.put("UR", 49);
        frequencies.put("US", 42);
        frequencies.put("UT", 45);
        frequencies.put("UU", 0);
        frequencies.put("UV", 0);
        frequencies.put("UW", 0);
        frequencies.put("UX", 1);
        frequencies.put("UY", 1);
        frequencies.put("UZ", 1);
        //W
        frequencies.put("WA", 32);
        frequencies.put("WB", 0);
        frequencies.put("WC", 3);
        frequencies.put("WD", 4);
        frequencies.put("WE", 30);
        frequencies.put("WF", 1);
        frequencies.put("WG", 0);
        frequencies.put("WH", 48);
        frequencies.put("WI", 37);
        frequencies.put("WJ", 0);
        frequencies.put("WK", 0);
        frequencies.put("WL", 4);
        frequencies.put("WM", 1);
        frequencies.put("WN", 10);
        frequencies.put("WO", 17);
        frequencies.put("WP", 2);
        frequencies.put("WQ", 0);
        frequencies.put("WR", 1);
        frequencies.put("WS", 3);
        frequencies.put("WT", 6);
        frequencies.put("WU", 1);
        frequencies.put("WV", 1);
        frequencies.put("WW", 2);
        frequencies.put("WX", 0);
        frequencies.put("WY", 0);
        frequencies.put("WZ", 0);
        //X
        frequencies.put("XA", 3);
        frequencies.put("XB", 0);
        frequencies.put("XC", 5);
        frequencies.put("XD", 0);
        frequencies.put("XE", 1);
        frequencies.put("XF", 0);
        frequencies.put("XG", 0);
        frequencies.put("XH", 0);
        frequencies.put("XI", 4);
        frequencies.put("XJ", 0);
        frequencies.put("XK", 0);
        frequencies.put("XL", 0);
        frequencies.put("XM", 0);
        frequencies.put("XN", 0);
        frequencies.put("XO", 1);
        frequencies.put("XP", 4);
        frequencies.put("XQ", 0);
        frequencies.put("XR", 0);
        frequencies.put("XS", 0);
        frequencies.put("XT", 1);
        frequencies.put("XU", 1);
        frequencies.put("XV", 0);
        frequencies.put("XW", 0);
        frequencies.put("XX", 0);
        frequencies.put("XY", 0);
        frequencies.put("XZ", 0);
        //Y
        frequencies.put("YA", 11);
        frequencies.put("YB", 11);
        frequencies.put("YC", 10);
        frequencies.put("YD", 4);
        frequencies.put("YE", 12);
        frequencies.put("YF", 3);
        frequencies.put("YG", 5);
        frequencies.put("YH", 5);
        frequencies.put("YI", 18);
        frequencies.put("YJ", 0);
        frequencies.put("YK", 0);
        frequencies.put("YL", 6);
        frequencies.put("YM", 4);
        frequencies.put("YN", 3);
        frequencies.put("YO", 28);
        frequencies.put("YP", 7);
        frequencies.put("YQ", 0);
        frequencies.put("YR", 5);
        frequencies.put("YS", 17);
        frequencies.put("YT", 21);
        frequencies.put("YU", 1);
        frequencies.put("YV", 3);
        frequencies.put("YW", 14);
        frequencies.put("YX", 0);
        frequencies.put("YY", 0);
        frequencies.put("YZ", 0);
        //Z
        frequencies.put("ZA", 0);
        frequencies.put("ZB", 0);
        frequencies.put("ZC", 0);
        frequencies.put("ZD", 0);
        frequencies.put("ZE", 5);
        frequencies.put("ZF", 0);
        frequencies.put("ZG", 0);
        frequencies.put("ZH", 0);
        frequencies.put("ZI", 2);
        frequencies.put("ZJ", 0);
        frequencies.put("ZK", 0);
        frequencies.put("ZL", 1);
        frequencies.put("ZM", 0);
        frequencies.put("ZN", 0);
        frequencies.put("ZO", 0);
        frequencies.put("ZP", 0);
        frequencies.put("ZQ", 0);
        frequencies.put("ZR", 0);
        frequencies.put("ZS", 0);
        frequencies.put("ZT", 0);
        frequencies.put("ZU", 0);
        frequencies.put("ZV", 0);
        frequencies.put("ZW", 0);
        frequencies.put("ZX", 0);
        frequencies.put("ZY", 0);
        frequencies.put("ZZ", 1);

        //TRIGRAMAS
        frequencies.put("THE", 1182);
        frequencies.put("ING", 356);
        frequencies.put("AND", 284);
        frequencies.put("ION", 252);
        frequencies.put("ENT", 246);
        frequencies.put("FOR", 191);
        frequencies.put("TIO", 188);
        frequencies.put("ERE", 173);
        frequencies.put("HER", 173);
        frequencies.put("ATE", 165);
        frequencies.put("VER", 159);
        frequencies.put("TER", 157);
        frequencies.put("THA", 155);
        frequencies.put("ATI", 148);
        frequencies.put("HAT", 138);
        frequencies.put("ERS", 135);
        frequencies.put("HIS", 130);
        frequencies.put("RES", 125);
        frequencies.put("ILL", 118);
        frequencies.put("ARE", 117);
        frequencies.put("CON", 114);
        frequencies.put("NCE", 113);
        frequencies.put("ALL", 111);
        frequencies.put("EVE", 111);
        frequencies.put("ITH", 111);
        frequencies.put("TED", 110);
        frequencies.put("AIN", 108);
        frequencies.put("EST", 106);
        frequencies.put("MAN", 101);
        frequencies.put("RED", 101);
        frequencies.put("THI", 100);
        frequencies.put("IVE", 96);
        frequencies.put("REA", 95);
        frequencies.put("WIT", 93);
        frequencies.put("ONS", 92);
        frequencies.put("ESS", 90);
        frequencies.put("AVE", 84);
        frequencies.put("PER", 84);
        frequencies.put("ECT", 83);
        frequencies.put("ONE", 83);
        frequencies.put("UND", 83);
        frequencies.put("INT", 80);
        frequencies.put("ANT", 79);
        frequencies.put("HOU", 77);
        frequencies.put("MEN", 76);
        frequencies.put("WAS", 76);
        frequencies.put("OUN", 75);
        frequencies.put("PRO", 75);
        frequencies.put("STA", 75);
        frequencies.put("INE", 73);
        frequencies.put("WHI", 71);
        frequencies.put("OVE", 71);
        frequencies.put("TIN", 71);
        frequencies.put("AST", 70);
        frequencies.put("DER", 70);
        frequencies.put("OUS", 70);
        frequencies.put("ROM", 70);
        frequencies.put("VEN", 70);
        frequencies.put("ARD", 69);
        frequencies.put("EAR", 69);
        frequencies.put("DIN", 68);
        frequencies.put("STI", 68);
        frequencies.put("NOT", 67);
        frequencies.put("ORT", 67);
        frequencies.put("THO", 66);
        frequencies.put("DAY", 65);
        frequencies.put("ORE", 65);
        frequencies.put("BUT", 64);
        frequencies.put("OUT", 63);
        frequencies.put("URE", 63);
        frequencies.put("STR", 62);
        frequencies.put("TIC", 62);
        frequencies.put("AME", 61);
        frequencies.put("COM", 61);
        frequencies.put("OUR", 61);
        frequencies.put("WER", 61);
        frequencies.put("OME", 60);
        frequencies.put("EEN", 59);
        frequencies.put("LAR", 59);
        frequencies.put("LES", 59);
        frequencies.put("SAN", 59);
        frequencies.put("STE", 59);
        frequencies.put("ANY", 58);
        frequencies.put("ART", 58);
        frequencies.put("NTE", 58);
        frequencies.put("RAT", 58);
        frequencies.put("TUR", 58);
        frequencies.put("ICA", 57);
        frequencies.put("ICH", 57);
        frequencies.put("NDE", 57);
        frequencies.put("PRE", 57);
        frequencies.put("ENC", 56);
        frequencies.put("HAS", 56);
        frequencies.put("WHE", 55);
        frequencies.put("WIL", 55);
        frequencies.put("ERA", 54);
        frequencies.put("LIN", 54);
        frequencies.put("TRA", 54);

        return frequencies;
    }
    */
}
