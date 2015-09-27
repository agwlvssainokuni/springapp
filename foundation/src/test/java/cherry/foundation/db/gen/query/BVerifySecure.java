package cherry.foundation.db.gen.query;

import javax.annotation.Generated;

/**
 * BVerifySecure is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BVerifySecure {

    private String bigdec;

    private String bigint;

    private Long id;

    private String int32;

    private String int64;

    private String str;

    public String getBigdec() {
        return bigdec;
    }

    public void setBigdec(String bigdec) {
        this.bigdec = bigdec;
    }

    public String getBigint() {
        return bigint;
    }

    public void setBigint(String bigint) {
        this.bigint = bigint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInt32() {
        return int32;
    }

    public void setInt32(String int32) {
        this.int32 = int32;
    }

    public String getInt64() {
        return int64;
    }

    public void setInt64(String int64) {
        this.int64 = int64;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
         return "bigdec = " + bigdec + ", bigint = " + bigint + ", id = " + id + ", int32 = " + int32 + ", int64 = " + int64 + ", str = " + str;
    }

}

