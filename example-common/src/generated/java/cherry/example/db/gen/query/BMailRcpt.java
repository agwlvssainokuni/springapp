package cherry.example.db.gen.query;

import javax.annotation.Generated;

/**
 * BMailRcpt is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BMailRcpt {

    private org.joda.time.LocalDateTime createdAt;

    private Long id;

    private Integer lockVersion;

    private Long mailId;

    private String rcptAddr;

    private String rcptType;

    private org.joda.time.LocalDateTime updatedAt;

    public org.joda.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(org.joda.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public Long getMailId() {
        return mailId;
    }

    public void setMailId(Long mailId) {
        this.mailId = mailId;
    }

    public String getRcptAddr() {
        return rcptAddr;
    }

    public void setRcptAddr(String rcptAddr) {
        this.rcptAddr = rcptAddr;
    }

    public String getRcptType() {
        return rcptType;
    }

    public void setRcptType(String rcptType) {
        this.rcptType = rcptType;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "createdAt = " + createdAt + ", id = " + id + ", lockVersion = " + lockVersion + ", mailId = " + mailId + ", rcptAddr = " + rcptAddr + ", rcptType = " + rcptType + ", updatedAt = " + updatedAt;
    }

}

