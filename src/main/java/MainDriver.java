import controllers.ReimbursementController;
import controllers.UserController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import models.Reimbursement;
import models.User;
import repositories.ReimbursementDAO;
import repositories.ReimbursementDAOImpl;
import repositories.UserDAO;
import repositories.UserDAOImpl;
import services.UserService;

public class MainDriver {
    public static void main(String[] args) {
        Javalin app =  Javalin.create(config -> {
            config.addStaticFiles("/", Location.CLASSPATH);
        }).start(9004);

        //instantiating controllers//

        UserController userController = new UserController();
        ReimbursementController reimbursementController = new ReimbursementController();
        UserDAO userDAO = new UserDAOImpl();
        ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
        UserService userService = new UserService();

        //testing DAO methods//
        //System.out.println(userDAO.getUserGivenUsernameEmployee("kev123"));
        //System.out.println(userDAO.getUserGivenUsernameFinancial("MoneyMan"));
        //System.out.println(userDAO.getUserGivenUsernameAdmin("admin"));
        //System.out.println(userDAO.getAllPendingUsers(0));
        //System.out.println(reimbursementDAO.getAllReimbByUserId(1));
        //System.out.println(userDAO.getAllUsers());
        //userDAO.createEmployeeUser(new User("kev123", "pass123", "Kevin", "Childs", "kev123@gmail.com")); //works
        //reimbursementDAO.createReimb(new Reimbursement(12.00, "chicken nuggets", 1, 2)); //works
        //reimbursementDAO.approveOrDenyReimb(new Reimbursement(2,2,11));
        //System.out.println(reimbursementDAO.getAllReimbByUserId(1));

        //endpoints for each method within each controller//

        //----UserController----//
        app.post("/newuser", userController::createUser); //works
        app.post("/login1", userController::loginEmployee); //works
        app.post("/login2", userController::loginFinancialManager); //works
        app.post("/login3", userController::loginAdmin); //works
        app.get("/checksession", userController::checkSession); //works
        app.delete("/logout", userController::logOut); //works
        app.get("/allusers", userController::viewAllUsers);
        //admin
        app.get("/pendingusers", userController::viewAllPendingUsers); //works
        app.put("/pendingusers/approve", userController::approvePendingUsers); //works

        //----ReimbursementController----//
        app.get("/1/{userId}/reimbursements", reimbursementController::displayAllReimbursementsForEmployee); //works
        app.get("/2/{reimbId}/reimbid", reimbursementController::displayReimbursementByReimbId);
        app.get("/2/{statusId}/status", reimbursementController::displayAllReimbursementsForAllEmployeesByStatus); //works
        app.post("/1/new-reimbursement", reimbursementController::createReimbursement); //works
        app.put("/2/update-reimbursement", reimbursementController::updateReimbursement); //works
        app.get("/2/reimbursements", reimbursementController::displayAllReimbursements);

    }
}
