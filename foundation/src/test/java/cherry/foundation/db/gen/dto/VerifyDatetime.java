package cherry.foundation.db.gen.dto;

import java.io.Serializable;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

public class VerifyDatetime implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column VERIFY_DATETIME.ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column VERIFY_DATETIME.DT
     *
     * @mbggenerated
     */
    private LocalDate dt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column VERIFY_DATETIME.TM
     *
     * @mbggenerated
     */
    private LocalTime tm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column VERIFY_DATETIME.DTM
     *
     * @mbggenerated
     */
    private LocalDateTime dtm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table VERIFY_DATETIME
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column VERIFY_DATETIME.ID
     *
     * @return the value of VERIFY_DATETIME.ID
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column VERIFY_DATETIME.ID
     *
     * @param id the value for VERIFY_DATETIME.ID
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column VERIFY_DATETIME.DT
     *
     * @return the value of VERIFY_DATETIME.DT
     *
     * @mbggenerated
     */
    public LocalDate getDt() {
        return dt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column VERIFY_DATETIME.DT
     *
     * @param dt the value for VERIFY_DATETIME.DT
     *
     * @mbggenerated
     */
    public void setDt(LocalDate dt) {
        this.dt = dt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column VERIFY_DATETIME.TM
     *
     * @return the value of VERIFY_DATETIME.TM
     *
     * @mbggenerated
     */
    public LocalTime getTm() {
        return tm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column VERIFY_DATETIME.TM
     *
     * @param tm the value for VERIFY_DATETIME.TM
     *
     * @mbggenerated
     */
    public void setTm(LocalTime tm) {
        this.tm = tm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column VERIFY_DATETIME.DTM
     *
     * @return the value of VERIFY_DATETIME.DTM
     *
     * @mbggenerated
     */
    public LocalDateTime getDtm() {
        return dtm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column VERIFY_DATETIME.DTM
     *
     * @param dtm the value for VERIFY_DATETIME.DTM
     *
     * @mbggenerated
     */
    public void setDtm(LocalDateTime dtm) {
        this.dtm = dtm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_DATETIME
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
        VerifyDatetime other = (VerifyDatetime) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDt() == null ? other.getDt() == null : this.getDt().equals(other.getDt()))
            && (this.getTm() == null ? other.getTm() == null : this.getTm().equals(other.getTm()))
            && (this.getDtm() == null ? other.getDtm() == null : this.getDtm().equals(other.getDtm()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_DATETIME
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDt() == null) ? 0 : getDt().hashCode());
        result = prime * result + ((getTm() == null) ? 0 : getTm().hashCode());
        result = prime * result + ((getDtm() == null) ? 0 : getDtm().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_DATETIME
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dt=").append(dt);
        sb.append(", tm=").append(tm);
        sb.append(", dtm=").append(dtm);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}