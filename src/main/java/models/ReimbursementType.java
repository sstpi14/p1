package models;

public class ReimbursementType {
    private Integer reimbTypeId;
    private String reimbType;

    //constructors//

    public ReimbursementType() {
    }

    public ReimbursementType(Integer reimbTypeId, String reimbType) {
        this.reimbTypeId = reimbTypeId;
        this.reimbType = reimbType;
    }

    //getters and setters//

    public Integer getReimbTypeId() {
        return reimbTypeId;
    }

    public void setReimbTypeId(Integer reimbTypeId) {
        this.reimbTypeId = reimbTypeId;
    }

    public String getReimbType() {
        return reimbType;
    }

    public void setReimbType(String reimbType) {
        this.reimbType = reimbType;
    }

    //toString//

    @Override
    public String toString() {
        return "ReimbursementType{" +
                "reimbTypeId=" + reimbTypeId +
                ", reimbType='" + reimbType + '\'' +
                '}';
    }
}
