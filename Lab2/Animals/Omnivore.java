package Lab2.Animals;

public class Omnivore extends Animal {

    public Omnivore(String id, String name, Food food) {
        super(id, name, food);
    }

    public Omnivore(String id, String name) {
        super(id, name);
    }

    public Omnivore(String name, Food food) {
        super(name, food);
    }

    public Omnivore(String name) {
        super(name);
    }

    @Override
    protected Food calculateFood() {
        return new Food("Standard omnivore set", 500);
    }
}
