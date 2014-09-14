package cherry.spring.common.db.gen.dto;

import cherry.spring.common.custom.DeletedFlag;
import java.io.Serializable;
import org.joda.time.LocalDateTime;

public class AsyncProc implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROC.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROC.LAUNCHER_ID
     *
     * @mbggenerated
     */
    private String launcherId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROC.NAME
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROC.STATUS
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROC.REGISTERED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime registeredAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROC.INVOKED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime invokedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROC.STARTED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime startedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROC.FINISHED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime finishedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROC.RESULT
     *
     * @mbggenerated
     */
    private String result;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROC.UPDATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROC.CREATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROC.LOCK_VERSION
     *
     * @mbggenerated
     */
    private Integer lockVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROC.DELETED_FLG
     *
     * @mbggenerated
     */
    private DeletedFlag deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ASYNC_PROC
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROC.ID
     *
     * @return the value of ASYNC_PROC.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROC.ID
     *
     * @param id the value for ASYNC_PROC.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROC.LAUNCHER_ID
     *
     * @return the value of ASYNC_PROC.LAUNCHER_ID
     *
     * @mbggenerated
     */
    public String getLauncherId() {
        return launcherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROC.LAUNCHER_ID
     *
     * @param launcherId the value for ASYNC_PROC.LAUNCHER_ID
     *
     * @mbggenerated
     */
    public void setLauncherId(String launcherId) {
        this.launcherId = launcherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROC.NAME
     *
     * @return the value of ASYNC_PROC.NAME
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROC.NAME
     *
     * @param name the value for ASYNC_PROC.NAME
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROC.STATUS
     *
     * @return the value of ASYNC_PROC.STATUS
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROC.STATUS
     *
     * @param status the value for ASYNC_PROC.STATUS
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROC.REGISTERED_AT
     *
     * @return the value of ASYNC_PROC.REGISTERED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROC.REGISTERED_AT
     *
     * @param registeredAt the value for ASYNC_PROC.REGISTERED_AT
     *
     * @mbggenerated
     */
    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROC.INVOKED_AT
     *
     * @return the value of ASYNC_PROC.INVOKED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getInvokedAt() {
        return invokedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROC.INVOKED_AT
     *
     * @param invokedAt the value for ASYNC_PROC.INVOKED_AT
     *
     * @mbggenerated
     */
    public void setInvokedAt(LocalDateTime invokedAt) {
        this.invokedAt = invokedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROC.STARTED_AT
     *
     * @return the value of ASYNC_PROC.STARTED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROC.STARTED_AT
     *
     * @param startedAt the value for ASYNC_PROC.STARTED_AT
     *
     * @mbggenerated
     */
    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROC.FINISHED_AT
     *
     * @return the value of ASYNC_PROC.FINISHED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROC.FINISHED_AT
     *
     * @param finishedAt the value for ASYNC_PROC.FINISHED_AT
     *
     * @mbggenerated
     */
    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROC.RESULT
     *
     * @return the value of ASYNC_PROC.RESULT
     *
     * @mbggenerated
     */
    public String getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROC.RESULT
     *
     * @param result the value for ASYNC_PROC.RESULT
     *
     * @mbggenerated
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROC.UPDATED_AT
     *
     * @return the value of ASYNC_PROC.UPDATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROC.UPDATED_AT
     *
     * @param updatedAt the value for ASYNC_PROC.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROC.CREATED_AT
     *
     * @return the value of ASYNC_PROC.CREATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROC.CREATED_AT
     *
     * @param createdAt the value for ASYNC_PROC.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROC.LOCK_VERSION
     *
     * @return the value of ASYNC_PROC.LOCK_VERSION
     *
     * @mbggenerated
     */
    public Integer getLockVersion() {
        return lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROC.LOCK_VERSION
     *
     * @param lockVersion the value for ASYNC_PROC.LOCK_VERSION
     *
     * @mbggenerated
     */
    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROC.DELETED_FLG
     *
     * @return the value of ASYNC_PROC.DELETED_FLG
     *
     * @mbggenerated
     */
    public DeletedFlag getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROC.DELETED_FLG
     *
     * @param deletedFlg the value for ASYNC_PROC.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(DeletedFlag deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROC
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
        AsyncProc other = (AsyncProc) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLauncherId() == null ? other.getLauncherId() == null : this.getLauncherId().equals(other.getLauncherId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRegisteredAt() == null ? other.getRegisteredAt() == null : this.getRegisteredAt().equals(other.getRegisteredAt()))
            && (this.getInvokedAt() == null ? other.getInvokedAt() == null : this.getInvokedAt().equals(other.getInvokedAt()))
            && (this.getStartedAt() == null ? other.getStartedAt() == null : this.getStartedAt().equals(other.getStartedAt()))
            && (this.getFinishedAt() == null ? other.getFinishedAt() == null : this.getFinishedAt().equals(other.getFinishedAt()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getLockVersion() == null ? other.getLockVersion() == null : this.getLockVersion().equals(other.getLockVersion()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROC
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLauncherId() == null) ? 0 : getLauncherId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRegisteredAt() == null) ? 0 : getRegisteredAt().hashCode());
        result = prime * result + ((getInvokedAt() == null) ? 0 : getInvokedAt().hashCode());
        result = prime * result + ((getStartedAt() == null) ? 0 : getStartedAt().hashCode());
        result = prime * result + ((getFinishedAt() == null) ? 0 : getFinishedAt().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getLockVersion() == null) ? 0 : getLockVersion().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROC
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
        sb.append(", launcherId=").append(launcherId);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", registeredAt=").append(registeredAt);
        sb.append(", invokedAt=").append(invokedAt);
        sb.append(", startedAt=").append(startedAt);
        sb.append(", finishedAt=").append(finishedAt);
        sb.append(", result=").append(result);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", lockVersion=").append(lockVersion);
        sb.append(", deletedFlg=").append(deletedFlg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}