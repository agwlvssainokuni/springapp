package cherry.example.db.gen.query;

import javax.annotation.Generated;

/**
 * BBizdatetimeMaster is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BBizdatetimeMaster {

    private org.joda.time.LocalDate bizdate;

    private org.joda.time.LocalDateTime createdAt;

    private Long id;

    private Integer lockVersion;

    private Integer offsetDay;

    private Integer offsetHour;

    private Integer offsetMinute;

    private Integer offsetSecond;

    private org.joda.time.LocalDateTime updatedAt;

    public org.joda.time.LocalDate getBizdate() {
        return bizdate;
    }

    public void setBizdate(org.joda.time.LocalDate bizdate) {
        this.bizdate = bizdate;
    }

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

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "bizdate = " + bizdate + ", createdAt = " + createdAt + ", id = " + id + ", lockVersion = " + lockVersion + ", offsetDay = " + offsetDay + ", offsetHour = " + offsetHour + ", offsetMinute = " + offsetMinute + ", offsetSecond = " + offsetSecond + ", updatedAt = " + updatedAt;
    }

}

