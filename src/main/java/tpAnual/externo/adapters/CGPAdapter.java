package tpAnual.externo.adapters;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

import tpAnual.Horario;
import tpAnual.Servicio;
import tpAnual.POIs.Cgp;
import tpAnual.POIs.Poi;
import tpAnual.externo.mocks.MockSistemaCGP;
import tpAnual.externo.sistemasExternos.CentroDTO;
import tpAnual.externo.sistemasExternos.Consultora;
import tpAnual.externo.sistemasExternos.ServicioDTO;

public class CGPAdapter implements Consultora{
	
	private MockSistemaCGP cpoExterno = new MockSistemaCGP();
		
		
	// Consultar es un metodo de la interface externa que me da el JSON que esto debe adaptar
	public List<Poi> consultar(List<String> palabras){
		List<Poi> pois = new ArrayList<Poi>();
		palabras.forEach(palabra-> pois.addAll(this.adaptar(cpoExterno.consultar(palabra))));
		return pois;
	}
	
	private List<Poi> adaptar(List<CentroDTO> centros){
		return centros
				.stream()
				.map(centro -> centroToPOI(centro))
				.collect(Collectors.toList());
	}
	
	
	private Poi centroToPOI(CentroDTO centro){
		Cgp cgp = new Cgp(null); //TODO HACER UNA LISTA DE COMUNAS ASOCIADAS CON SU RECTANGULO PARA TRABSFORMAR NUMERO DE COMUNA A RECTANGGULO
		Point ubicacion = new Point(-35.9345681,72.344546); 
		String nombre = centro.getDirector();
		Set<String> tags = centro.getZonas();
		
		centro.getServicios().forEach(servDto-> cgp.agregarServicio( convertirAServicioCgp(servDto)));
		
		Poi nuevoPoi = new Poi(cgp,ubicacion,nombre,tags);
		return nuevoPoi;
	}
	
	private Servicio convertirAServicioCgp(ServicioDTO servDto){
		LocalTime desde = LocalTime.of(servDto.getHoraApertura(),servDto.getMinutoApertura());		//junta la hora y el minuto en LocalTime
		LocalTime hasta = LocalTime.of(servDto.getHoraCierre(),servDto.getMinutoCierre());
		DayOfWeek dia = DayOfWeek.of(servDto.getDia());
		
		Horario horario = Horario.nuevoHorarioParaDia(dia,desde,hasta);
		
		Servicio servicioCgp = new Servicio(servDto.getNombre());
		servicioCgp.agregarHorario(horario);
		
		return servicioCgp;
	}
}
