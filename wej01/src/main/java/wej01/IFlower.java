package wej01;

import java.util.List;

public interface IFlower {
    public void addFlower(Flower f);
    public void removeFlower(Flower f);
    public List<Flower> getAllFlowers();
}