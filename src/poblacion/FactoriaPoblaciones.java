package poblacion;


public class FactoriaPoblaciones {
	public static Poblacion getPoblacion(String choice,int tam, String ejercicio,double elitismo, double trunk, String texto, String choice_mut) {
		switch(choice) {
		case("RULETA"):
			return new PoblacionRuleta(tam, ejercicio,elitismo,texto,choice_mut);
		case("UNIVERSAL"):
			return new PoblacionUniversalEstocastica(tam, ejercicio,elitismo,texto,choice_mut);
		case("TRUNCAMIENTO"):
			return new PoblacionTruncamiento(tam, ejercicio,elitismo,trunk,texto, choice_mut);
		case("TORNEO DETERMINISTA"):
			return new PoblacionTruncamiento(tam, ejercicio,elitismo,trunk,texto,choice_mut);    
        case("TORNEO PROBABILISTA"):
            return new PoblacionTruncamiento(tam, ejercicio,elitismo,trunk,texto,choice_mut);
		default:
			return new PoblacionRuleta(tam, ejercicio,elitismo,texto,choice_mut);
		}
	}
}
