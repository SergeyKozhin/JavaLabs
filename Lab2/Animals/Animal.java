package Lab2.Animals;

public abstract class Animal {
    private String name;
    private int food;

    Animal(String name) {
        this.name = name;
        this.food = -1;
    }

    abstract int countFood();

    public String getName() {
        return name;
    }

    public int getFood() {
        return (food == -1) ? food = countFood() : food;
    }

    @Override
    public String toString() {
        return "name= " +  getName() + ", type= " + getClass().getSimpleName() + ", food= " + getFood();
    }
}
