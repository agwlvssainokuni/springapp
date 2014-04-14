package cherry.spring.common.db.gen.dto;

import cherry.spring.common.db.BaseDto;
import java.io.Serializable;
import java.util.Date;

public class Users extends BaseDto implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.MAIL_ADDR
     *
     * @mbggenerated
     */
    private String mailAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.PASSWORD
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.REGISTERED_AT
     *
     * @mbggenerated
     */
    private Date registeredAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.FIRST_NAME
     *
     * @mbggenerated
     */
    private String firstName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.LAST_NAME
     *
     * @mbggenerated
     */
    private String lastName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.UPDATED_AT
     *
     * @mbggenerated
     */
    private Date updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.CREATED_AT
     *
     * @mbggenerated
     */
    private Date createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.DELETED_FLG
     *
     * @mbggenerated
     */
    private Integer deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table USERS
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.ID
     *
     * @return the value of USERS.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.ID
     *
     * @param id the value for USERS.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.MAIL_ADDR
     *
     * @return the value of USERS.MAIL_ADDR
     *
     * @mbggenerated
     */
    public String getMailAddr() {
        return mailAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.MAIL_ADDR
     *
     * @param mailAddr the value for USERS.MAIL_ADDR
     *
     * @mbggenerated
     */
    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.PASSWORD
     *
     * @return the value of USERS.PASSWORD
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.PASSWORD
     *
     * @param password the value for USERS.PASSWORD
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.REGISTERED_AT
     *
     * @return the value of USERS.REGISTERED_AT
     *
     * @mbggenerated
     */
    public Date getRegisteredAt() {
        return registeredAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.REGISTERED_AT
     *
     * @param registeredAt the value for USERS.REGISTERED_AT
     *
     * @mbggenerated
     */
    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.FIRST_NAME
     *
     * @return the value of USERS.FIRST_NAME
     *
     * @mbggenerated
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.FIRST_NAME
     *
     * @param firstName the value for USERS.FIRST_NAME
     *
     * @mbggenerated
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.LAST_NAME
     *
     * @return the value of USERS.LAST_NAME
     *
     * @mbggenerated
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.LAST_NAME
     *
     * @param lastName the value for USERS.LAST_NAME
     *
     * @mbggenerated
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.UPDATED_AT
     *
     * @return the value of USERS.UPDATED_AT
     *
     * @mbggenerated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.UPDATED_AT
     *
     * @param updatedAt the value for USERS.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.CREATED_AT
     *
     * @return the value of USERS.CREATED_AT
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.CREATED_AT
     *
     * @param createdAt the value for USERS.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.DELETED_FLG
     *
     * @return the value of USERS.DELETED_FLG
     *
     * @mbggenerated
     */
    public Integer getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.DELETED_FLG
     *
     * @param deletedFlg the value for USERS.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(Integer deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
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
        Users other = (Users) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMailAddr() == null ? other.getMailAddr() == null : this.getMailAddr().equals(other.getMailAddr()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getRegisteredAt() == null ? other.getRegisteredAt() == null : this.getRegisteredAt().equals(other.getRegisteredAt()))
            && (this.getFirstName() == null ? other.getFirstName() == null : this.getFirstName().equals(other.getFirstName()))
            && (this.getLastName() == null ? other.getLastName() == null : this.getLastName().equals(other.getLastName()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMailAddr() == null) ? 0 : getMailAddr().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getRegisteredAt() == null) ? 0 : getRegisteredAt().hashCode());
        result = prime * result + ((getFirstName() == null) ? 0 : getFirstName().hashCode());
        result = prime * result + ((getLastName() == null) ? 0 : getLastName().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        return result;
    }
}