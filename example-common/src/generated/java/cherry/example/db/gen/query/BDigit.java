package cherry.example.db.gen.query;

import javax.annotation.Generated;

/**
 * BDigit is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BDigit {

    private org.joda.time.LocalDateTime createdAt;

    private Integer d;

    private Integer lockVersion;

    private org.joda.time.LocalDateTime updatedAt;

    public org.joda.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(org.joda.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "createdAt = " + createdAt + ", d = " + d + ", lockVersion = " + lockVersion + ", updatedAt = " + updatedAt;
    }

}

