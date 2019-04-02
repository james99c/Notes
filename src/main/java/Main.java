import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Utilities {

    /**
     * Stores the console input from the user
     */
    private static Scanner userInput;
    /**
     * Stores a list of users
     */
    private static ArrayList<String> users = new ArrayList<>();
    /**
     * Stores the path to the file containing the list of users
     */
    private static File usersFile = new File("src/main/resources/users.txt");
    /**
     * Stores whether there is an active user or not
     */
    private static boolean userActive = false;
    /**
     * Stores a list of the user's notes
     */
    private static ArrayList<Note> notes = new ArrayList<>();


    public static void main(String[] args) {
        // Find the list of users and add them to a list
        users = readUsersFile(usersFile, usersFile.getAbsolutePath(), users);
        println("Welcome to Notes");
        userInput = new Scanner(System.in);
        // Log a user in, then store that user and run the main part of the program
        String user = login();
        run(user);
    }


    private static String login() {
        println("Type login or register to proceed:");
        String loginAction = userInput.nextLine();
        String username = "";
        // While there is no active user continually go through the login process
        do {
            switch (loginAction) {
                case "login":
                    print("Username: ");
                    username = userInput.nextLine();
                    if (username.matches("^[a-zA-Z0-9_]*$")) {
                        if (users.contains(username)) {
                            userActive = true;
                        }
                        else {
                            println("Username not recognized");
                        }
                    }
                    else {
                        println("Username can only contain letters, numbers or the \'_\' character");
                    }
                    break;
                case "register":
                    print("Choose a username: ");
                    username = userInput.nextLine();
                    if (username.matches("^[a-zA-Z0-9_]*$")) {
                        if (users.contains(username)) {
                            println("Sorry, that username is taken");
                        }
                        else {
                            addToDatabase(username);
                            userActive = true;
                        }
                    }
                    else {
                        println("Username can only contain letters, numbers or the \'_\' character");
                    }
                    break;
                default:
                    println("Unknown command");
                    login();
                    break;
            }
        }
        while (!userActive);
        println("Logged in");
        return username;
    }

    /**
     *
     * Add a new user to the database
     *
     * @param username The username of the new user
     */
    private static void addToDatabase(String username) {
        users.add(username);
        writeUsersFile(usersFile, usersFile.getAbsolutePath(), users);
    }

    /**
     *
     * Runs the main part of the program
     * i.e. allow the active user to create, view and edit notes
     *
     * @param user The name of the active user
     */
    private static void run(String user) {

        File userNotesFile = new File("src/main/resources/notes/" + user + ".txt");
        notes = readNotesFile(userNotesFile, userNotesFile.getAbsolutePath(), notes);

        do {
            println("[QUIT] [HELP] [NEW] [NOTES]");
            String command = userInput.nextLine();

            switch (command) {
                case "quit" :
                    println("Quitting...");
                    userActive = false;
                    break;
                case "help" :
                    println("Welcome to Help! Valid commands are");
                    println("QUIT            Exit the program");
                    println("HELP            Display list of commands");
                    println("NEW             Create a new note");
                    println("NOTES           Show a list of the user's notes");
                    break;
                case "new" :
                    println("Title:");
                    String title = userInput.nextLine();
                    println("Contents");
                    String contents = userInput.nextLine();
                    notes.add(new Note(title, contents));
                    writeNotesFile(userNotesFile, userNotesFile.getAbsolutePath(), notes);
                    break;
                case "notes" :
                    for (Note note : notes) {
                        println(note.toString());
                    }
                    break;
                default:
                    println("Unknown command");
                    break;
            }
        }
        while (userActive);
    }

}
