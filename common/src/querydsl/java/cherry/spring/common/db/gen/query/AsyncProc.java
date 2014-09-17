package cherry.spring.common.db.gen.query;

import javax.annotation.Generated;

/**
 * AsyncProc is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class AsyncProc {

    private java.sql.Timestamp createdAt;

    private Integer deletedFlg;

    private java.sql.Timestamp finishedAt;

    private Integer id;

    private java.sql.Timestamp invokedAt;

    private String launcherId;

    private Integer lockVersion;

    private String name;

    private java.sql.Timestamp registeredAt;

    private String result;

    private java.sql.Timestamp startedAt;

    private String status;

    private java.sql.Timestamp updatedAt;

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

    public java.sql.Timestamp getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(java.sql.Timestamp finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.sql.Timestamp getInvokedAt() {
        return invokedAt;
    }

    public void setInvokedAt(java.sql.Timestamp invokedAt) {
        this.invokedAt = invokedAt;
    }

    public String getLauncherId() {
        return launcherId;
    }

    public void setLauncherId(String launcherId) {
        this.launcherId = launcherId;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(java.sql.Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public java.sql.Timestamp getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(java.sql.Timestamp startedAt) {
        this.startedAt = startedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.sql.Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.sql.Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}

