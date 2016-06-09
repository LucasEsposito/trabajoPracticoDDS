package tpAnual.reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReporteFecha{ //Ej llamada: generadorReportes.reporteFecha(mapa.buscadorTexto.getRegistros());

	public void reportar(List<RegistroBusqueda> registros){
		List<ElementoReporte> listadoReporte = new ArrayList<ElementoReporte>();
		
		registros.forEach(registro->{
					agregarSiNoEstaFecha(registro,listadoReporte);
					buscarElementoYSumarBusqueda(registro,listadoReporte);
		});
		
		mostrarReporteFecha(listadoReporte);
	}
	
	private void mostrarReporteFecha(List<ElementoReporte> listadoReporte){
		listadoReporte.forEach(elemento->{
						System.out.println(elemento.fecha.toString());
						System.out.println(elemento.cantidadBusquedas);
					}
				);
	}
	
	private void buscarElementoYSumarBusqueda(RegistroBusqueda registro,List<ElementoReporte> listadoReporte){
		listadoReporte.stream()
				.filter(elemento->elemento.fecha.toString().equals(registro.fecha.toString()))
				.findFirst()
				.get()
				.sumarBusqueda();
	}
	
	private void agregarSiNoEstaFecha(RegistroBusqueda registro,List<ElementoReporte> listadoReporte){
		if(!this.estaLaFecha(registro.fecha, listadoReporte)){
			ElementoReporte elemento = new ElementoReporte();
			elemento.setFecha(registro.fecha);
			listadoReporte.add(elemento);
		}
	}
	
	private Boolean estaLaFecha(LocalDate fecha, List<ElementoReporte> listadoReporte){
		return listadoReporte.stream()
				.anyMatch(elemento-> elemento.fecha.toString().equals(fecha.toString()));
	}
	
}
