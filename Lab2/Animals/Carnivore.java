package Lab2.Animals;

public class Carnivore extends Animal {

    public Carnivore(String name) {
        super(name);
    }

    @Override
    int countFood() {
        return 1000;
    }
}
