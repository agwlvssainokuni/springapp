package cherry.example.db.gen.query;

import javax.annotation.Generated;

/**
 * BExTbl1 is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BExTbl1 {

    private String code2;

    private org.joda.time.LocalDateTime createdAt;

    private java.math.BigDecimal decimal1;

    private java.math.BigDecimal decimal3;

    private org.joda.time.LocalDate dt;

    private org.joda.time.LocalDateTime dtm;

    private Long id;

    private Long int64;

    private Integer lockVersion;

    private String text10;

    private String text100;

    private org.joda.time.LocalTime tm;

    private org.joda.time.LocalDateTime updatedAt;

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public org.joda.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(org.joda.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public java.math.BigDecimal getDecimal1() {
        return decimal1;
    }

    public void setDecimal1(java.math.BigDecimal decimal1) {
        this.decimal1 = decimal1;
    }

    public java.math.BigDecimal getDecimal3() {
        return decimal3;
    }

    public void setDecimal3(java.math.BigDecimal decimal3) {
        this.decimal3 = decimal3;
    }

    public org.joda.time.LocalDate getDt() {
        return dt;
    }

    public void setDt(org.joda.time.LocalDate dt) {
        this.dt = dt;
    }

    public org.joda.time.LocalDateTime getDtm() {
        return dtm;
    }

    public void setDtm(org.joda.time.LocalDateTime dtm) {
        this.dtm = dtm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInt64() {
        return int64;
    }

    public void setInt64(Long int64) {
        this.int64 = int64;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public String getText10() {
        return text10;
    }

    public void setText10(String text10) {
        this.text10 = text10;
    }

    public String getText100() {
        return text100;
    }

    public void setText100(String text100) {
        this.text100 = text100;
    }

    public org.joda.time.LocalTime getTm() {
        return tm;
    }

    public void setTm(org.joda.time.LocalTime tm) {
        this.tm = tm;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "code2 = " + code2 + ", createdAt = " + createdAt + ", decimal1 = " + decimal1 + ", decimal3 = " + decimal3 + ", dt = " + dt + ", dtm = " + dtm + ", id = " + id + ", int64 = " + int64 + ", lockVersion = " + lockVersion + ", text10 = " + text10 + ", text100 = " + text100 + ", tm = " + tm + ", updatedAt = " + updatedAt;
    }

}

