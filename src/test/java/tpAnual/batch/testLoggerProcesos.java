package tpAnual.batch;

import java.util.HashSet;
import java.util.Set;
import org.junit.*;

import tpAnual.Terminal;
import tpAnual.batch.Lanzador;
import tpAnual.batch.observers.LoggerProcesos;
import tpAnual.batch.observers.ReLanzador;
import tpAnual.batch.procesos.Proceso;
import tpAnual.batch.procesos.ProcesoActivadorAcciones;

public class testLoggerProcesos {
	private Set<Terminal> terminales;
	private Set<String> activar;
	private Set<String> desactivar;
	private int limite;
	private Proceso proceso;
	
	@Before
	public void init(){
		Lanzador.resetSingleton();
		LoggerProcesos.resetSingleton();
		terminales = new HashSet<>();
		activar = new HashSet<>();
		desactivar = new HashSet<>();
		limite=2;
		terminales.add(new Terminal(0));
		activar.add("Mail");
		desactivar.add("Registro");
				
		proceso = ProcesoActivadorAcciones.EnComuna(0, activar, desactivar);
		proceso.agregarObservador(ReLanzador.ReLanzadorSinMail(limite));
	}
	
	@After
	public void finalizar(){
		Lanzador.resetSingleton();
		LoggerProcesos.resetSingleton();
	}
	
	@Test
	public void registraEventosFallidosSinRelanzador(){
		proceso = ProcesoActivadorAcciones.EnTodos(activar, new HashSet<>());
		Lanzador.getInstance().ejecutarProceso(proceso);
		Assert.assertEquals(1, LoggerProcesos.getInstance().getResultados().size(),0);
	}
	
	@Test
	public void registraEventosFallidosConRelanzador(){
		Proceso proceso1 = ProcesoActivadorAcciones.EnTodos(activar, null);
		ReLanzador relanzador = ReLanzador.ReLanzadorSinMail(3);
		proceso1.agregarObservador(relanzador);
		Lanzador.getInstance().ejecutarProceso(proceso1);
		Assert.assertEquals(1+3, LoggerProcesos.getInstance().getResultados().size(),0);
	}
	
	@Test
	public void registraEventosExitosos(){
		proceso = ProcesoActivadorAcciones.EnTodos(activar, desactivar);
		Lanzador.getInstance().ejecutarProceso(proceso);
		Assert.assertEquals(1, LoggerProcesos.getInstance().getResultados().size(),0);
	}
}
