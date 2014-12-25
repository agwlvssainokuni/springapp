package cherry.spring.common.db.gen.query;

import javax.annotation.Generated;

/**
 * BSignupRequest is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BSignupRequest {

    private org.joda.time.LocalDateTime appliedAt;

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private Integer id;

    private Integer lockVersion;

    private String mailAddr;

    private String token;

    private org.joda.time.LocalDateTime updatedAt;

    public org.joda.time.LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(org.joda.time.LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

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

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "appliedAt = " + appliedAt + ", createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", id = " + id + ", lockVersion = " + lockVersion + ", mailAddr = " + mailAddr + ", token = " + token + ", updatedAt = " + updatedAt;
    }

}

