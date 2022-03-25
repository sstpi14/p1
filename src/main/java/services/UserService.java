package services;

import models.User;
import repositories.UserDAO;
import repositories.UserDAOImpl;

import java.sql.ResultSet;
import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserService() {
        this.userDAO = new UserDAOImpl();
    }

    public User validateCredentialsEmployee(String username, String password) {
        User user = this.userDAO.getUserGivenUsernameEmployee(username);
        if (user == null) {
            return null;
        }
        if (!password.equals(user.getPassword())) {
            return null; }
        return user;
    }

    public User validateApprovalEmployee(String username, Boolean approved) {
        User user = this.userDAO.getUserGivenUsernameEmployee(username);
        if (user == null) {
            return null;
        }
        if (!approved.equals(user.getApproved())) {
            return null;
        }
        return user;
    }

    public User validateCredentialsFinancial(String username, String password) {
        User user = this.userDAO.getUserGivenUsernameFinancial(username);
        if (user == null)
            return null;
        if (!password.equals(user.getPassword()))
            return null;
        return user;
    }
    public User validateApprovalFinancial(String username, Boolean approved) {
        User user = this.userDAO.getUserGivenUsernameFinancial(username);
        if (user == null) {
            return null;
        }
        if (!approved.equals(user.getApproved())) {
            return null;
        }
        return user;
    }
    public User validateCredentialsAdmin(String username, String password) {
        User user = this.userDAO.getUserGivenUsernameAdmin(username);
        if (user == null)
            return null;
        if (!password.equals(user.getPassword()))
            return null;
        return user;
    }
    public User validateApprovalAdmin(String username, Boolean approved) {
        User user = this.userDAO.getUserGivenUsernameAdmin(username);
        if (user == null) {
            return null;
        }
        if (!approved.equals(user.getApproved())) {
            return null;
        }
        return user;
    }
    public void createUser(User user) {
        this.userDAO.createUser(user);
    }
    public List<User> getAllPendingUsers(Boolean approved) {
        List<User> users = this.userDAO.getAllPendingUsers(approved);
        return users;
    }
    public List<User> getAllUsers() {
        List<User> users = this.userDAO.getAllUsers();
        return users;
    }
    public void approveUser(User user) {
        this.userDAO.approveUser(user);
    }

}
