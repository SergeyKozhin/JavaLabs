package Lab2;

import Lab2.Animals.Animal;
import Lab2.Animals.Carnivore;
import Lab2.Animals.Herbivore;
import Lab2.Animals.Omnivore;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Animal> list = new LinkedList<>();
        list.add(new  Carnivore("Wolf"));
        list.add(new Omnivore("Bear"));
        list.add(new Herbivore("Cow"));

        list.forEach(System.out::println);
    }
}
