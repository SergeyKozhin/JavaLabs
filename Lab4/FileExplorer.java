package Lab4;

import java.io.*;
import java.nio.file.*;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

@FunctionalInterface
interface Command {
    void apply(FileExplorer fe, Scanner sc);
}

public class FileExplorer {
    private Scanner in;
    private PrintStream out;
    private Path currentPath = Paths.get("").toAbsolutePath();
    private final static Map<String, Command> commands = Map.of(
            "cd", FileExplorer::goTo,
            "ls", FileExplorer::listDirectory,
            "read", FileExplorer::printFile,
            "touch", FileExplorer::createFile,
            "write", FileExplorer::writeToFile,
            "rm", FileExplorer::deleteFile
    );

    public FileExplorer(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void start() {
        out.print(currentPath + ": ");

        while (in.hasNextLine()) {
            execute(in.nextLine());
        }
    }

    public void execute(String command) {
        Scanner line = new Scanner(command.strip());

        if (line.hasNext()) {
            commands.getOrDefault(line.next(), (fe, sc) -> out.println("No such command")).apply(this, line);

            if (line.hasNextLine()) {
                out.println("Redundant arguments: " + line.nextLine().strip());
            }
        }

        out.print(currentPath + ": ");
    }

    private void goTo(Scanner line) {
        try {
            Path path = extractPath(line);
            if (!Files.isDirectory(path)) {
                out.println("Not a directory: " + path);
                return;
            }

            currentPath = path;
        } catch (InvalidPathException e) {
            out.println("No such path: " + e.getInput());
        }
    }

    private void listDirectory(Scanner line) {
        try {
            Path path = extractPath(line);
            if (!Files.isDirectory(path)) {
                out.println("Not a directory: " + path);
                return;
            }

            File dir = new File(path.toUri());
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    out.println("Dir:\t" + file.getName());
                } else {
                    out.println("File:\t" + file.getName());
                }
            }
        } catch (InvalidPathException e) {
            out.println("No such path: " + e.getInput());
        }
    }

    private void printFile(Scanner line) {
        try (BufferedReader bf = new BufferedReader(new FileReader(extractPath(line).toFile()))) {
            String fileLine;
            while ((fileLine = bf.readLine()) != null) {
                out.println(fileLine);
            }
        } catch (InvalidPathException e) {
            out.println("No such path: " + e.getInput());
        } catch (FileNotFoundException e) {
            out.println("Not a file: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFile(Scanner line) {
        if (!line.hasNext()) {
            out.println("No file name provided!");
            return;
        }

        File newFile = new File(currentPath.resolve(line.next()).normalize().toUri());
        Path parent = Paths.get(newFile.getParent());
        if (Files.notExists(parent) || !Files.isDirectory(parent)) {
            out.println("No such directory: " + parent);
            return;
        }
        try {
            if (!newFile.createNewFile()) {
                out.println("File already exists: " + newFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(Scanner line) {
        if (!line.hasNext()) {
            out.println("No file name provided!");
            return;
        }

        Path file = currentPath.resolve(line.next());
        if (Files.notExists(file.getParent()) || !Files.isDirectory(file.getParent())) {
            out.println("No such directory: " + file.getParent());
            return;
        }
        try {
            Files.write(file, Collections.singletonList(line.nextLine().strip()),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFile(Scanner line) {
        try {
            Path path = extractPath(line);
            if (!Files.deleteIfExists(path)) {
                out.println("No such file: " + path);
            }
        } catch (DirectoryNotEmptyException e) {
            out.println("Directory is not empty: " + e.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path extractPath(Scanner line) {
        if (line.hasNext()) {
            Path path = currentPath.resolve(line.next()).normalize();
            if (Files.notExists(path)) {
                throw new InvalidPathException(path.toString(), "No such path!");
            }
            return path;
        }
        return currentPath;
    }
}
