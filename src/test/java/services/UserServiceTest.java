package services;

import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repositories.UserDAO;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;

    private UserDAO userDAO = Mockito.mock(UserDAO.class);

    public UserServiceTest() {
        this.userService = new UserService(userDAO);
    }

    @Test
    void validateCredentialsEmployeeInvalidUsername() {
        //assert
        String expectedUsername = "blahblahblah";
        String expectedPassword = "pass123";
        User expectedOutput = null;
        Mockito.when(userDAO.getUserGivenUsernameEmployee(expectedUsername)).thenReturn(expectedOutput);

        //act
        User actualOutput = userService.validateCredentialsEmployee(expectedUsername,expectedPassword);

        //assert
        Assertions.assertEquals(expectedOutput, actualOutput);

    }
    @Test
    void validateCredentialsEmployeeInvalidPassword() {
        //assert
        String expectedUsername = "kev123";
        String expectedPassword = "blahblahblah";
        User expectedOutput = null;
        User userFromDb = new User("kev123", "pass123","first", "last", "email");
        Mockito.when(userDAO.getUserGivenUsernameEmployee(expectedUsername)).thenReturn(userFromDb);

        //act
        User actualOutput = userService.validateCredentialsEmployee(expectedUsername,expectedPassword);

        //assert
        Assertions.assertEquals(expectedOutput, actualOutput);

    }
    @Test
    void validateCredentialsEmployeeValidCredentials() {
        //assert
        String expectedUsername = "kev123";
        String expectedPassword = "pass123";
        User expectedOutput = new User(expectedUsername, expectedPassword, "first", "last", "email");
        Mockito.when(userDAO.getUserGivenUsernameEmployee(expectedUsername)).thenReturn(expectedOutput);

        //act
        User actualOutput = userService.validateCredentialsEmployee(expectedUsername,expectedPassword);

        //assert
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void createUser() {
        //arrange
        User userToPass = new User("username", "password", "kevin", "childs", "email");

        //act
        userService.createUser(userToPass);

        //assert
        Mockito.verify(userDAO, Mockito.times(1)).createUser(userToPass);
    }
}