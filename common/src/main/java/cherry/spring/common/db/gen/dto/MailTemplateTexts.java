package cherry.spring.common.db.gen.dto;

import cherry.spring.common.db.BaseDto;
import java.io.Serializable;
import org.joda.time.LocalDateTime;

public class MailTemplateTexts extends BaseDto implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_TEXTS.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_TEXTS.MAIL_TEMPLATE_ID
     *
     * @mbggenerated
     */
    private Integer mailTemplateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_TEXTS.LOCALE
     *
     * @mbggenerated
     */
    private String locale;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_TEXTS.SUBJECT
     *
     * @mbggenerated
     */
    private String subject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_TEXTS.BODY
     *
     * @mbggenerated
     */
    private String body;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_TEXTS.UPDATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_TEXTS.CREATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_TEXTS.LOCK_VERSION
     *
     * @mbggenerated
     */
    private Integer lockVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_TEXTS.DELETED_FLG
     *
     * @mbggenerated
     */
    private Integer deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table MAIL_TEMPLATE_TEXTS
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_TEXTS.ID
     *
     * @return the value of MAIL_TEMPLATE_TEXTS.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_TEXTS.ID
     *
     * @param id the value for MAIL_TEMPLATE_TEXTS.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_TEXTS.MAIL_TEMPLATE_ID
     *
     * @return the value of MAIL_TEMPLATE_TEXTS.MAIL_TEMPLATE_ID
     *
     * @mbggenerated
     */
    public Integer getMailTemplateId() {
        return mailTemplateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_TEXTS.MAIL_TEMPLATE_ID
     *
     * @param mailTemplateId the value for MAIL_TEMPLATE_TEXTS.MAIL_TEMPLATE_ID
     *
     * @mbggenerated
     */
    public void setMailTemplateId(Integer mailTemplateId) {
        this.mailTemplateId = mailTemplateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_TEXTS.LOCALE
     *
     * @return the value of MAIL_TEMPLATE_TEXTS.LOCALE
     *
     * @mbggenerated
     */
    public String getLocale() {
        return locale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_TEXTS.LOCALE
     *
     * @param locale the value for MAIL_TEMPLATE_TEXTS.LOCALE
     *
     * @mbggenerated
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_TEXTS.SUBJECT
     *
     * @return the value of MAIL_TEMPLATE_TEXTS.SUBJECT
     *
     * @mbggenerated
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_TEXTS.SUBJECT
     *
     * @param subject the value for MAIL_TEMPLATE_TEXTS.SUBJECT
     *
     * @mbggenerated
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_TEXTS.BODY
     *
     * @return the value of MAIL_TEMPLATE_TEXTS.BODY
     *
     * @mbggenerated
     */
    public String getBody() {
        return body;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_TEXTS.BODY
     *
     * @param body the value for MAIL_TEMPLATE_TEXTS.BODY
     *
     * @mbggenerated
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_TEXTS.UPDATED_AT
     *
     * @return the value of MAIL_TEMPLATE_TEXTS.UPDATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_TEXTS.UPDATED_AT
     *
     * @param updatedAt the value for MAIL_TEMPLATE_TEXTS.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_TEXTS.CREATED_AT
     *
     * @return the value of MAIL_TEMPLATE_TEXTS.CREATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_TEXTS.CREATED_AT
     *
     * @param createdAt the value for MAIL_TEMPLATE_TEXTS.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_TEXTS.LOCK_VERSION
     *
     * @return the value of MAIL_TEMPLATE_TEXTS.LOCK_VERSION
     *
     * @mbggenerated
     */
    public Integer getLockVersion() {
        return lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_TEXTS.LOCK_VERSION
     *
     * @param lockVersion the value for MAIL_TEMPLATE_TEXTS.LOCK_VERSION
     *
     * @mbggenerated
     */
    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_TEXTS.DELETED_FLG
     *
     * @return the value of MAIL_TEMPLATE_TEXTS.DELETED_FLG
     *
     * @mbggenerated
     */
    public Integer getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_TEXTS.DELETED_FLG
     *
     * @param deletedFlg the value for MAIL_TEMPLATE_TEXTS.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(Integer deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_TEXTS
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
        MailTemplateTexts other = (MailTemplateTexts) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMailTemplateId() == null ? other.getMailTemplateId() == null : this.getMailTemplateId().equals(other.getMailTemplateId()))
            && (this.getLocale() == null ? other.getLocale() == null : this.getLocale().equals(other.getLocale()))
            && (this.getSubject() == null ? other.getSubject() == null : this.getSubject().equals(other.getSubject()))
            && (this.getBody() == null ? other.getBody() == null : this.getBody().equals(other.getBody()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getLockVersion() == null ? other.getLockVersion() == null : this.getLockVersion().equals(other.getLockVersion()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_TEXTS
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMailTemplateId() == null) ? 0 : getMailTemplateId().hashCode());
        result = prime * result + ((getLocale() == null) ? 0 : getLocale().hashCode());
        result = prime * result + ((getSubject() == null) ? 0 : getSubject().hashCode());
        result = prime * result + ((getBody() == null) ? 0 : getBody().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getLockVersion() == null) ? 0 : getLockVersion().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        return result;
    }
}