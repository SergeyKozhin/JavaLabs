package Lab2.Animals;

public class Herbivore extends Animal {

    public Herbivore(String id, String name, Food food) {
        super(id, name, food);
    }

    public Herbivore(String name, Food food) {
        super(name, food);
    }

    public Herbivore(String name) {
        super(name);
    }

    public Herbivore(String id, String name) {
        super(id, name);
    }

    @Override
    protected Food calculateFood() {
        return new Food("Standard herbivore set", 200);
    }
}
