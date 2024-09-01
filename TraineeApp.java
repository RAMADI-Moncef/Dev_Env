import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeApp {

    private static final String URL = "jdbc:postgresql://localhost:5432/Dev_Env-DB";
    private static final String USER = "RAMADI";
    private static final String PASSWORD = "Sophist";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter hours worked: ");
            int hoursWorked = scanner.nextInt();

            String sql = "INSERT INTO employees (first_name, last_name, hours_worked) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setInt(3, hoursWorked);
                pstmt.executeUpdate();
                System.out.println("Employee data saved successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
}

