package Lab2.Animals;

public class Herbivore extends Animal {

    public Herbivore(String name) {
        super(name);
    }

    @Override
    int countFood() {
        return 100;
    }
}
