package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.User;

public class UserDAO {

    public static boolean create(User user) {
        // INSERT INTO user (name, email, password) VALUES (?, ?, ?)
        String query = "INSERT INTO User (login, email, password) VALUES (?, ?, ?)";
        try (Connection connection = DataAcessObject.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<User> readAll() {
        String query = "SELECT login, email, password FROM User";
        List<User> users = new ArrayList<>();
        
        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                User user = new User(login, email, password);
                users.add(user);        
            }
            return users;
        } catch (SQLException e) {
            return null;
        }
    }

    public static User readById(String id) {
        String query = "SELECT login, email, password FROM User WHERE login = ?";
        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String login = resultSet.getString("login");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                return new User(login, email, password);
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean update(User user) {
        String query = "UPDATE User SET email = ?, password = ? WHERE login = ?";
        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getLogin());
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean delete(String id) {
        String query = "DELETE FROM User WHERE login = ?";

        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
