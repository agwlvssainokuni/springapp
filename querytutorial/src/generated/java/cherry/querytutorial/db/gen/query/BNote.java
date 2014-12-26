package cherry.querytutorial.db.gen.query;

import javax.annotation.Generated;

/**
 * BNote is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BNote {

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private String description;

    private Long id;

    private Integer lockVersion;

    private org.joda.time.LocalDateTime postedAt;

    private String postedBy;

    private Long todoId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public org.joda.time.LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(org.joda.time.LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", description = " + description + ", id = " + id + ", lockVersion = " + lockVersion + ", postedAt = " + postedAt + ", postedBy = " + postedBy + ", todoId = " + todoId + ", updatedAt = " + updatedAt;
    }

}

