package cherry.sqlman.db.gen.query;

import javax.annotation.Generated;

/**
 * BUserAccount is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BUserAccount {

    private org.joda.time.LocalDateTime createdAt;

    private Integer id;

    private Integer lockVersion;

    private String loginId;

    private String mailAddr;

    private String password;

    private org.joda.time.LocalDateTime updatedAt;

    public org.joda.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(org.joda.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMailAddr() {
        return mailAddr;
    }

    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "createdAt = " + createdAt + ", id = " + id + ", lockVersion = " + lockVersion + ", loginId = " + loginId + ", mailAddr = " + mailAddr + ", password = " + password + ", updatedAt = " + updatedAt;
    }

}

