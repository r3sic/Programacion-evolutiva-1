package cromosoma;

public class FactoriaCromosoma {
	/*public static Cromosoma getCromosoma(String choice, double precision, int num_fen) {
		switch(choice) {
		case("EJ1"):
			return new CromosomaF1(precision);
		case("EJ2"):
			return new CromosomaF2(precision);
		case("EJ3"):
			return new CromosomaF3(precision);
		case("EJ4"):
			return new CromosomaF4(precision);
		case("EJ5"):
			return new CromosomaF5(precision, num_fen);
		default:
			return new CromosomaF1(precision);
		}
	}*/
	public static Cromosoma getCromosomaCopia(Cromosoma c) {
                String choice = c._tipoCrom;
		switch(choice) {
		case("CICLOS"):
			return new CromosomaCruceCiclos( c);
		case("OX"):
			return new CromosomaCruceOX( c);
		case("PMX"):
			return new CromosomaCrucePMX( c);
                case("CODORDINAL"):
                        return new CromosomaCruceCodOrdinal(c);
		default:
			return new CromosomaCruceCiclos( c);
		}
	}
}
