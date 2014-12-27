package cherry.querytutorial.db.gen.dto;

import java.io.Serializable;
import org.joda.time.LocalDateTime;

public class PriorityMaster implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PRIORITY_MASTER.ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PRIORITY_MASTER.PRIORITY_CD
     *
     * @mbggenerated
     */
    private Integer priorityCd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PRIORITY_MASTER.PRIORITY_LABEL
     *
     * @mbggenerated
     */
    private String priorityLabel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PRIORITY_MASTER.UPDATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PRIORITY_MASTER.CREATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PRIORITY_MASTER.LOCK_VERSION
     *
     * @mbggenerated
     */
    private Integer lockVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PRIORITY_MASTER.DELETED_FLG
     *
     * @mbggenerated
     */
    private Integer deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PRIORITY_MASTER
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PRIORITY_MASTER.ID
     *
     * @return the value of PRIORITY_MASTER.ID
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PRIORITY_MASTER.ID
     *
     * @param id the value for PRIORITY_MASTER.ID
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PRIORITY_MASTER.PRIORITY_CD
     *
     * @return the value of PRIORITY_MASTER.PRIORITY_CD
     *
     * @mbggenerated
     */
    public Integer getPriorityCd() {
        return priorityCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PRIORITY_MASTER.PRIORITY_CD
     *
     * @param priorityCd the value for PRIORITY_MASTER.PRIORITY_CD
     *
     * @mbggenerated
     */
    public void setPriorityCd(Integer priorityCd) {
        this.priorityCd = priorityCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PRIORITY_MASTER.PRIORITY_LABEL
     *
     * @return the value of PRIORITY_MASTER.PRIORITY_LABEL
     *
     * @mbggenerated
     */
    public String getPriorityLabel() {
        return priorityLabel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PRIORITY_MASTER.PRIORITY_LABEL
     *
     * @param priorityLabel the value for PRIORITY_MASTER.PRIORITY_LABEL
     *
     * @mbggenerated
     */
    public void setPriorityLabel(String priorityLabel) {
        this.priorityLabel = priorityLabel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PRIORITY_MASTER.UPDATED_AT
     *
     * @return the value of PRIORITY_MASTER.UPDATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PRIORITY_MASTER.UPDATED_AT
     *
     * @param updatedAt the value for PRIORITY_MASTER.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PRIORITY_MASTER.CREATED_AT
     *
     * @return the value of PRIORITY_MASTER.CREATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PRIORITY_MASTER.CREATED_AT
     *
     * @param createdAt the value for PRIORITY_MASTER.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PRIORITY_MASTER.LOCK_VERSION
     *
     * @return the value of PRIORITY_MASTER.LOCK_VERSION
     *
     * @mbggenerated
     */
    public Integer getLockVersion() {
        return lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PRIORITY_MASTER.LOCK_VERSION
     *
     * @param lockVersion the value for PRIORITY_MASTER.LOCK_VERSION
     *
     * @mbggenerated
     */
    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PRIORITY_MASTER.DELETED_FLG
     *
     * @return the value of PRIORITY_MASTER.DELETED_FLG
     *
     * @mbggenerated
     */
    public Integer getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PRIORITY_MASTER.DELETED_FLG
     *
     * @param deletedFlg the value for PRIORITY_MASTER.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(Integer deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRIORITY_MASTER
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PriorityMaster other = (PriorityMaster) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPriorityCd() == null ? other.getPriorityCd() == null : this.getPriorityCd().equals(other.getPriorityCd()))
            && (this.getPriorityLabel() == null ? other.getPriorityLabel() == null : this.getPriorityLabel().equals(other.getPriorityLabel()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getLockVersion() == null ? other.getLockVersion() == null : this.getLockVersion().equals(other.getLockVersion()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRIORITY_MASTER
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPriorityCd() == null) ? 0 : getPriorityCd().hashCode());
        result = prime * result + ((getPriorityLabel() == null) ? 0 : getPriorityLabel().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getLockVersion() == null) ? 0 : getLockVersion().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRIORITY_MASTER
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", priorityCd=").append(priorityCd);
        sb.append(", priorityLabel=").append(priorityLabel);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", lockVersion=").append(lockVersion);
        sb.append(", deletedFlg=").append(deletedFlg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}