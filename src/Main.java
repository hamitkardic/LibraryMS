import com.library.repository.UserDAO;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Library");
        System.out.println("Please login.");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String inputUsername = scanner.nextLine();

        System.out.print("Password: ");
        String inputPassword = scanner.nextLine();

        UserDAO userDAO = new UserDAO();

        if (userDAO.login(inputUsername, inputPassword)) {
            System.out.println("Login successful");
        } else {
            System.out.println("Invalid credentials");
        }

        scanner.close();
    }
}
