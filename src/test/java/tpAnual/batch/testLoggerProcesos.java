package tpAnual.batch;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.Terminal;
import tpAnual.batch.postEjecucionProceso.LoggerProcesos;
import tpAnual.batch.postEjecucionProceso.ReLanzador;
import tpAnual.batch.procesos.ActivacionEnTodas;
import tpAnual.batch.procesos.ActivacionPorComuna;
import tpAnual.batch.procesos.Proceso;
import tpAnual.util.Reseter;

public class testLoggerProcesos {
	private Set<Terminal> terminales;
	private Set<String> activar;
	private Set<String> desactivar;
	private int limite;
	private Proceso proceso;
	
	@Before
	public void init(){
		Reseter.resetSingletons();
		terminales = new HashSet<>();
		activar = new HashSet<>();
		desactivar = new HashSet<>();
		limite=2;
		terminales.add(new Terminal(0));
		activar.add("Mail");
		desactivar.add("Registro");
				
		proceso = new ActivacionPorComuna(0, null);
		proceso.agregarAccionPostFallo(ReLanzador.ReLanzadorSinMail(limite));
	}
	
	@After
	public void finalizar(){
		Lanzador.resetSingleton();
		LoggerProcesos.resetSingleton();
	}
	
	@Test
	public void registraEventosFallidosSinRelanzador(){
		proceso = new ActivacionEnTodas(null);
		Lanzador.getInstance().ejecutarProceso(proceso);
		Assert.assertEquals(1, LoggerProcesos.getInstance().getResultados().size(),0);
	}
	
	@Test
	public void registraEventosFallidosConRelanzador(){
		Proceso proceso1 = new ActivacionEnTodas(null);
		ReLanzador relanzador = ReLanzador.ReLanzadorSinMail(3);
		proceso1.agregarAccionPostFallo(relanzador);
		Lanzador.getInstance().ejecutarProceso(proceso1);
		Assert.assertEquals(1+3, LoggerProcesos.getInstance().getResultados().size(),0);
	}
	
	@Test
	public void registraEventosExitosos(){
		proceso = new ActivacionEnTodas(null);
		Lanzador.getInstance().ejecutarProceso(proceso);
		Assert.assertEquals(1, LoggerProcesos.getInstance().getResultados().size(),0);
	}
}
