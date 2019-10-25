package Lab2.Animals;

public class Omnivore extends Animal {

    public Omnivore(String name) {
        super(name);
    }

    @Override
    int countFood() {
        return 500;
    }
}
