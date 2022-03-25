package repositories;

import models.Reimbursement;

import java.util.List;

public interface ReimbursementDAO{
    List<Reimbursement> getAllReimbByReimbId(Integer reimbId);
    List<Reimbursement> getAllReimbByUserId(Integer UserId);
    List<Reimbursement> getAllReimbByStatusId(Integer statusId);
    List<Reimbursement> getAllReimb();
    void createReimb(Reimbursement reimb);
    void approveOrDenyReimb(Reimbursement reimb);
}
