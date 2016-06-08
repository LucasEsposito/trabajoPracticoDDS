package tpAnual;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroBusqueda {
	
	List<String> palabrasUtilizadas = new ArrayList<String>();
	Long tiempoBusqueda;
	LocalDate fecha;
	Integer cantidadEncontrada;
	Terminal terminal;
	
	public RegistroBusqueda(List<Poi> pois, List<String> palabras, Long tiempo, Terminal terminalParam){
		palabrasUtilizadas.addAll(palabras);
		cantidadEncontrada = pois.size();
		tiempoBusqueda = tiempo;
		fecha = LocalDate.now();
		terminal = terminalParam;
	}
	
}