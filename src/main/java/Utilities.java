import java.io.*;
import java.util.ArrayList;

class Utilities {

    /**
     *
     * Prints a String to the console
     *
     * @param newString The string to be printed
     */
    static void print(String newString) {
        System.out.print(newString);
    }

    /**
     *
     * Prints a String to the console
     *
     * @param newString The string to be printed
     */
    static void println(String newString) {
        System.out.println(newString);
    }

    private static BufferedReader findFile(File file, String path) {
        BufferedReader reader = null;
        try {
           reader = new BufferedReader(new FileReader(path));
        }
        catch(FileNotFoundException e) {
            println("Couldn't find file\n    Creating the file...");
            try {
                // If the file does not exist, try and create the file
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            catch (IOException e2) {
                println("Couldn't create file");
            }
            println("    Created file");
        }
        return reader;
    }

    /**
     *
     * Takes a file and stores its contents in a list
     *
     * @param file The file to be read
     * @param path The path to the specified file
     * @param list The list that the file contents should be stored in
     */
    static ArrayList<String> readUsersFile(File file, String path, ArrayList<String> list) {
        // Create a new BufferedReader that will go onto read the file's contents
        BufferedReader reader = findFile(file, path);

        // Stores the current line in the file
        String line;

        try {
            // While the current line is not empty, read it and store it in the list
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            reader.close();
        }
        catch (NullPointerException | IOException e) {
            println("Couldn't read users file\n    The file may be empty");
        }
        return list;
    }

    /**
     *
     * Write a list to a file
     *
     * @param path The path for the new file
     * @param list The list which holds the content
     */
    static void writeUsersFile(File file, String path, ArrayList<String> list) {
        try {
            // Create a new BufferedWrite to write to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path), false));
            // For every item in the list, write it to the file and then add a line break
            for (String item : list) {
                writer.write(item);
                writer.newLine();
            }
            writer.close();
        }
        catch (FileNotFoundException f) {
            /* If the file cannot be found try to read the file.
                This will include creating the file if it does not already exist,
                then attempt to write to the file again
             */
            readUsersFile(file, path, list);
            writeUsersFile(file, path, list);
        }
        catch (IOException e) {
            println(e.getMessage());
        }
    }


    /**
     *
     * Takes a notes file and stores its contents in a list
     *
     * @param file The file to be read
     * @param path The path to the specified file
     * @param list The list that the file contents should be stored in
     */
    static ArrayList<Note> readNotesFile(File file, String path, ArrayList<Note> list) {
        // Create a new BufferedReader that will go onto read the file's contents
        BufferedReader reader = findFile(file, path);

        // Stores the current line in the file
        String line;

        try {
            // While the current line is not empty, read it and store it in the list
            while ((line = reader.readLine()) != null) {
                String title = line;
                String contents = reader.readLine();
                list.add(new Note(title, contents));
            }
            reader.close();
        }
        catch (NullPointerException | IOException e) {
            println("Couldn't read notes file\n    The file may be empty");
        }
        return list;
    }


    /**
     *
     * Write a list to a file
     *
     * @param path The path for the new file
     * @param list The list which holds the content
     */
    static void writeNotesFile(File file, String path, ArrayList<Note> list) {
        try {
            // Create a new BufferedWrite to write to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path), false));
            // For every item in the list, write it to the file and then add a line break
            for (Note note : list) {
                writer.write(note.getTitle());
                writer.newLine();
                writer.write(note.getBody());
                writer.newLine();
            }
            writer.close();
        }
        catch (FileNotFoundException f) {
            /* If the file cannot be found try to read the file.
                This will include creating the file if it does not already exist,
                then attempt to write to the file again
             */
            readNotesFile(file, path, list);
            writeNotesFile(file, path, list);
        }
        catch (IOException e) {
            println(e.getMessage());
        }
    }

}
