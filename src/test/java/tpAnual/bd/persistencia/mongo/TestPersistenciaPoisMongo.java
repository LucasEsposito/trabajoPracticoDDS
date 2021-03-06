package tpAnual.bd.persistencia.mongo;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;
import tpAnual.util.Reseter;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;
import tpAnual.util.wrapper.PointWrapper;

public class TestPersistenciaPoisMongo {

	private static Datastore datastore;

	private PointWrapper ubicacion = new PointWrapper(54, 10);
	private PointWrapper ubicacion2 = new PointWrapper(46, 33);
	
	private Set<String> tags1 = new HashSet<String>();
	private Set<String> tags2 = new HashSet<String>();
	
	private Poi poi1 = new EstacionDeColectivo(ubicacion, "107", tags1,0,"");
	private Poi poi2 = new Negocio(ubicacion2,"donPepe", tags2,"cosas",2);
	
	@BeforeClass
	public static void initClass() throws UnknownHostException{
		datastore = MongoDatastoreSingleton.getDatastore("pois");
	}
	
	@Before
	public void init(){
		Reseter.resetSingletons();
		
		poi1.removerTags();
		poi2.removerTags();
		
		poi1.agregarTag("colectivo");
		poi1.agregarTag("chocador");
		poi2.agregarTag("donPepe");
	}
	
	@After
	public void clear() {
		Reseter.resetDatastore(datastore);
		Reseter.resetSingletons();
	}
	 	
 	@Test
 	public void persistoUnPoi(){
 		datastore.save(poi2);
 		
 		Assert.assertEquals(1, datastore.createQuery(Poi.class).asList().size());
 	}
	
	@Test
	public void persistoDosPoiDistintos(){
		
		datastore.save(poi1);
		datastore.save(poi2);
		Assert.assertEquals(2, datastore.createQuery(Poi.class).asList().size());
	}	
	
	@Test
 	public void sePersistenLosTagsDelPoi(){
 		datastore.save(poi1);
 		Poi poiEncontrado = datastore.createQuery(Poi.class)
 								.filter("nombre","107").asList().get(0);
 		
 		Assert.assertEquals(2, poiEncontrado.getTags().size());
 	}
	
	@Test
 	public void sePersisteLaUbicacionDelPoi(){
 		datastore.save(poi1);
 		Poi poiEncontrado = datastore.createQuery(Poi.class).filter("nombre","107").asList().get(0);
 		Assert.assertTrue((ubicacion.equals(poiEncontrado.getUbicacion())));
 	}

}
