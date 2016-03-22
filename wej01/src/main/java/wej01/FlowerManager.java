package wej01;

import java.util.ArrayList;
import java.util.List;

public class FlowerManager implements IFlower{
    List<Flower> flowers = new ArrayList<Flower>();

    public void addFlower(Flower f) {
        flowers.add(f);
    }

    public void removeFlower(Flower f) {
        flowers.remove(f);
    }

    public List<Flower> getAllFlowers() {
        return flowers;
    }
}
