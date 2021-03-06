package tpAnual.externo.sistemasExternos;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

@Entity
public class BancoDTO{
	
	@Embedded
    private String[] servicios;
    private String banco;
    private String y;
    private String x;

	public String[] getServicios() {
		return servicios;
	}

	public void setServicios(String[] servicios) {
		this.servicios = servicios;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}
}