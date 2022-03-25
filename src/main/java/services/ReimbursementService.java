package services;

import models.Reimbursement;
import repositories.ReimbursementDAO;
import repositories.ReimbursementDAOImpl;

import java.util.List;

public class ReimbursementService {
    private ReimbursementDAO reimbursementDAO;

    public ReimbursementService() {
        this.reimbursementDAO = new ReimbursementDAOImpl();
    }
    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public List<Reimbursement> getAllReimbByUserId(Integer userId) {
        return this.reimbursementDAO.getAllReimbByUserId(userId);
    }

    public List<Reimbursement> getAllReimbByReimbId(Integer reimbId) {
        return this.reimbursementDAO.getAllReimbByReimbId(reimbId);
    }
    public List<Reimbursement> getAllReimbByStatusId(Integer statusId) {
        return this.reimbursementDAO.getAllReimbByStatusId(statusId);
    }
    public List<Reimbursement> getAllReimbursements() {
        return this.reimbursementDAO.getAllReimb();
    }
    public void createReimbursement(Reimbursement reimbursement) {
        this.reimbursementDAO.createReimb(reimbursement);
    }
    public void updateReimbursement(Reimbursement reimbursement) {
        this.reimbursementDAO.approveOrDenyReimb(reimbursement);
    }
}
