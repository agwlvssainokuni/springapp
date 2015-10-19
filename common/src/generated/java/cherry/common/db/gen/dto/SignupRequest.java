package cherry.common.db.gen.dto;

import cherry.foundation.type.DeletedFlag;
import java.io.Serializable;
import org.joda.time.LocalDateTime;

public class SignupRequest implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SIGNUP_REQUEST.ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SIGNUP_REQUEST.MAIL_ADDR
     *
     * @mbggenerated
     */
    private String mailAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SIGNUP_REQUEST.TOKEN
     *
     * @mbggenerated
     */
    private String token;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SIGNUP_REQUEST.APPLIED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime appliedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SIGNUP_REQUEST.UPDATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SIGNUP_REQUEST.CREATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SIGNUP_REQUEST.LOCK_VERSION
     *
     * @mbggenerated
     */
    private Integer lockVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SIGNUP_REQUEST.DELETED_FLG
     *
     * @mbggenerated
     */
    private DeletedFlag deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SIGNUP_REQUEST
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SIGNUP_REQUEST.ID
     *
     * @return the value of SIGNUP_REQUEST.ID
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SIGNUP_REQUEST.ID
     *
     * @param id the value for SIGNUP_REQUEST.ID
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SIGNUP_REQUEST.MAIL_ADDR
     *
     * @return the value of SIGNUP_REQUEST.MAIL_ADDR
     *
     * @mbggenerated
     */
    public String getMailAddr() {
        return mailAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SIGNUP_REQUEST.MAIL_ADDR
     *
     * @param mailAddr the value for SIGNUP_REQUEST.MAIL_ADDR
     *
     * @mbggenerated
     */
    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SIGNUP_REQUEST.TOKEN
     *
     * @return the value of SIGNUP_REQUEST.TOKEN
     *
     * @mbggenerated
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SIGNUP_REQUEST.TOKEN
     *
     * @param token the value for SIGNUP_REQUEST.TOKEN
     *
     * @mbggenerated
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SIGNUP_REQUEST.APPLIED_AT
     *
     * @return the value of SIGNUP_REQUEST.APPLIED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SIGNUP_REQUEST.APPLIED_AT
     *
     * @param appliedAt the value for SIGNUP_REQUEST.APPLIED_AT
     *
     * @mbggenerated
     */
    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SIGNUP_REQUEST.UPDATED_AT
     *
     * @return the value of SIGNUP_REQUEST.UPDATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SIGNUP_REQUEST.UPDATED_AT
     *
     * @param updatedAt the value for SIGNUP_REQUEST.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SIGNUP_REQUEST.CREATED_AT
     *
     * @return the value of SIGNUP_REQUEST.CREATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SIGNUP_REQUEST.CREATED_AT
     *
     * @param createdAt the value for SIGNUP_REQUEST.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SIGNUP_REQUEST.LOCK_VERSION
     *
     * @return the value of SIGNUP_REQUEST.LOCK_VERSION
     *
     * @mbggenerated
     */
    public Integer getLockVersion() {
        return lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SIGNUP_REQUEST.LOCK_VERSION
     *
     * @param lockVersion the value for SIGNUP_REQUEST.LOCK_VERSION
     *
     * @mbggenerated
     */
    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SIGNUP_REQUEST.DELETED_FLG
     *
     * @return the value of SIGNUP_REQUEST.DELETED_FLG
     *
     * @mbggenerated
     */
    public DeletedFlag getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SIGNUP_REQUEST.DELETED_FLG
     *
     * @param deletedFlg the value for SIGNUP_REQUEST.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(DeletedFlag deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SIGNUP_REQUEST
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
        SignupRequest other = (SignupRequest) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMailAddr() == null ? other.getMailAddr() == null : this.getMailAddr().equals(other.getMailAddr()))
            && (this.getToken() == null ? other.getToken() == null : this.getToken().equals(other.getToken()))
            && (this.getAppliedAt() == null ? other.getAppliedAt() == null : this.getAppliedAt().equals(other.getAppliedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getLockVersion() == null ? other.getLockVersion() == null : this.getLockVersion().equals(other.getLockVersion()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SIGNUP_REQUEST
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMailAddr() == null) ? 0 : getMailAddr().hashCode());
        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
        result = prime * result + ((getAppliedAt() == null) ? 0 : getAppliedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getLockVersion() == null) ? 0 : getLockVersion().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SIGNUP_REQUEST
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
        sb.append(", mailAddr=").append(mailAddr);
        sb.append(", token=").append(token);
        sb.append(", appliedAt=").append(appliedAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", lockVersion=").append(lockVersion);
        sb.append(", deletedFlg=").append(deletedFlg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}