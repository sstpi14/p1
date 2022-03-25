package controllers;

import io.javalin.http.Context;
import models.JsonResponse;
import models.User;
import services.UserService;

import java.util.List;

public class UserController {
    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //create users//
    public void createUser(Context context) {
        User user = context.bodyAsClass(User.class);
        userService.createUser(user);

        JsonResponse jsonResponse = new JsonResponse(true, "User has been created, pending approval from Admin", null);
        context.json(jsonResponse);
    }

    //login users//
    public void loginEmployee(Context context) {
        JsonResponse jsonResponse;
        User credentials = context.bodyAsClass(User.class);
        User userFromDb = userService.validateCredentialsEmployee((credentials.getUsername()), credentials.getPassword());
        User userFromDb2 = userService.validateApprovalEmployee((credentials.getUsername()), credentials.getApproved());
        context.sessionAttribute("user", userFromDb);

        if(userFromDb == null) {
            jsonResponse = new JsonResponse(false, "invalid username or password", null);
        } else if (userFromDb2 == null) {
            jsonResponse = new JsonResponse(false, "pending approval from admin", null);
        } else {
            jsonResponse = new JsonResponse(true, "login successful", userFromDb);
        }
        context.json(jsonResponse);
    }
    public void loginFinancialManager(Context context) {
        JsonResponse jsonResponse;
        User credentials = context.bodyAsClass(User.class);
        User userFromDb = userService.validateCredentialsFinancial((credentials.getUsername()), credentials.getPassword());
        User userFromDb2 = userService.validateApprovalFinancial((credentials.getUsername()),credentials.getApproved());
        context.sessionAttribute("user", userFromDb);

        if (userFromDb == null) {
            jsonResponse = new JsonResponse(false, "invalid username or password", null);
        } else if(userFromDb2 == null) {
            jsonResponse = new JsonResponse(false, "user pending approval from admin", null);
        } else {
            jsonResponse = new JsonResponse(true, "login successful", userFromDb);
        }
        context.json(jsonResponse);
    }
    public void loginAdmin(Context context) {
        JsonResponse jsonResponse;
        User credentials = context.bodyAsClass(User.class);
        User userFromDb = userService.validateCredentialsAdmin((credentials.getUsername()), credentials.getPassword());
        User userFromDb2 = userService.validateApprovalAdmin((credentials.getUsername()),credentials.getApproved());
        context.sessionAttribute("user", userFromDb);

        if (userFromDb == null) {
            jsonResponse = new JsonResponse(false, "invalid username or password", null);
        } else if(userFromDb2 == null) {
            jsonResponse = new JsonResponse(false, "user pending approval from admin", null);
        } else {
            jsonResponse = new JsonResponse(true, "login successful", userFromDb);
        }
        context.json(jsonResponse);
    }
    public void viewAllPendingUsers(Context context){
        Boolean approved = false;
        List<User> pendingUsers = userService.getAllPendingUsers(approved);
        context.json(new JsonResponse(true, "viewing all pending users", pendingUsers));
    }
    public void viewAllUsers(Context context) {
        List<User> users = userService.getAllUsers();
        context.json(new JsonResponse(true, "viewing all users", users));
    }
    public void approvePendingUsers(Context context) {
        User user = context.bodyAsClass(User.class);
        userService.approveUser(user);

        JsonResponse jsonResponse = new JsonResponse(true, "User has approved by admin", null);
        context.json(jsonResponse);
    }
    public void logOut(Context context) {
        context.sessionAttribute("user", null);

        JsonResponse jsonResponse = new JsonResponse(true, "User Logged Out", null);
    }
    public void checkSession(Context context) {
        User user = context.sessionAttribute("user");

        if(user == null) {
            context.json(new JsonResponse(false, "You are not logged in, redirecting", null));
        }else{
            context.json(new JsonResponse(true, "User is logged in", user));
        }
    }
}
