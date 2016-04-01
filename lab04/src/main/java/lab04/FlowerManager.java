package lab04;


public class FlowerManager {

	private IMyList container;

	public FlowerManager(IMyList ifm) {
		this.container = ifm;
	}

	public void add(Flower f) {
		container.add(f);
	}

	public IMyList getAllFlowers() {
		return container.getAllFlowers();
	}
}