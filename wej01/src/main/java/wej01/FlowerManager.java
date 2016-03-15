package wej01;


import java.util.ArrayList;
import java.util.List;

public class FlowerManager {
	private String name;

	List<String> flowerList = new ArrayList<String>();
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public void addFlower(String name) {
		int length = flowerList.size();
		for (int i= 0; i < length; i++) {
			String s = flowerList.get(i);
		}
	}
	
	public List<String> showFlowers() {
		return flowerList;
	}

}
