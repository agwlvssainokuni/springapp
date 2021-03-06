package cherry.common.db.gen.dto;

import cherry.foundation.type.DeletedFlag;
import java.io.Serializable;
import org.joda.time.LocalDateTime;

public class ZipcdMaster implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZIPCD_MASTER.ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZIPCD_MASTER.CITY_CD
     *
     * @mbggenerated
     */
    private Integer cityCd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZIPCD_MASTER.ZIPCD
     *
     * @mbggenerated
     */
    private String zipcd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZIPCD_MASTER.PREF
     *
     * @mbggenerated
     */
    private String pref;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZIPCD_MASTER.CITY
     *
     * @mbggenerated
     */
    private String city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZIPCD_MASTER.ADDR
     *
     * @mbggenerated
     */
    private String addr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZIPCD_MASTER.PREF_KANA
     *
     * @mbggenerated
     */
    private String prefKana;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZIPCD_MASTER.CITY_KANA
     *
     * @mbggenerated
     */
    private String cityKana;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZIPCD_MASTER.ADDR_KANA
     *
     * @mbggenerated
     */
    private String addrKana;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZIPCD_MASTER.UPDATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZIPCD_MASTER.CREATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZIPCD_MASTER.LOCK_VERSION
     *
     * @mbggenerated
     */
    private Integer lockVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZIPCD_MASTER.DELETED_FLG
     *
     * @mbggenerated
     */
    private DeletedFlag deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZIPCD_MASTER.ID
     *
     * @return the value of ZIPCD_MASTER.ID
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZIPCD_MASTER.ID
     *
     * @param id the value for ZIPCD_MASTER.ID
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZIPCD_MASTER.CITY_CD
     *
     * @return the value of ZIPCD_MASTER.CITY_CD
     *
     * @mbggenerated
     */
    public Integer getCityCd() {
        return cityCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZIPCD_MASTER.CITY_CD
     *
     * @param cityCd the value for ZIPCD_MASTER.CITY_CD
     *
     * @mbggenerated
     */
    public void setCityCd(Integer cityCd) {
        this.cityCd = cityCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZIPCD_MASTER.ZIPCD
     *
     * @return the value of ZIPCD_MASTER.ZIPCD
     *
     * @mbggenerated
     */
    public String getZipcd() {
        return zipcd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZIPCD_MASTER.ZIPCD
     *
     * @param zipcd the value for ZIPCD_MASTER.ZIPCD
     *
     * @mbggenerated
     */
    public void setZipcd(String zipcd) {
        this.zipcd = zipcd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZIPCD_MASTER.PREF
     *
     * @return the value of ZIPCD_MASTER.PREF
     *
     * @mbggenerated
     */
    public String getPref() {
        return pref;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZIPCD_MASTER.PREF
     *
     * @param pref the value for ZIPCD_MASTER.PREF
     *
     * @mbggenerated
     */
    public void setPref(String pref) {
        this.pref = pref;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZIPCD_MASTER.CITY
     *
     * @return the value of ZIPCD_MASTER.CITY
     *
     * @mbggenerated
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZIPCD_MASTER.CITY
     *
     * @param city the value for ZIPCD_MASTER.CITY
     *
     * @mbggenerated
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZIPCD_MASTER.ADDR
     *
     * @return the value of ZIPCD_MASTER.ADDR
     *
     * @mbggenerated
     */
    public String getAddr() {
        return addr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZIPCD_MASTER.ADDR
     *
     * @param addr the value for ZIPCD_MASTER.ADDR
     *
     * @mbggenerated
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZIPCD_MASTER.PREF_KANA
     *
     * @return the value of ZIPCD_MASTER.PREF_KANA
     *
     * @mbggenerated
     */
    public String getPrefKana() {
        return prefKana;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZIPCD_MASTER.PREF_KANA
     *
     * @param prefKana the value for ZIPCD_MASTER.PREF_KANA
     *
     * @mbggenerated
     */
    public void setPrefKana(String prefKana) {
        this.prefKana = prefKana;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZIPCD_MASTER.CITY_KANA
     *
     * @return the value of ZIPCD_MASTER.CITY_KANA
     *
     * @mbggenerated
     */
    public String getCityKana() {
        return cityKana;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZIPCD_MASTER.CITY_KANA
     *
     * @param cityKana the value for ZIPCD_MASTER.CITY_KANA
     *
     * @mbggenerated
     */
    public void setCityKana(String cityKana) {
        this.cityKana = cityKana;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZIPCD_MASTER.ADDR_KANA
     *
     * @return the value of ZIPCD_MASTER.ADDR_KANA
     *
     * @mbggenerated
     */
    public String getAddrKana() {
        return addrKana;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZIPCD_MASTER.ADDR_KANA
     *
     * @param addrKana the value for ZIPCD_MASTER.ADDR_KANA
     *
     * @mbggenerated
     */
    public void setAddrKana(String addrKana) {
        this.addrKana = addrKana;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZIPCD_MASTER.UPDATED_AT
     *
     * @return the value of ZIPCD_MASTER.UPDATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZIPCD_MASTER.UPDATED_AT
     *
     * @param updatedAt the value for ZIPCD_MASTER.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZIPCD_MASTER.CREATED_AT
     *
     * @return the value of ZIPCD_MASTER.CREATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZIPCD_MASTER.CREATED_AT
     *
     * @param createdAt the value for ZIPCD_MASTER.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZIPCD_MASTER.LOCK_VERSION
     *
     * @return the value of ZIPCD_MASTER.LOCK_VERSION
     *
     * @mbggenerated
     */
    public Integer getLockVersion() {
        return lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZIPCD_MASTER.LOCK_VERSION
     *
     * @param lockVersion the value for ZIPCD_MASTER.LOCK_VERSION
     *
     * @mbggenerated
     */
    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZIPCD_MASTER.DELETED_FLG
     *
     * @return the value of ZIPCD_MASTER.DELETED_FLG
     *
     * @mbggenerated
     */
    public DeletedFlag getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZIPCD_MASTER.DELETED_FLG
     *
     * @param deletedFlg the value for ZIPCD_MASTER.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(DeletedFlag deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
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
        ZipcdMaster other = (ZipcdMaster) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCityCd() == null ? other.getCityCd() == null : this.getCityCd().equals(other.getCityCd()))
            && (this.getZipcd() == null ? other.getZipcd() == null : this.getZipcd().equals(other.getZipcd()))
            && (this.getPref() == null ? other.getPref() == null : this.getPref().equals(other.getPref()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getAddr() == null ? other.getAddr() == null : this.getAddr().equals(other.getAddr()))
            && (this.getPrefKana() == null ? other.getPrefKana() == null : this.getPrefKana().equals(other.getPrefKana()))
            && (this.getCityKana() == null ? other.getCityKana() == null : this.getCityKana().equals(other.getCityKana()))
            && (this.getAddrKana() == null ? other.getAddrKana() == null : this.getAddrKana().equals(other.getAddrKana()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getLockVersion() == null ? other.getLockVersion() == null : this.getLockVersion().equals(other.getLockVersion()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCityCd() == null) ? 0 : getCityCd().hashCode());
        result = prime * result + ((getZipcd() == null) ? 0 : getZipcd().hashCode());
        result = prime * result + ((getPref() == null) ? 0 : getPref().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getAddr() == null) ? 0 : getAddr().hashCode());
        result = prime * result + ((getPrefKana() == null) ? 0 : getPrefKana().hashCode());
        result = prime * result + ((getCityKana() == null) ? 0 : getCityKana().hashCode());
        result = prime * result + ((getAddrKana() == null) ? 0 : getAddrKana().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getLockVersion() == null) ? 0 : getLockVersion().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
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
        sb.append(", cityCd=").append(cityCd);
        sb.append(", zipcd=").append(zipcd);
        sb.append(", pref=").append(pref);
        sb.append(", city=").append(city);
        sb.append(", addr=").append(addr);
        sb.append(", prefKana=").append(prefKana);
        sb.append(", cityKana=").append(cityKana);
        sb.append(", addrKana=").append(addrKana);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", lockVersion=").append(lockVersion);
        sb.append(", deletedFlg=").append(deletedFlg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}