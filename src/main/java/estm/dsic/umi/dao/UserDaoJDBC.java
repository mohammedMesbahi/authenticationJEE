package estm.dsic.umi.dao;

import java.sql.*;

import estm.dsic.umi.beans.User;
import estm.dsic.umi.dao.interfaces.UserDao;

public class UserDaoJDBC implements UserDao {
    private Connection connection;
    private static UserDaoJDBC instance;

    private UserDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    public static UserDaoJDBC getInstance() {
        if (instance == null)
            instance = new UserDaoJDBC(JdbcConnection.getInstance());
        return instance;
    }


    @Override
    public User get(Integer id) {
        User user = null;
        try {
            String query = "SELECT * FROM user WHERE user.id = " + id;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Error while getting user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User create(User user) {
        try {
            // creat a prepared statement
            String query = "INSERT INTO user (name,email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Error while creating user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User delete(User user) {
        return null;
    }

    @Override
    public User get(User user) {
        User user1 = null;
        try {
            String query = "SELECT * FROM user WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user1 = new User();
                user1.setId(resultSet.getInt("id"));
                user1.setName(resultSet.getString("name"));
                user1.setEmail(resultSet.getString("email"));
                user1.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            System.out.println("Error while getting user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User get(String email, String password) {
        User user = null;
        try {
            String query = "SELECT * FROM user WHERE email=? AND password=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Error while getting user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return user;
    }
}
