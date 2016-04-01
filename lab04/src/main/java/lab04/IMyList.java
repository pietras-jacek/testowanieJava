package lab04;

public interface IMyList {
	public void add(Flower f);

	public Flower get(int index);

	public IMyList getAllFlowers();

	public int size();
}
