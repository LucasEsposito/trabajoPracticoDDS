package tpAnual.externo.sistemasExternos;

public class ServicioDTO {
	private String nombre;
	private int dia;
	private int horaApertura;
	private int minutoApertura;
	private int horaCierre;
	private int minutoCierre;
	
	public ServicioDTO(String nombre, int dia, int horaApertura, int minApertura, int horaCierre, int minCierre){
		this.nombre = nombre;
		this.dia = dia;
		this.horaApertura = horaApertura;
		this.minutoApertura = minApertura;
		this.horaCierre = horaCierre;
		this.minutoCierre = minCierre;
	}
	
	public int getDia(){
		return dia;
	}
	public String getNombre(){
		return nombre;
	}
	public int getHoraApertura(){
		return horaApertura;
	}
	public int getMinutoApertura(){
		return minutoApertura;
	}
	public int getHoraCierre(){
		return horaCierre;
	}
	public int getMinutoCierre(){
		return minutoCierre;
	}
}
