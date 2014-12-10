package cherry.spring.common.db.gen.query;

import javax.annotation.Generated;

/**
 * BAsyncProcessFileResultDetail is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BAsyncProcessFileResultDetail {

    private Integer asyncProcessId;

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private String description;

    private Integer id;

    private Integer lockVersion;

    private Long recordNumber;

    private org.joda.time.LocalDateTime updatedAt;

    public Integer getAsyncProcessId() {
        return asyncProcessId;
    }

    public void setAsyncProcessId(Integer asyncProcessId) {
        this.asyncProcessId = asyncProcessId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(Long recordNumber) {
        this.recordNumber = recordNumber;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "asyncProcessId = " + asyncProcessId + ", createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", description = " + description + ", id = " + id + ", lockVersion = " + lockVersion + ", recordNumber = " + recordNumber + ", updatedAt = " + updatedAt;
    }

}

