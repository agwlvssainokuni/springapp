package cherry.spring.common.db.gen.dto;

import cherry.spring.common.db.BaseDto;
import java.util.Date;

public class MailTemplateAddresses extends BaseDto {
    private Integer id;

    private Integer mailTemplateId;

    private String mailAddress;

    private String type;

    private Date updatedAt;

    private Date createdAt;

    private Integer deletedFlg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMailTemplateId() {
        return mailTemplateId;
    }

    public void setMailTemplateId(Integer mailTemplateId) {
        this.mailTemplateId = mailTemplateId;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getDeletedFlg() {
        return deletedFlg;
    }

    public void setDeletedFlg(Integer deletedFlg) {
        this.deletedFlg = deletedFlg;
    }
}