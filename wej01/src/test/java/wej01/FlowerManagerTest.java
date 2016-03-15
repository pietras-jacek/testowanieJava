package wej01;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FlowerManagerTest {

	Flower fl = new Flower("Mlecz");
	
	FlowerManager flm = new FlowerManager();
	
	@Test
	public void checkAdd() {
		flm.add(fl);
		assertEquals(fl.getName(), flm.getAllFlowers().get(0).getName());
	}

}
