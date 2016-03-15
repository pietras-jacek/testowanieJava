package wej01;

import java.util.ArrayList;
import java.util.List;

public class FlowerManager {

	
	List<Flower> flowers = new ArrayList<Flower>();
	
	public void add(Flower f) {
		flowers.add(f);
	}
	
	public List<Flower> getAllFlowers() {
		return flowers;
	}
	
}
