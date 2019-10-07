package Lab1.TaskC;

class Book {
    private int id;
    private String author;
    private String name;
    private int year;

    Book(int id, String author, String name, int year) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.year = year;
    }

    @Override
    public String toString() {
        return "" + id + ", " + author + ", " + name + ", " + year;
    }

    int getId() {
        return id;
    }

    String getAuthor() {
        return author;
    }

    String getName() {
        return name;
    }

    int getYear() {
        return year;
    }
}

