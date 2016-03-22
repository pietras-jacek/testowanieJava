package lab04;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;


public class FlowerManagerTest {
	
    Flower fl = new Flower("Mlecz");
  
    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);
    
    @Mock
    private IMyList mock;
    
    @TestSubject
    private FlowerManager flMan = new FlowerManager(mock);
    
    @Test
    public void checkAdding() {
    	Flower f = new Flower("Malina");
    	mock.add(f);
    	
    	expectLastCall();
    	expect(mock.size()).andReturn(1);
    	expect(mock.getAllFlowers()).andReturn(mock);
    	replay(mock);
    	flMan.add(f);
    	
    	assertEquals(1, flMan.getAllFlowers().size());
    	verify(mock);
    }
    
}
