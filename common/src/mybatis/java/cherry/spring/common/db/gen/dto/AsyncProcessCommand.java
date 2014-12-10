package cherry.spring.common.db.gen.dto;

import cherry.foundation.type.DeletedFlag;
import java.io.Serializable;
import org.joda.time.LocalDateTime;

public class AsyncProcessCommand implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND.ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND.ASYNC_ID
     *
     * @mbggenerated
     */
    private Long asyncId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND.COMMAND
     *
     * @mbggenerated
     */
    private String command;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND.UPDATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND.CREATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND.LOCK_VERSION
     *
     * @mbggenerated
     */
    private Integer lockVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND.DELETED_FLG
     *
     * @mbggenerated
     */
    private DeletedFlag deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ASYNC_PROCESS_COMMAND
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND.ID
     *
     * @return the value of ASYNC_PROCESS_COMMAND.ID
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND.ID
     *
     * @param id the value for ASYNC_PROCESS_COMMAND.ID
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND.ASYNC_ID
     *
     * @return the value of ASYNC_PROCESS_COMMAND.ASYNC_ID
     *
     * @mbggenerated
     */
    public Long getAsyncId() {
        return asyncId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND.ASYNC_ID
     *
     * @param asyncId the value for ASYNC_PROCESS_COMMAND.ASYNC_ID
     *
     * @mbggenerated
     */
    public void setAsyncId(Long asyncId) {
        this.asyncId = asyncId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND.COMMAND
     *
     * @return the value of ASYNC_PROCESS_COMMAND.COMMAND
     *
     * @mbggenerated
     */
    public String getCommand() {
        return command;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND.COMMAND
     *
     * @param command the value for ASYNC_PROCESS_COMMAND.COMMAND
     *
     * @mbggenerated
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND.UPDATED_AT
     *
     * @return the value of ASYNC_PROCESS_COMMAND.UPDATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND.UPDATED_AT
     *
     * @param updatedAt the value for ASYNC_PROCESS_COMMAND.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND.CREATED_AT
     *
     * @return the value of ASYNC_PROCESS_COMMAND.CREATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND.CREATED_AT
     *
     * @param createdAt the value for ASYNC_PROCESS_COMMAND.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND.LOCK_VERSION
     *
     * @return the value of ASYNC_PROCESS_COMMAND.LOCK_VERSION
     *
     * @mbggenerated
     */
    public Integer getLockVersion() {
        return lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND.LOCK_VERSION
     *
     * @param lockVersion the value for ASYNC_PROCESS_COMMAND.LOCK_VERSION
     *
     * @mbggenerated
     */
    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND.DELETED_FLG
     *
     * @return the value of ASYNC_PROCESS_COMMAND.DELETED_FLG
     *
     * @mbggenerated
     */
    public DeletedFlag getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND.DELETED_FLG
     *
     * @param deletedFlg the value for ASYNC_PROCESS_COMMAND.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(DeletedFlag deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND
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
        AsyncProcessCommand other = (AsyncProcessCommand) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAsyncId() == null ? other.getAsyncId() == null : this.getAsyncId().equals(other.getAsyncId()))
            && (this.getCommand() == null ? other.getCommand() == null : this.getCommand().equals(other.getCommand()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getLockVersion() == null ? other.getLockVersion() == null : this.getLockVersion().equals(other.getLockVersion()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAsyncId() == null) ? 0 : getAsyncId().hashCode());
        result = prime * result + ((getCommand() == null) ? 0 : getCommand().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getLockVersion() == null) ? 0 : getLockVersion().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND
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
        sb.append(", command=").append(command);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", lockVersion=").append(lockVersion);
        sb.append(", deletedFlg=").append(deletedFlg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}