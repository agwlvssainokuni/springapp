package cherry.spring.common.db.gen.query;

import javax.annotation.Generated;

/**
 * User is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class User {

    private java.sql.Timestamp createdAt;

    private Integer deletedFlg;

    private String firstName;

    private Integer id;

    private String lastName;

    private Integer lockVersion;

    private String loginId;

    private String password;

    private java.sql.Timestamp registeredAt;

    private java.sql.Timestamp updatedAt;

    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
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

    public java.sql.Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(java.sql.Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }

    public java.sql.Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.sql.Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}

