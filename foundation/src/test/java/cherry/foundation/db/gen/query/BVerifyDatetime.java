package cherry.foundation.db.gen.query;

import javax.annotation.Generated;

/**
 * BVerifyDatetime is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BVerifyDatetime {

    private org.joda.time.LocalDate dt;

    private org.joda.time.LocalDateTime dtm;

    private Long id;

    private org.joda.time.LocalTime tm;

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

    public org.joda.time.LocalTime getTm() {
        return tm;
    }

    public void setTm(org.joda.time.LocalTime tm) {
        this.tm = tm;
    }

    @Override
    public String toString() {
         return "dt = " + dt + ", dtm = " + dtm + ", id = " + id + ", tm = " + tm;
    }

}

