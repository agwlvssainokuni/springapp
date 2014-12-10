package cherry.spring.common.db.gen.query;

import javax.annotation.Generated;

/**
 * BAsyncProcess is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BAsyncProcess {

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private org.joda.time.LocalDateTime finishedAt;

    private Integer id;

    private org.joda.time.LocalDateTime invokedAt;

    private org.joda.time.LocalDateTime launchedAt;

    private String launchedBy;

    private Integer lockVersion;

    private org.joda.time.LocalDateTime startedAt;

    private String status;

    private String type;

    private org.joda.time.LocalDateTime updatedAt;

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

    public org.joda.time.LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(org.joda.time.LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public org.joda.time.LocalDateTime getInvokedAt() {
        return invokedAt;
    }

    public void setInvokedAt(org.joda.time.LocalDateTime invokedAt) {
        this.invokedAt = invokedAt;
    }

    public org.joda.time.LocalDateTime getLaunchedAt() {
        return launchedAt;
    }

    public void setLaunchedAt(org.joda.time.LocalDateTime launchedAt) {
        this.launchedAt = launchedAt;
    }

    public String getLaunchedBy() {
        return launchedBy;
    }

    public void setLaunchedBy(String launchedBy) {
        this.launchedBy = launchedBy;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public org.joda.time.LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(org.joda.time.LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", finishedAt = " + finishedAt + ", id = " + id + ", invokedAt = " + invokedAt + ", launchedAt = " + launchedAt + ", launchedBy = " + launchedBy + ", lockVersion = " + lockVersion + ", startedAt = " + startedAt + ", status = " + status + ", type = " + type + ", updatedAt = " + updatedAt;
    }

}

