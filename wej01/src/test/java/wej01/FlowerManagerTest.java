package wej01;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FlowerManagerTest {
    Flower fl = new Flower("Mlecz");

    FlowerManager flm = new FlowerManager();

    @Test
    public void checkAdd() {
        flm.addFlower(fl);
        assertEquals(fl.getName(), flm.getAllFlowers().get(0).getName());
    }

    @Test
    public void checkDel() {
        
        assertTrue(flm.getAllFlowers().isEmpty());
     
        flm.addFlower(fl);

        
        assertFalse(flm.getAllFlowers().isEmpty());

        flm.removeFlower(fl);
       
        assertTrue(flm.getAllFlowers().isEmpty());
       
        assertEquals(0, flm.getAllFlowers().size());
    }
}
