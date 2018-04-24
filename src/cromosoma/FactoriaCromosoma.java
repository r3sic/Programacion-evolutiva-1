package cromosoma;

public class FactoriaCromosoma {
	public static Cromosoma getCromosoma(String cruce) {
		switch(cruce) {
		case("CICLOS"):
			return new CromosomaCruceCiclos();
		case("OX"):
			return new CromosomaCruceOX();
		case("PMX"):
			return new CromosomaCrucePMX();
        case("CODORDINAL"):
            return new CromosomaCruceCodOrdinal();
		default:
			return new CromosomaCruceCiclos();
		}
	}
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
