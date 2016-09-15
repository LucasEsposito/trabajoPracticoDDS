package tpAnual;

import tpAnual.acciones.RepositorioRegistros;
import tpAnual.batch.Lanzador;
import tpAnual.batch.errorCatch.LoggerProcesos;

public class SingletonReseter {
	public static void resetAll(){
		Mapa.resetSingleton();
		RepositorioRegistros.resetSingleton();
		RepositorioBuscador.resetSingleton();
		LoggerProcesos.resetSingleton();
		Lanzador.resetSingleton();
	}
}
