import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/GestionEmployes";  // Remplace par le nom de ta base de données
        String user = "root";  // Remplace par ton utilisateur MySQL
        String password = "02424820";  // Remplace par ton mot de passe MySQL

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connexion réussie à la base de données !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
