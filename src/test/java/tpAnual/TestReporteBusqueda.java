package tpAnual;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class TestReporteBusqueda extends TestBuscador{
		
	@Test
	public void testSeAlmacenaLaCantidad(){
			
		Assert.assertEquals(pois.size(), registro.getCantidad(),0);
	}
	
	@Test
	public void testSeAlmacenaLaFecha(){
		Assert.assertEquals(LocalDate.now(), registro.getFecha());
	}
	
	@Test
	public void testSeAlmacenaLasPalabaras(){
		List <String> comp = new ArrayList<>();
		comp.add("colectivo");
		Assert.assertTrue(registro.getPalabras().containsAll(comp));
	}
	
	@Test
	public void testSeAlmacenaElTerminal(){
		
		Assert.assertEquals(terminal,registro.getTerminal());
	}
	
}
