package models;

import java.util.Date;

public class Reimbursement {
    private Integer reimbId;
    private Double amount;
    private Date dateSubmitted;
    private Date dateResolved;
    private String description;
    private Object receipt;
    private Integer authorId;
    private Integer resolverId;
    private Integer statusId;
    private Integer typeId;

    //constructors//

    public Reimbursement() {
    }

    //Employee Reimbursement Creation with Description
    public Reimbursement(Double amount, String description, Integer authorId, Integer typeId) {
        this.amount = amount;
        this.description = description;
        this.authorId = authorId;
        this.typeId = typeId;
    }

    //Employee Reimbursement Creation without Description
    public Reimbursement(Double amount, Integer authorId, Integer typeId) {
        this.amount = amount;
        this.authorId = authorId;
        this.typeId = typeId;
    }

    //Finance Manager Reimbursement Update
    public Reimbursement(Integer statusId, Integer resolverId, Integer reimbId) {
        this.statusId = statusId;
        this.resolverId = resolverId;
        this.reimbId = reimbId;
    }

    //Full view of Reimbursements
    public Reimbursement(Integer reimbId, Double amount, Date dateSubmitted, Date dateResolved, String description, Object receipt, Integer authorId, Integer resolverId, Integer statusId, Integer typeId) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.dateSubmitted = dateSubmitted;
        this.dateResolved = dateResolved;
        this.description = description;
        this.receipt = receipt;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    //getters and setters//

    public Integer getReimbId() {
        return reimbId;
    }

    public void setReimbId(Integer reimbId) {
        this.reimbId = reimbId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public Date getDateResolved() {
        return dateResolved;
    }

    public void setDateResolved(Date dateResolved) {
        this.dateResolved = dateResolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getReceipt() {
        return receipt;
    }

    public void setReceipt(Object receipt) {
        this.receipt = receipt;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getResolverId() {
        return resolverId;
    }

    public void setResolverId(Integer resolverId) {
        this.resolverId = resolverId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    //toString//

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbId=" + reimbId +
                ", amount=" + amount +
                ", dateSubmitted=" + dateSubmitted +
                ", dateResolved=" + dateResolved +
                ", description='" + description + '\'' +
                ", receipt=" + receipt +
                ", authorId=" + authorId +
                ", resolverId=" + resolverId +
                ", statusId=" + statusId +
                ", typeId=" + typeId +
                '}';
    }
}
