package cherry.spring.common.db.gen.query;

import javax.annotation.Generated;

/**
 * SignupRequest is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class SignupRequest {

    private java.sql.Timestamp appliedAt;

    private java.sql.Timestamp createdAt;

    private Integer deletedFlg;

    private Integer id;

    private Integer lockVersion;

    private String mailAddr;

    private String token;

    private java.sql.Timestamp updatedAt;

    public java.sql.Timestamp getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(java.sql.Timestamp appliedAt) {
        this.appliedAt = appliedAt;
    }

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

    public String getMailAddr() {
        return mailAddr;
    }

    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public java.sql.Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.sql.Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}

