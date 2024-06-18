package Services;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserService {

    public static User getUser(String username, String password) throws SQLException {
        User result = null;
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT id_user, nazwa_uzytkownika, email, haslo, imie, nazwisko, Admin FROM user WHERE nazwa_uzytkownika = ? AND haslo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = new User(resultSet.getInt("id_user"),resultSet.getString("nazwa_uzytkownika"), resultSet.getString("email"), resultSet.getString("haslo"), resultSet.getString("imie"), resultSet.getString("nazwisko"), resultSet.getInt("Admin"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    public static User getUser(int userID) throws SQLException {
        User result = null;
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT id_user, nazwa_uzytkownika, email, haslo, imie, nazwisko, Admin FROM user WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = new User(resultSet.getInt("id_user"),resultSet.getString("nazwa_uzytkownika"), resultSet.getString("email"), resultSet.getString("haslo"), resultSet.getString("imie"), resultSet.getString("nazwisko"), resultSet.getInt("Admin"));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    public static List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT id_user, nazwa_uzytkownika, email, haslo, imie, nazwisko, Admin FROM user";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id_user"),resultSet.getString("nazwa_uzytkownika"), resultSet.getString("email"), resultSet.getString("haslo"), resultSet.getString("imie"), resultSet.getString("nazwisko"), resultSet.getInt("Admin")));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return users;
    }

    public static void updateUser(int userID, User user) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String query = "UPDATE user SET nazwa_uzytkownika= ? ,email= ? ,haslo= ? ,imie= ? ,nazwisko= ?, Admin= ? WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getSureName());
            preparedStatement.setInt(6, user.getUserRole());
            preparedStatement.setInt(7, userID);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw e;
        }
    }

    public static int createUser(User user) throws SQLException {
        int id = -1;
        try (Connection connection = Database.getConnection()) {
            String query = "INSERT INTO user (nazwa_uzytkownika, email, haslo, imie, nazwisko, Admin) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getSureName());
            preparedStatement.setInt(6, user.getUserRole());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                id = resultSet.getInt(1);
            }

            preparedStatement.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw e;
        }

        return id;
    }

    public static void removeUser(int userId) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String query = "DELETE FROM user WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
