package cherry.spring.common.db.gen.dto;

import cherry.foundation.type.DeletedFlag;
import java.io.Serializable;
import org.joda.time.LocalDateTime;

public class AsyncProcessFileResult implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_FILE_RESULT.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_FILE_RESULT.ASYNC_PROCESS_ID
     *
     * @mbggenerated
     */
    private Integer asyncProcessId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_FILE_RESULT.TOTAL_COUNT
     *
     * @mbggenerated
     */
    private Long totalCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_FILE_RESULT.OK_COUNT
     *
     * @mbggenerated
     */
    private Long okCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_FILE_RESULT.NG_COUNT
     *
     * @mbggenerated
     */
    private Long ngCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_FILE_RESULT.UPDATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_FILE_RESULT.CREATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_FILE_RESULT.LOCK_VERSION
     *
     * @mbggenerated
     */
    private Integer lockVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_FILE_RESULT.DELETED_FLG
     *
     * @mbggenerated
     */
    private DeletedFlag deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ASYNC_PROCESS_FILE_RESULT
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_FILE_RESULT.ID
     *
     * @return the value of ASYNC_PROCESS_FILE_RESULT.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_FILE_RESULT.ID
     *
     * @param id the value for ASYNC_PROCESS_FILE_RESULT.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_FILE_RESULT.ASYNC_PROCESS_ID
     *
     * @return the value of ASYNC_PROCESS_FILE_RESULT.ASYNC_PROCESS_ID
     *
     * @mbggenerated
     */
    public Integer getAsyncProcessId() {
        return asyncProcessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_FILE_RESULT.ASYNC_PROCESS_ID
     *
     * @param asyncProcessId the value for ASYNC_PROCESS_FILE_RESULT.ASYNC_PROCESS_ID
     *
     * @mbggenerated
     */
    public void setAsyncProcessId(Integer asyncProcessId) {
        this.asyncProcessId = asyncProcessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_FILE_RESULT.TOTAL_COUNT
     *
     * @return the value of ASYNC_PROCESS_FILE_RESULT.TOTAL_COUNT
     *
     * @mbggenerated
     */
    public Long getTotalCount() {
        return totalCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_FILE_RESULT.TOTAL_COUNT
     *
     * @param totalCount the value for ASYNC_PROCESS_FILE_RESULT.TOTAL_COUNT
     *
     * @mbggenerated
     */
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_FILE_RESULT.OK_COUNT
     *
     * @return the value of ASYNC_PROCESS_FILE_RESULT.OK_COUNT
     *
     * @mbggenerated
     */
    public Long getOkCount() {
        return okCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_FILE_RESULT.OK_COUNT
     *
     * @param okCount the value for ASYNC_PROCESS_FILE_RESULT.OK_COUNT
     *
     * @mbggenerated
     */
    public void setOkCount(Long okCount) {
        this.okCount = okCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_FILE_RESULT.NG_COUNT
     *
     * @return the value of ASYNC_PROCESS_FILE_RESULT.NG_COUNT
     *
     * @mbggenerated
     */
    public Long getNgCount() {
        return ngCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_FILE_RESULT.NG_COUNT
     *
     * @param ngCount the value for ASYNC_PROCESS_FILE_RESULT.NG_COUNT
     *
     * @mbggenerated
     */
    public void setNgCount(Long ngCount) {
        this.ngCount = ngCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_FILE_RESULT.UPDATED_AT
     *
     * @return the value of ASYNC_PROCESS_FILE_RESULT.UPDATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_FILE_RESULT.UPDATED_AT
     *
     * @param updatedAt the value for ASYNC_PROCESS_FILE_RESULT.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_FILE_RESULT.CREATED_AT
     *
     * @return the value of ASYNC_PROCESS_FILE_RESULT.CREATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_FILE_RESULT.CREATED_AT
     *
     * @param createdAt the value for ASYNC_PROCESS_FILE_RESULT.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_FILE_RESULT.LOCK_VERSION
     *
     * @return the value of ASYNC_PROCESS_FILE_RESULT.LOCK_VERSION
     *
     * @mbggenerated
     */
    public Integer getLockVersion() {
        return lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_FILE_RESULT.LOCK_VERSION
     *
     * @param lockVersion the value for ASYNC_PROCESS_FILE_RESULT.LOCK_VERSION
     *
     * @mbggenerated
     */
    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_FILE_RESULT.DELETED_FLG
     *
     * @return the value of ASYNC_PROCESS_FILE_RESULT.DELETED_FLG
     *
     * @mbggenerated
     */
    public DeletedFlag getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_FILE_RESULT.DELETED_FLG
     *
     * @param deletedFlg the value for ASYNC_PROCESS_FILE_RESULT.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(DeletedFlag deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_FILE_RESULT
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
        AsyncProcessFileResult other = (AsyncProcessFileResult) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAsyncProcessId() == null ? other.getAsyncProcessId() == null : this.getAsyncProcessId().equals(other.getAsyncProcessId()))
            && (this.getTotalCount() == null ? other.getTotalCount() == null : this.getTotalCount().equals(other.getTotalCount()))
            && (this.getOkCount() == null ? other.getOkCount() == null : this.getOkCount().equals(other.getOkCount()))
            && (this.getNgCount() == null ? other.getNgCount() == null : this.getNgCount().equals(other.getNgCount()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getLockVersion() == null ? other.getLockVersion() == null : this.getLockVersion().equals(other.getLockVersion()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_FILE_RESULT
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAsyncProcessId() == null) ? 0 : getAsyncProcessId().hashCode());
        result = prime * result + ((getTotalCount() == null) ? 0 : getTotalCount().hashCode());
        result = prime * result + ((getOkCount() == null) ? 0 : getOkCount().hashCode());
        result = prime * result + ((getNgCount() == null) ? 0 : getNgCount().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getLockVersion() == null) ? 0 : getLockVersion().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_FILE_RESULT
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
        sb.append(", asyncProcessId=").append(asyncProcessId);
        sb.append(", totalCount=").append(totalCount);
        sb.append(", okCount=").append(okCount);
        sb.append(", ngCount=").append(ngCount);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", lockVersion=").append(lockVersion);
        sb.append(", deletedFlg=").append(deletedFlg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}