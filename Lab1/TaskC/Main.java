package Lab1.TaskC;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    private static Scanner in = new Scanner(new InputStreamReader(System.in));

    public static void main(String[] args) {
        Library library = new Library();

        System.out.println("Enter number for one of following commands:");
        System.out.println("\t1. Add book to library.");
        System.out.println("\t2. Get  book entry from library");
        System.out.println("\t3. Update book in library.");
        System.out.println("\t4. Delete book from library.");
        System.out.println("\t5. Search books by author.");
        System.out.println("\t6. Search books by name.");
        System.out.println("\t7. Search books by year of issue.");
        System.out.println("\t8. Print out all books.");
        System.out.println("\t0. Exit.");
        loop: while (true) {
            System.out.print("Command: ");
            switch (in.nextInt()){
                case 0:
                    System.out.println("Exiting.");
                    break loop;
                case 1:
                    System.out.println("Enter book information.");
                    library.add(readBook());
                    System.out.println("Book added.");
                    break;
                case 2:
                    System.out.println("Enter book id.");
                    Book found = library.get(in.nextInt());
                    if (found == null)
                        System.out.println("Book was not found.");
                    else
                        System.out.println(found);
                    break;
                case 3:
                    System.out.println("Enter updated book info.");
                    library.update(readBook());
                    System.out.println("Book updated.");
                    break;
                case 4:
                    System.out.println("Enter book id to delete.");
                    if (library.delete(in.nextInt()))
                        System.out.println("Book successfully deleted.");
                    else
                        System.out.println("Book was not found.");
                    break;
                case 5:
                    System.out.println("Enter author name to search by.");
                    Library resultByAuthor = library.searchByAuthor(in.nextLine());
                    System.out.println("Books found:");
                    resultByAuthor.printAll();
                    break;
                case 6:
                    System.out.println("Enter book name to search by.");
                    Library resultByName = library.searchByName(in.nextLine());
                    System.out.println("Books found:");
                    resultByName.printAll();
                    break;
                case 7:
                    System.out.println("Enter year of issue to search by.");
                    Library resultByYear = library.searchByYear(in.nextInt());
                    System.out.println("Books found:");
                    resultByYear.printAll();
                    break;
                case 8:
                    System.out.println("All books in library:");
                    library.printAll();
                    break;
                default:
                    System.out.println("Unknown command.");
            }
        }
    }

    static Book readBook() {
        System.out.print("ID = ");
        int id = in.nextInt();
        in.nextLine();
        System.out.print("Author = ");
        String auth = in.nextLine();
        System.out.print("Name = ");
        String name = in.nextLine();
        System.out.print("Year = ");
        int year = in.nextInt();
        in.nextLine();
        return new Book(id, auth, name, year);
    }
}
