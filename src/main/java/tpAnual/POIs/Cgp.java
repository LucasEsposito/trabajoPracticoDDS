package tpAnual.POIs;

import java.util.*;

import javax.persistence.Transient;

import org.uqbar.geodds.*;

public class Cgp extends PoiConServicios {


	@Transient //TODO: sacar y q la comuna sea un nro!
	private Polygon comuna;


	public Cgp(Point ubicacion, String nombre, Set<String> tags, List<Point> puntosComuna) {
		super(ubicacion, nombre, tags);
		comuna =new Polygon(puntosComuna);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean estaCerca(Point ubicacion) {
		return comuna.isInside(ubicacion) && comuna.isInside(this.getUbicacion());
	}
	
}
