package repositories;

import models.Reimbursement;
import models.User;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{
    @Override
    public User getUserGivenUsernameEmployee(String username) {
        User user = null;

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ? AND USER_ROLE_ID_FK = 1;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7), rs.getBoolean(8));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserGivenUsernameFinancial(String username) {
        User user = null;

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ? AND USER_ROLE_ID_FK = 2;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7), rs.getBoolean(8));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserGivenUsernameAdmin(String username) {
        User user = null;

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ? AND USER_ROLE_ID_FK = 3;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7), rs.getBoolean(8));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return user;
    }

    @Override
    public void createUser(User user) {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT INTO ERS_USERS (ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID_FK) VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstname());
            ps.setString(4, user.getLastname());
            ps.setString(5, user.getEmail());
            ps.setInt(6, user.getRoleId());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    @Override
    public List<User> getAllPendingUsers(Boolean approved) {
        List<User> users = new ArrayList<>();
        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM ERS_USERS WHERE APPROVED = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, approved);

            ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getInt(7),rs.getBoolean(8)));
            }
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT ERS_USERS_ID, USER_FIRST_NAME, USER_LAST_NAME FROM ERS_USERS;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return users;
    }

    @Override
    public void approveUser(User user) {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "UPDATE ERS_USERS SET APPROVED = true WHERE ERS_USERS_ID = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserId());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
