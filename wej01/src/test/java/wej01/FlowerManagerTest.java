package wej01;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FlowerManagerTest {

	FlowerManager flower = new FlowerManager();
	
	
	@Before
	public void initializeTestEnv() throws Exception {
	}
	
	@Test
	public void checkAdd() {
		assertEquals(flower.getName(), flower.addFlower(null));	
	}

}
