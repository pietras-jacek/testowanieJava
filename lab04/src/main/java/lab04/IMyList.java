package lab04;

import java.util.List;

public interface IMyList {
	public void add(Flower f);

	public Flower get(int index);

	public IMyList getAllFlowers();

	public int size();
}
