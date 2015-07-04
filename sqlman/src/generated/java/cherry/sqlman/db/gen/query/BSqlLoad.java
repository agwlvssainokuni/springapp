package cherry.sqlman.db.gen.query;

import javax.annotation.Generated;

/**
 * BSqlLoad is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BSqlLoad {

    private org.joda.time.LocalDateTime createdAt;

    private String databaseName;

    private Integer id;

    private Integer lockVersion;

    private String query;

    private org.joda.time.LocalDateTime updatedAt;

    public org.joda.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(org.joda.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "createdAt = " + createdAt + ", databaseName = " + databaseName + ", id = " + id + ", lockVersion = " + lockVersion + ", query = " + query + ", updatedAt = " + updatedAt;
    }

}

