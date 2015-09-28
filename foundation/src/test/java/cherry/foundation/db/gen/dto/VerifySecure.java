package cherry.foundation.db.gen.dto;

import cherry.foundation.type.SecureBigDecimal;
import cherry.foundation.type.SecureBigInteger;
import cherry.foundation.type.SecureInteger;
import cherry.foundation.type.SecureLong;
import cherry.foundation.type.SecureString;
import java.io.Serializable;

public class VerifySecure implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column VERIFY_SECURE.ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column VERIFY_SECURE.STR
     *
     * @mbggenerated
     */
    private SecureString str;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column VERIFY_SECURE.INT32
     *
     * @mbggenerated
     */
    private SecureInteger int32;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column VERIFY_SECURE.INT64
     *
     * @mbggenerated
     */
    private SecureLong int64;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column VERIFY_SECURE.BIGINT
     *
     * @mbggenerated
     */
    private SecureBigInteger bigint;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column VERIFY_SECURE.BIGDEC
     *
     * @mbggenerated
     */
    private SecureBigDecimal bigdec;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table VERIFY_SECURE
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column VERIFY_SECURE.ID
     *
     * @return the value of VERIFY_SECURE.ID
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column VERIFY_SECURE.ID
     *
     * @param id the value for VERIFY_SECURE.ID
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column VERIFY_SECURE.STR
     *
     * @return the value of VERIFY_SECURE.STR
     *
     * @mbggenerated
     */
    public SecureString getStr() {
        return str;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column VERIFY_SECURE.STR
     *
     * @param str the value for VERIFY_SECURE.STR
     *
     * @mbggenerated
     */
    public void setStr(SecureString str) {
        this.str = str;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column VERIFY_SECURE.INT32
     *
     * @return the value of VERIFY_SECURE.INT32
     *
     * @mbggenerated
     */
    public SecureInteger getInt32() {
        return int32;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column VERIFY_SECURE.INT32
     *
     * @param int32 the value for VERIFY_SECURE.INT32
     *
     * @mbggenerated
     */
    public void setInt32(SecureInteger int32) {
        this.int32 = int32;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column VERIFY_SECURE.INT64
     *
     * @return the value of VERIFY_SECURE.INT64
     *
     * @mbggenerated
     */
    public SecureLong getInt64() {
        return int64;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column VERIFY_SECURE.INT64
     *
     * @param int64 the value for VERIFY_SECURE.INT64
     *
     * @mbggenerated
     */
    public void setInt64(SecureLong int64) {
        this.int64 = int64;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column VERIFY_SECURE.BIGINT
     *
     * @return the value of VERIFY_SECURE.BIGINT
     *
     * @mbggenerated
     */
    public SecureBigInteger getBigint() {
        return bigint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column VERIFY_SECURE.BIGINT
     *
     * @param bigint the value for VERIFY_SECURE.BIGINT
     *
     * @mbggenerated
     */
    public void setBigint(SecureBigInteger bigint) {
        this.bigint = bigint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column VERIFY_SECURE.BIGDEC
     *
     * @return the value of VERIFY_SECURE.BIGDEC
     *
     * @mbggenerated
     */
    public SecureBigDecimal getBigdec() {
        return bigdec;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column VERIFY_SECURE.BIGDEC
     *
     * @param bigdec the value for VERIFY_SECURE.BIGDEC
     *
     * @mbggenerated
     */
    public void setBigdec(SecureBigDecimal bigdec) {
        this.bigdec = bigdec;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_SECURE
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
        VerifySecure other = (VerifySecure) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStr() == null ? other.getStr() == null : this.getStr().equals(other.getStr()))
            && (this.getInt32() == null ? other.getInt32() == null : this.getInt32().equals(other.getInt32()))
            && (this.getInt64() == null ? other.getInt64() == null : this.getInt64().equals(other.getInt64()))
            && (this.getBigint() == null ? other.getBigint() == null : this.getBigint().equals(other.getBigint()))
            && (this.getBigdec() == null ? other.getBigdec() == null : this.getBigdec().equals(other.getBigdec()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_SECURE
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStr() == null) ? 0 : getStr().hashCode());
        result = prime * result + ((getInt32() == null) ? 0 : getInt32().hashCode());
        result = prime * result + ((getInt64() == null) ? 0 : getInt64().hashCode());
        result = prime * result + ((getBigint() == null) ? 0 : getBigint().hashCode());
        result = prime * result + ((getBigdec() == null) ? 0 : getBigdec().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_SECURE
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
        sb.append(", str=").append(str);
        sb.append(", int32=").append(int32);
        sb.append(", int64=").append(int64);
        sb.append(", bigint=").append(bigint);
        sb.append(", bigdec=").append(bigdec);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}