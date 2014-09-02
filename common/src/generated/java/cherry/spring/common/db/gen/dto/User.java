package cherry.spring.common.db.gen.dto;

import java.io.Serializable;
import org.joda.time.LocalDateTime;

public class User implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.LOGIN_ID
     *
     * @mbggenerated
     */
    private String loginId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.PASSWORD
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.REGISTERED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime registeredAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.FIRST_NAME
     *
     * @mbggenerated
     */
    private String firstName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.LAST_NAME
     *
     * @mbggenerated
     */
    private String lastName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.UPDATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.CREATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.LOCK_VERSION
     *
     * @mbggenerated
     */
    private Integer lockVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.DELETED_FLG
     *
     * @mbggenerated
     */
    private Integer deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table USER
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.ID
     *
     * @return the value of USER.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.ID
     *
     * @param id the value for USER.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.LOGIN_ID
     *
     * @return the value of USER.LOGIN_ID
     *
     * @mbggenerated
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.LOGIN_ID
     *
     * @param loginId the value for USER.LOGIN_ID
     *
     * @mbggenerated
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.PASSWORD
     *
     * @return the value of USER.PASSWORD
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.PASSWORD
     *
     * @param password the value for USER.PASSWORD
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.REGISTERED_AT
     *
     * @return the value of USER.REGISTERED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.REGISTERED_AT
     *
     * @param registeredAt the value for USER.REGISTERED_AT
     *
     * @mbggenerated
     */
    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.FIRST_NAME
     *
     * @return the value of USER.FIRST_NAME
     *
     * @mbggenerated
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.FIRST_NAME
     *
     * @param firstName the value for USER.FIRST_NAME
     *
     * @mbggenerated
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.LAST_NAME
     *
     * @return the value of USER.LAST_NAME
     *
     * @mbggenerated
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.LAST_NAME
     *
     * @param lastName the value for USER.LAST_NAME
     *
     * @mbggenerated
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.UPDATED_AT
     *
     * @return the value of USER.UPDATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.UPDATED_AT
     *
     * @param updatedAt the value for USER.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.CREATED_AT
     *
     * @return the value of USER.CREATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.CREATED_AT
     *
     * @param createdAt the value for USER.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.LOCK_VERSION
     *
     * @return the value of USER.LOCK_VERSION
     *
     * @mbggenerated
     */
    public Integer getLockVersion() {
        return lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.LOCK_VERSION
     *
     * @param lockVersion the value for USER.LOCK_VERSION
     *
     * @mbggenerated
     */
    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.DELETED_FLG
     *
     * @return the value of USER.DELETED_FLG
     *
     * @mbggenerated
     */
    public Integer getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.DELETED_FLG
     *
     * @param deletedFlg the value for USER.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(Integer deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER
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
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLoginId() == null ? other.getLoginId() == null : this.getLoginId().equals(other.getLoginId()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getRegisteredAt() == null ? other.getRegisteredAt() == null : this.getRegisteredAt().equals(other.getRegisteredAt()))
            && (this.getFirstName() == null ? other.getFirstName() == null : this.getFirstName().equals(other.getFirstName()))
            && (this.getLastName() == null ? other.getLastName() == null : this.getLastName().equals(other.getLastName()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getLockVersion() == null ? other.getLockVersion() == null : this.getLockVersion().equals(other.getLockVersion()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoginId() == null) ? 0 : getLoginId().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getRegisteredAt() == null) ? 0 : getRegisteredAt().hashCode());
        result = prime * result + ((getFirstName() == null) ? 0 : getFirstName().hashCode());
        result = prime * result + ((getLastName() == null) ? 0 : getLastName().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getLockVersion() == null) ? 0 : getLockVersion().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER
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
        sb.append(", loginId=").append(loginId);
        sb.append(", password=").append(password);
        sb.append(", registeredAt=").append(registeredAt);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", lockVersion=").append(lockVersion);
        sb.append(", deletedFlg=").append(deletedFlg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}