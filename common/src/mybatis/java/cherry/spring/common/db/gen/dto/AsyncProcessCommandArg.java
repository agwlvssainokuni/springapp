package cherry.spring.common.db.gen.dto;

import cherry.foundation.type.DeletedFlag;
import java.io.Serializable;
import org.joda.time.LocalDateTime;

public class AsyncProcessCommandArg implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_ARG.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_ARG.ASYNC_PROCESS_ID
     *
     * @mbggenerated
     */
    private Integer asyncProcessId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_ARG.ARGUMENT
     *
     * @mbggenerated
     */
    private String argument;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_ARG.UPDATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_ARG.CREATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_ARG.LOCK_VERSION
     *
     * @mbggenerated
     */
    private Integer lockVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_ARG.DELETED_FLG
     *
     * @mbggenerated
     */
    private DeletedFlag deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ASYNC_PROCESS_COMMAND_ARG
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_ARG.ID
     *
     * @return the value of ASYNC_PROCESS_COMMAND_ARG.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_ARG.ID
     *
     * @param id the value for ASYNC_PROCESS_COMMAND_ARG.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_ARG.ASYNC_PROCESS_ID
     *
     * @return the value of ASYNC_PROCESS_COMMAND_ARG.ASYNC_PROCESS_ID
     *
     * @mbggenerated
     */
    public Integer getAsyncProcessId() {
        return asyncProcessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_ARG.ASYNC_PROCESS_ID
     *
     * @param asyncProcessId the value for ASYNC_PROCESS_COMMAND_ARG.ASYNC_PROCESS_ID
     *
     * @mbggenerated
     */
    public void setAsyncProcessId(Integer asyncProcessId) {
        this.asyncProcessId = asyncProcessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_ARG.ARGUMENT
     *
     * @return the value of ASYNC_PROCESS_COMMAND_ARG.ARGUMENT
     *
     * @mbggenerated
     */
    public String getArgument() {
        return argument;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_ARG.ARGUMENT
     *
     * @param argument the value for ASYNC_PROCESS_COMMAND_ARG.ARGUMENT
     *
     * @mbggenerated
     */
    public void setArgument(String argument) {
        this.argument = argument;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_ARG.UPDATED_AT
     *
     * @return the value of ASYNC_PROCESS_COMMAND_ARG.UPDATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_ARG.UPDATED_AT
     *
     * @param updatedAt the value for ASYNC_PROCESS_COMMAND_ARG.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_ARG.CREATED_AT
     *
     * @return the value of ASYNC_PROCESS_COMMAND_ARG.CREATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_ARG.CREATED_AT
     *
     * @param createdAt the value for ASYNC_PROCESS_COMMAND_ARG.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_ARG.LOCK_VERSION
     *
     * @return the value of ASYNC_PROCESS_COMMAND_ARG.LOCK_VERSION
     *
     * @mbggenerated
     */
    public Integer getLockVersion() {
        return lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_ARG.LOCK_VERSION
     *
     * @param lockVersion the value for ASYNC_PROCESS_COMMAND_ARG.LOCK_VERSION
     *
     * @mbggenerated
     */
    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_ARG.DELETED_FLG
     *
     * @return the value of ASYNC_PROCESS_COMMAND_ARG.DELETED_FLG
     *
     * @mbggenerated
     */
    public DeletedFlag getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_ARG.DELETED_FLG
     *
     * @param deletedFlg the value for ASYNC_PROCESS_COMMAND_ARG.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(DeletedFlag deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND_ARG
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
        AsyncProcessCommandArg other = (AsyncProcessCommandArg) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAsyncProcessId() == null ? other.getAsyncProcessId() == null : this.getAsyncProcessId().equals(other.getAsyncProcessId()))
            && (this.getArgument() == null ? other.getArgument() == null : this.getArgument().equals(other.getArgument()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getLockVersion() == null ? other.getLockVersion() == null : this.getLockVersion().equals(other.getLockVersion()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND_ARG
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAsyncProcessId() == null) ? 0 : getAsyncProcessId().hashCode());
        result = prime * result + ((getArgument() == null) ? 0 : getArgument().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getLockVersion() == null) ? 0 : getLockVersion().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND_ARG
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
        sb.append(", argument=").append(argument);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", lockVersion=").append(lockVersion);
        sb.append(", deletedFlg=").append(deletedFlg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}