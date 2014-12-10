package cherry.spring.common.db.gen.query;

import javax.annotation.Generated;

/**
 * BAsyncProcessCommandResult is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BAsyncProcessCommandResult {

    private Integer asyncProcessId;

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private Integer exitValue;

    private Integer id;

    private Integer lockVersion;

    private String stderr;

    private String stdout;

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

    public Integer getExitValue() {
        return exitValue;
    }

    public void setExitValue(Integer exitValue) {
        this.exitValue = exitValue;
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

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "asyncProcessId = " + asyncProcessId + ", createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", exitValue = " + exitValue + ", id = " + id + ", lockVersion = " + lockVersion + ", stderr = " + stderr + ", stdout = " + stdout + ", updatedAt = " + updatedAt;
    }

}

