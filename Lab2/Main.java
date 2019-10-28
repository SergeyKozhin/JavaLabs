package Lab2;

import Lab2.Animals.Animal;
import Lab2.Animals.Carnivore;
import Lab2.Animals.Herbivore;
import Lab2.Animals.Omnivore;

import java.io.*;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Animal> list = new LinkedList<>();
        list.add(new Carnivore("1", "Wolf", new Animal.Food("Meat", 600)));
        list.add(new Omnivore("2", "Bear"));
        list.add(new Herbivore("Cow", new Animal.Food("Hay", 1000)));
        list.add(new Herbivore("Sheep"));
        list.add(new Carnivore("Tiger"));
        list.add(new Omnivore("Mouse", new Animal.Food("Seeds", 100)));

        // Sorting
        list.sort((a, b) -> (a.getFood() != b.getFood()) ?
                a.getFood().getAmount() - b.getFood().getAmount() :
                a.getName().toLowerCase().compareTo(b.getName().toLowerCase()));

        // All list
        list.forEach(System.out::println);
        System.out.println();
        // First 5 names
        list.subList(0, 5).forEach((x) -> System.out.println(x.getName()));
        System.out.println();
        // Last 3 IDs
        list.subList(list.size() - 3, list.size()).forEach((x) -> System.out.println(x.getId()));
        System.out.println();

        // Writing to file
        write(list, "animals");

        // Reading from file
        LinkedList<Animal> listFromFile = new LinkedList<>();
        read(listFromFile, "animals");
        listFromFile.forEach(System.out::println);
    }

    static void write(LinkedList<Animal> list, String fileName) {
        try {
            ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(fileName));
            for (Animal animal : list) {
                objectOut.writeObject(animal);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open file.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error while writing to file.");
            e.printStackTrace();
        }
    }

    static void read(LinkedList<Animal> list, String fileName) {
        try {
            ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(fileName));
            while (true) {
                try {
                    list.add((Animal) objectIn.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open file.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error while reading from file.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("File contains wrong objects.");
            e.printStackTrace();
        }
    }
}
