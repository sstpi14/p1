package repositories;

import models.Reimbursement;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;

public class ReimbursementDAOImpl implements ReimbursementDAO{

    //get reimbursements by reimbursement id
    @Override
    public List<Reimbursement> getAllReimbByReimbId(Integer reimbId) {
        List<Reimbursement> reimbursements = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_ID = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, reimbId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reimbursements.add(new Reimbursement(
                        rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3),
                        rs.getTimestamp(4), rs.getString(5), rs.getObject(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return reimbursements;
    }

    //get reimbursements by user id(foreign key)
    @Override
    public List<Reimbursement> getAllReimbByUserId(Integer userId) {
        List<Reimbursement> reimbursements = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR_FK = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reimbursements.add(new Reimbursement(
                        rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3),
                        rs.getTimestamp(4), rs.getString(5), rs.getObject(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return reimbursements;
    }

    @Override
    public List<Reimbursement> getAllReimbByStatusId(Integer statusId) {
        List<Reimbursement> reimbursements = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_STATUS_ID_FK = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, statusId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reimbursements.add(new Reimbursement(
                        rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3),
                        rs.getTimestamp(4), rs.getString(5), rs.getObject(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return reimbursements;
    }

    @Override
    public List<Reimbursement> getAllReimb() {
        List<Reimbursement> reimbursements = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ERS_REIMBURSEMENT;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reimbursements.add(new Reimbursement(
                        rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3),
                        rs.getTimestamp(4), rs.getString(5), rs.getObject(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return reimbursements;
    }

    @Override
    public void createReimb(Reimbursement reimb) {
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT INTO ERS_REIMBURSEMENT (REIMB_AMOUNT, REIMB_DESCRIPTION, REIMB_AUTHOR_FK, REIMB_TYPE_ID_FK) VALUES (?, ?, ?, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, reimb.getAmount());
            ps.setString(2, reimb.getDescription());
            ps.setInt(3, reimb.getAuthorId());
            ps.setInt(4, reimb.getTypeId());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    @Override
    public void approveOrDenyReimb(Reimbursement reimb) {
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "UPDATE ERS_REIMBURSEMENT SET REIMB_STATUS_ID_FK = ?, REIMB_RESOLVER_FK = ?, REIMB_RESOLVED = NOW() WHERE REIMB_ID = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reimb.getStatusId());
            ps.setInt(2, reimb.getResolverId());
            ps.setInt(3, reimb.getReimbId());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
