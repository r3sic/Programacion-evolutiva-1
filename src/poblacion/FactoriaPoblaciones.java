package poblacion;


public class FactoriaPoblaciones {
	public static Poblacion getPoblacion(String choice,int tam, String ejercicio, double precision, double trunk, double elitismo, int num_fen) {
		switch(choice) {
		case("RULETA"):
			return new PoblacionRuleta(tam, ejercicio,precision,elitismo, num_fen);
		case("UNIVERSAL"):
			return new PoblacionUniversalEstocastica(tam, ejercicio, precision,elitismo, num_fen);
        case("TRUNCAMIENTO"):
        	return new PoblacionTruncamiento(tam, ejercicio, precision,trunk,elitismo, num_fen);
        case("TORNEO DETERMINISTA"):
        	return new PoblacionTorneoDeterministico(tam, ejercicio, precision,elitismo, num_fen);
        case("TORNEO PROBABILISTA"):
        	return new PoblacionTorneoProbabilistico(tam, ejercicio, precision,trunk,elitismo, num_fen);
		default:
			return new PoblacionRuleta(tam, ejercicio,precision,elitismo, num_fen);
		}
	}
}
