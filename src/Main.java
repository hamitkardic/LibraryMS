import com.library.repository.UserDAO;
import java.io.Console;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Library");
        System.out.println("Please login.");

        Console console = System.console();

        if (console == null) {
            System.out.println("No console available. Run from a terminal.");
            return;
        }

        String username = console.readLine("Username: ");
        char[] passwordChars = console.readPassword("Password: ");
        String password = new String(passwordChars);

        UserDAO userDAO = new UserDAO();

        if (userDAO.login(username, password)) {
            System.out.println("Login successful");
        } else {
            System.out.println("Invalid credentials");
        }
    }
}