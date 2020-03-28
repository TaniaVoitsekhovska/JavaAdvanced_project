package ua.lviv.home.daos;

import org.apache.log4j.Logger;
import ua.lviv.home.ConnectionUtil;
import ua.lviv.home.enteties.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements CRUD<User> {

    private static final Logger LOG = Logger.getLogger(UserDao.class);

    public static final String SELECT_ALL = "SELECT * FROM users";
    public static final String DELETE = "DELETE FROM users where id = ?";
    public static final String UPDATE = "UPDATE users SET email = ?, first_name = ?, last_name = ?, " +
            "role = ?,password = ? where id = ?";
    public static final String SELECT_BY_ID = "SELECT * FROM users where id = ?";
    public static final String INSERT_INTO =
            "INSERT INTO users(email, first_name, last_name, role, password) values(?, ?, ?, ?, ?)";
    public static final String SELECT_BY_EMAIL = "SELECT * FROM users where email = ?";

    private Connection connection;

    public UserDao() {
        this.connection = ConnectionUtil.getConnection();
    }

    @Override
    public User insert(User user) {
        String message = String.format("Will create a user for  userId=%d", user.getId());
        LOG.debug(message);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            user.setRole("USER");
            preparedStatement.setObject(4, user.getRole());
            preparedStatement.setObject(5, user.getPassword());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getInt(1));
            return user;
        } catch (SQLException e) {
            String errorMessage = String.format("Error while inserting a user for productId=%d", user.getId());
            LOG.error(errorMessage, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public User read(int id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return User.of(resultSet);
        } catch (SQLException e) {
            String errorMessage = String.format("Error while getting user by id=%d", id);
            LOG.error(errorMessage, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> readAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                users.add(User.of(resultSet));
            }
            return users;
        } catch (SQLException e) {
            LOG.error("Error while getting all users", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String errorMessage = String.format("Error while deleting user by id=%d", id);
            LOG.error(errorMessage, e);
        }
    }

    @Override
    public void update(User user, int id) {
        try {
            user.setId(id);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setObject(1, user.getEmail());
            preparedStatement.setObject(2, user.getFirstName());
            preparedStatement.setObject(3, user.getLastName());
            preparedStatement.setObject(4, user.getRole());
            preparedStatement.setObject(5, user.getPassword());
            preparedStatement.setObject(6, user.getId());
            preparedStatement.executeUpdate();
            LOG.info(String.format(""));
        } catch (SQLException e) {
            String errorMessage = String.format("Error while updating user id=%d", user.getId());
            LOG.error(errorMessage, e);
        }
    }

    public Optional<User> getByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL);
            preparedStatement.setObject(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Optional<User> u = Optional.of(User.of(resultSet));
                return u;
            } else {
                String errorMessage = String.format("Check user email= %s or password ", email);
                LOG.error(errorMessage);
            }
        } catch (SQLException e) {
            String errorMessage = String.format("Error while getting user by email=%s", email);
            LOG.error(errorMessage, e);
        }
        return Optional.empty();
    }

}
