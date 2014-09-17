package cherry.spring.common.db.gen.query;

import javax.annotation.Generated;

/**
 * MailTemplateAddress is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class MailTemplateAddress {

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private Integer id;

    private Integer lockVersion;

    private String mailAddr;

    private Integer mailTemplateId;

    private String rcptType;

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

    public Integer getMailTemplateId() {
        return mailTemplateId;
    }

    public void setMailTemplateId(Integer mailTemplateId) {
        this.mailTemplateId = mailTemplateId;
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

}

