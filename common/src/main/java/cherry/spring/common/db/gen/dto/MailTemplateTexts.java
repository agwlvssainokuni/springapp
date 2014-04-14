package cherry.spring.common.db.gen.dto;

import cherry.spring.common.db.BaseDto;
import java.io.Serializable;
import java.util.Date;

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
    private Date updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_TEXTS.CREATED_AT
     *
     * @mbggenerated
     */
    private Date createdAt;

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
    public Date getUpdatedAt() {
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
    public void setUpdatedAt(Date updatedAt) {
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
    public Date getCreatedAt() {
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
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
}