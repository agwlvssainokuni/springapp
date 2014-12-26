package cherry.querytutorial.db.gen.query;

import javax.annotation.Generated;

/**
 * BPriorityMaster is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BPriorityMaster {

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private Long id;

    private Integer lockVersion;

    private Integer priorityCd;

    private String priorityLabel;

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

    public Integer getPriorityCd() {
        return priorityCd;
    }

    public void setPriorityCd(Integer priorityCd) {
        this.priorityCd = priorityCd;
    }

    public String getPriorityLabel() {
        return priorityLabel;
    }

    public void setPriorityLabel(String priorityLabel) {
        this.priorityLabel = priorityLabel;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", id = " + id + ", lockVersion = " + lockVersion + ", priorityCd = " + priorityCd + ", priorityLabel = " + priorityLabel + ", updatedAt = " + updatedAt;
    }

}

