package Lab2.Animals;

public class Carnivore extends Animal {

    public Carnivore(String id, String name, Food food) {
        super(id, name, food);
    }

    public Carnivore(String id, String name) {
        super(id, name);
    }

    public Carnivore(String name, Food food) {
        super(name, food);
    }

    public Carnivore(String name) {
        super(name);
    }

    @Override
    protected Food calculateFood() {
        return new Food("Standard carnivore set", 1000);
    }
}
