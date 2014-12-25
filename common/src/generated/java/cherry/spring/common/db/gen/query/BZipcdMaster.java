package cherry.spring.common.db.gen.query;

import javax.annotation.Generated;

/**
 * BZipcdMaster is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BZipcdMaster {

    private String addr;

    private String addrKana;

    private String city;

    private Integer cityCd;

    private String cityKana;

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private Long id;

    private Integer lockVersion;

    private String pref;

    private String prefKana;

    private org.joda.time.LocalDateTime updatedAt;

    private String zipcd;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAddrKana() {
        return addrKana;
    }

    public void setAddrKana(String addrKana) {
        this.addrKana = addrKana;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCityCd() {
        return cityCd;
    }

    public void setCityCd(Integer cityCd) {
        this.cityCd = cityCd;
    }

    public String getCityKana() {
        return cityKana;
    }

    public void setCityKana(String cityKana) {
        this.cityKana = cityKana;
    }

    public org.joda.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(org.joda.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getDeletedFlg() {
        return deletedFlg;
    }

    public void setDeletedFlg(Integer deletedFlg) {
        this.deletedFlg = deletedFlg;
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

    public String getPref() {
        return pref;
    }

    public void setPref(String pref) {
        this.pref = pref;
    }

    public String getPrefKana() {
        return prefKana;
    }

    public void setPrefKana(String prefKana) {
        this.prefKana = prefKana;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getZipcd() {
        return zipcd;
    }

    public void setZipcd(String zipcd) {
        this.zipcd = zipcd;
    }

    public String toString() {
         return "addr = " + addr + ", addrKana = " + addrKana + ", city = " + city + ", cityCd = " + cityCd + ", cityKana = " + cityKana + ", createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", id = " + id + ", lockVersion = " + lockVersion + ", pref = " + pref + ", prefKana = " + prefKana + ", updatedAt = " + updatedAt + ", zipcd = " + zipcd;
    }

}

