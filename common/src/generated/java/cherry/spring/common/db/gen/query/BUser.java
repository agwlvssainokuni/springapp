package cherry.spring.common.db.gen.query;

import javax.annotation.Generated;

/**
 * BUser is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BUser {

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private String firstName;

    private Integer id;

    private String lastName;

    private Integer lockVersion;

    private String loginId;

    private String password;

    private org.joda.time.LocalDateTime registeredAt;

    private org.joda.time.LocalDateTime updatedAt;

    public org.joda.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(org.joda.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getDeletedFlg() {
        return deletedFlg;
    }

    public void setDeletedFlg(Integer deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public org.joda.time.LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(org.joda.time.LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", firstName = " + firstName + ", id = " + id + ", lastName = " + lastName + ", lockVersion = " + lockVersion + ", loginId = " + loginId + ", password = " + password + ", registeredAt = " + registeredAt + ", updatedAt = " + updatedAt;
    }

}

