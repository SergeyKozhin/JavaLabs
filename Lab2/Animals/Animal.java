package Lab2.Animals;

import java.io.Serializable;
import java.util.UUID;

public abstract class Animal implements Serializable {
    private String id;
    private String name;
    private Food food;

    Animal(String id, String name, Food food) {
        this.id = id;
        this.name = name;
        this.food = food;
    }

    Animal(String id, String name) {
        this(id, name, null);
    }

    Animal(String name, Food food) {
        this(UUID.randomUUID().toString(), name, food);
    }

    Animal(String name) {
        this(name, (Food) null);
    }

    abstract protected Food calculateFood();

    @Override
    public String toString() {
        return String.format("id = %s, name =  %s, type of food = %s, amount of food = %d",
                getId(), getName(), getFood().getType(), getFood().getAmount());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Food getFood() {
        return (food == null) ? food = calculateFood() : food;
    }

    public static class Food implements Serializable {
        private String type;
        private int amount;

        public Food(String type, int amount) {
            this.type = type;
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public int getAmount() {
            return amount;
        }
    }

}
