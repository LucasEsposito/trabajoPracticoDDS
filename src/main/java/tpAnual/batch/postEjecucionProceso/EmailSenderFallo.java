package tpAnual.batch.postEjecucionProceso;

import tpAnual.batch.procesos.Proceso;
import tpAnual.util.com.EmailSender;

/**
 * Singleton para avisar por mail al administrador que un proceso fallo mas de N veces consecutivas.
 * Precondicion: El proceso falló.
 */
public class EmailSenderFallo extends EmailSender implements IEmailSenderFallo{
	private static EmailSenderFallo instance = null;
	
	private EmailSenderFallo(){
		//Para evitar que sea instanciada esta clase.
	}
	
	public static EmailSenderFallo getInstance(){
		if(instance==null){
			instance = new EmailSenderFallo();
		}
		return instance;
	}
	
	@Override
	public void accionar(Proceso proceso) {
		enviarMensaje("Proceso fallido",
				"El proceso "+proceso.getNombre()
				+" falló");
	}
}
