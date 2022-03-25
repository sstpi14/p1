package repositories;

import models.User;

import java.util.List;

public interface UserDAO {
    User getUserGivenUsernameEmployee(String username);
    User getUserGivenUsernameFinancial(String username);
    User getUserGivenUsernameAdmin(String username);
    void createUser(User user);
    List<User> getAllPendingUsers(Boolean approved);
    List<User> getAllUsers();
    void approveUser(User user);
}
