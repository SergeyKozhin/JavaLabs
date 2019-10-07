package Lab1.TaskC;

import java.util.ArrayList;

class Library {
    private ArrayList<Book> library;

    Library(ArrayList<Book> library) {
        this.library = library;
    }
    Library() {
        this(new ArrayList<>());
    }

    void add(Book book) {
        for (int i = 0; i < library.size(); ++i) {
            if (library.get(i).getId() > book.getId()) {
                library.add(i, book);
                return;
            }
        }
        library.add(book);
    }

    Book get(int id) {
        for (Book book : library) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    void update(Book book) {
        for (int i = 0; i < library.size(); ++i) {
            if (library.get(i).getId() == book.getId()) {
                library.set(i, book);
                return;
            }
        }
        add(book);
    }

    boolean delete(int id) {
        for (int i = 0; i < library.size(); ++i) {
            if (library.get(i).getId() == id) {
                library.remove(i);
                return true;
            }
        }
        return false;
    }

    Library searchByAuthor(String author) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        for (Book book : library) {
            if (book.getAuthor().equals(author)) {
                foundBooks.add(book);
            }
        }
        return new Library(foundBooks);
    }

    Library searchByName(String name) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        for (Book book : library) {
            if (book.getName().equals(name)) {
                foundBooks.add(book);
            }
        }
        return new Library(foundBooks);
    }

    Library searchByYear(int year) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        for (Book book : library) {
            if (book.getYear() == year) {
                foundBooks.add(book);
            }
        }
        return new Library(foundBooks);
    }

    void printAll() {
        for (Book book : library) {
            System.out.println(book);
        }
    }
}

