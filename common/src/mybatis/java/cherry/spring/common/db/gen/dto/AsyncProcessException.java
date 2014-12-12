package cherry.spring.common.db.gen.dto;

import cherry.foundation.type.DeletedFlag;
import java.io.Serializable;
import org.joda.time.LocalDateTime;

public class AsyncProcessException implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_EXCEPTION.ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_EXCEPTION.ASYNC_ID
     *
     * @mbggenerated
     */
    private Long asyncId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_EXCEPTION.UPDATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_EXCEPTION.CREATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_EXCEPTION.LOCK_VERSION
     *
     * @mbggenerated
     */
    private Integer lockVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_EXCEPTION.DELETED_FLG
     *
     * @mbggenerated
     */
    private DeletedFlag deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_EXCEPTION.EXCEPTION
     *
     * @mbggenerated
     */
    private String exception;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_EXCEPTION.ID
     *
     * @return the value of ASYNC_PROCESS_EXCEPTION.ID
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_EXCEPTION.ID
     *
     * @param id the value for ASYNC_PROCESS_EXCEPTION.ID
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_EXCEPTION.ASYNC_ID
     *
     * @return the value of ASYNC_PROCESS_EXCEPTION.ASYNC_ID
     *
     * @mbggenerated
     */
    public Long getAsyncId() {
        return asyncId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_EXCEPTION.ASYNC_ID
     *
     * @param asyncId the value for ASYNC_PROCESS_EXCEPTION.ASYNC_ID
     *
     * @mbggenerated
     */
    public void setAsyncId(Long asyncId) {
        this.asyncId = asyncId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_EXCEPTION.UPDATED_AT
     *
     * @return the value of ASYNC_PROCESS_EXCEPTION.UPDATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_EXCEPTION.UPDATED_AT
     *
     * @param updatedAt the value for ASYNC_PROCESS_EXCEPTION.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_EXCEPTION.CREATED_AT
     *
     * @return the value of ASYNC_PROCESS_EXCEPTION.CREATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_EXCEPTION.CREATED_AT
     *
     * @param createdAt the value for ASYNC_PROCESS_EXCEPTION.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_EXCEPTION.LOCK_VERSION
     *
     * @return the value of ASYNC_PROCESS_EXCEPTION.LOCK_VERSION
     *
     * @mbggenerated
     */
    public Integer getLockVersion() {
        return lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_EXCEPTION.LOCK_VERSION
     *
     * @param lockVersion the value for ASYNC_PROCESS_EXCEPTION.LOCK_VERSION
     *
     * @mbggenerated
     */
    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_EXCEPTION.DELETED_FLG
     *
     * @return the value of ASYNC_PROCESS_EXCEPTION.DELETED_FLG
     *
     * @mbggenerated
     */
    public DeletedFlag getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_EXCEPTION.DELETED_FLG
     *
     * @param deletedFlg the value for ASYNC_PROCESS_EXCEPTION.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(DeletedFlag deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_EXCEPTION.EXCEPTION
     *
     * @return the value of ASYNC_PROCESS_EXCEPTION.EXCEPTION
     *
     * @mbggenerated
     */
    public String getException() {
        return exception;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_EXCEPTION.EXCEPTION
     *
     * @param exception the value for ASYNC_PROCESS_EXCEPTION.EXCEPTION
     *
     * @mbggenerated
     */
    public void setException(String exception) {
        this.exception = exception;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
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
        AsyncProcessException other = (AsyncProcessException) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAsyncId() == null ? other.getAsyncId() == null : this.getAsyncId().equals(other.getAsyncId()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getLockVersion() == null ? other.getLockVersion() == null : this.getLockVersion().equals(other.getLockVersion()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()))
            && (this.getException() == null ? other.getException() == null : this.getException().equals(other.getException()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAsyncId() == null) ? 0 : getAsyncId().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getLockVersion() == null) ? 0 : getLockVersion().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        result = prime * result + ((getException() == null) ? 0 : getException().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
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
        sb.append(", asyncId=").append(asyncId);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", lockVersion=").append(lockVersion);
        sb.append(", deletedFlg=").append(deletedFlg);
        sb.append(", exception=").append(exception);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}