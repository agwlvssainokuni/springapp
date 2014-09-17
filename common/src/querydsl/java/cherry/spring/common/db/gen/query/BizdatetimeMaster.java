package cherry.spring.common.db.gen.query;

import javax.annotation.Generated;

/**
 * BizdatetimeMaster is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BizdatetimeMaster {

    private java.sql.Date bizdate;

    private java.sql.Timestamp createdAt;

    private Integer deletedFlg;

    private Integer id;

    private Integer lockVersion;

    private Integer offsetDay;

    private Integer offsetHour;

    private Integer offsetMinute;

    private Integer offsetSecond;

    private java.sql.Timestamp updatedAt;

    public java.sql.Date getBizdate() {
        return bizdate;
    }

    public void setBizdate(java.sql.Date bizdate) {
        this.bizdate = bizdate;
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

    public Integer getOffsetDay() {
        return offsetDay;
    }

    public void setOffsetDay(Integer offsetDay) {
        this.offsetDay = offsetDay;
    }

    public Integer getOffsetHour() {
        return offsetHour;
    }

    public void setOffsetHour(Integer offsetHour) {
        this.offsetHour = offsetHour;
    }

    public Integer getOffsetMinute() {
        return offsetMinute;
    }

    public void setOffsetMinute(Integer offsetMinute) {
        this.offsetMinute = offsetMinute;
    }

    public Integer getOffsetSecond() {
        return offsetSecond;
    }

    public void setOffsetSecond(Integer offsetSecond) {
        this.offsetSecond = offsetSecond;
    }

    public java.sql.Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.sql.Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}

