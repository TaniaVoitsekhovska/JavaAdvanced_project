package ua.lviv.home.services;

import ua.lviv.home.daos.UserDao;
import ua.lviv.home.enteties.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    private UserDao userDao;

    private static UserService userService;

    private UserService() {
        this.userDao = new UserDao();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public User create(User t) {
        return userDao.insert(t);
    }

    public User read(int id) {
        return userDao.read(id);
    }

    public void update(User t, int id) {
        userDao.update(t, id);
    }

    public void delete(Integer id) {
        userDao.delete(id);
    }

    public List<User> readAll() {
        return userDao.readAll();
    }

    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}
